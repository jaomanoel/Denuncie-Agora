package com.example.denuncieagora.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.denuncieagora.R
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.denuncieagora.presentation.ui.theme.DenuncieAgoraTheme

@Composable
fun ButtonComponent(label: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val gradient = Brush.linearGradient(
        colors = listOf(MaterialTheme.colors.primary, MaterialTheme.colors.secondary),
        start = Offset.Zero,
        end = Offset.Infinite
    )

    Button(
        modifier = modifier
            .background(brush = gradient, shape = MaterialTheme.shapes.small)
            .fillMaxWidth(),
        contentPadding = PaddingValues(12.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            disabledBackgroundColor = Color.Transparent),
        elevation = null,
        onClick = onClick,
    ) {
        Text(
            text = label,
            color = Color.White,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.button,
        )
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun Preview() {
    DenuncieAgoraTheme {
        ButtonComponent(stringResource(R.string.button_home), onClick = { })
    }
}