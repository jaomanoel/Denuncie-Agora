package com.example.denuncieagora.presentation.reports.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.denuncieagora.presentation.ui.theme.DenuncieAgoraTheme

@Composable
fun PillComponent(
    label: String,
    isEnabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        elevation = null,
        contentPadding = PaddingValues(12.dp, 6.dp),
        modifier = modifier
            .background(Color.Transparent),
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if(isEnabled) MaterialTheme.colors.primary else MaterialTheme.colors.background
        ),
        border = if(isEnabled) null else BorderStroke(1.dp, MaterialTheme.colors.onBackground),
        onClick = onClick
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.body1,
            color = if(isEnabled) Color.White else MaterialTheme.colors.onBackground
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun Preview() {
    DenuncieAgoraTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            PillComponent(label = "Todos", isEnabled = true, {})
        }
    }
}