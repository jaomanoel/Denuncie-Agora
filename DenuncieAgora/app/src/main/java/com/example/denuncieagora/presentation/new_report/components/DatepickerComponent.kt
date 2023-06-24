package com.example.denuncieagora.presentation.new_report.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.denuncieagora.R
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.time.LocalDate

@Composable
fun DatePickerComponent(
    label: LocalDate?,
    error: String?,
    onSelection: (LocalDate) -> Unit,
    modifier: Modifier = Modifier
) {
    var enabled by remember {
        mutableStateOf(false)
    }
    val icon = if(enabled) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    val calendarState = rememberUseCaseState(
        onDismissRequest = {
            enabled = false
        },
        onCloseRequest = {
            enabled = false
        },
        onFinishedRequest = {
            enabled = false
        }
    )

    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            yearSelection = true,
            monthSelection = true,
            style = CalendarStyle.MONTH
        ),
        selection = CalendarSelection.Date { date ->
            enabled = false
            onSelection(date)
        }
    )

    Column(modifier = modifier.fillMaxWidth()) {
        Button(
            onClick = {
                enabled = true
                calendarState.show()
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            contentPadding = PaddingValues(vertical = 10.dp, horizontal = 15.dp),
            elevation = null,
            border = BorderStroke(1.dp, Color.Gray)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = label?.toString() ?: stringResource(R.string.placeholder_date_report),
                    style = MaterialTheme.typography.body1,

                )
                Icon(
                    imageVector = icon,
                    contentDescription = stringResource(R.string.accessibility_date),
                    tint = Color.Gray
                )
            }
        }

        if(error != null) {
            Text(text = error, color = MaterialTheme.colors.error)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}