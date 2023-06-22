package com.example.denuncieagora.presentation.reports.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.denuncieagora.R
import com.example.denuncieagora.domain.enums.HateCrimeTypeEnum
import com.example.denuncieagora.domain.useCases.reports.DeleteReport
import com.example.denuncieagora.presentation.ui.theme.DenuncieAgoraTheme
import com.example.denuncieagora.utils.getAdvertisingInfo
import java.util.UUID

@Composable
fun CardReportComponent(
    id: UUID,
    isOwner: Boolean,
    about: HateCrimeTypeEnum,
    city: String,
    state: String,
    date: String,
    description: String,
    onClickButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 20.dp)) {
        HeaderCardReport(about.name, city, state, date);
        BodyCardReport(id, isOwner, description, onClickButton)
    }
}

@Composable
private fun HeaderCardReport(
    about: String,
    city: String,
    state: String,
    date: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = about,
                fontSize = 18.sp,
                fontWeight = FontWeight.W800,
                modifier = Modifier.padding(bottom = 2.dp)
            )

            Row {
                Text(
                    text = city,
                    style = MaterialTheme.typography.caption
                )

                Text(
                    text = ", ",
                    style = MaterialTheme.typography.caption
                )

                Text(
                    text = state,
                    style = MaterialTheme.typography.caption
                )
            }
        }

        Text(
            text = date.replace("-","/"),
            fontSize = 16.sp
        )
    }
}

@Composable
private fun BodyCardReport(id: UUID, isOwner: Boolean, description: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val paddingBox: Dp = 8.dp
    val borderSize: Dp = 2.dp

    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = borderSize,
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colors.onBackground
            )
    ) {
        Text(
            text = description,
            fontSize = 16.sp,
            fontWeight = FontWeight.W600,
            modifier = Modifier.padding(paddingBox)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .border(
                    width = borderSize,
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colors.onBackground
                )
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                elevation = null,
                onClick = onClick,
                modifier = Modifier.align(Alignment.CenterEnd),
            ) {
                Icon(
                    imageVector = if (isOwner) Icons.Filled.Delete else Icons.Filled.MoreVert,
                    tint = MaterialTheme.colors.onBackground,
                    contentDescription = stringResource(R.string.accessibility_modal),
                    modifier = Modifier.size(20.dp),
                )
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun Preview() {
    DenuncieAgoraTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {

        }
    }
}