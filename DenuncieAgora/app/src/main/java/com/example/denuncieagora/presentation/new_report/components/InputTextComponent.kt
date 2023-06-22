package com.example.denuncieagora.presentation.new_report.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun InputTextComponent(
    label: String,
    error: String?,
    placeholder: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        TextField(
            value = label,
            onValueChange = {
                onChange(it)
            },
            isError = error != null,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = placeholder)
            }
        )

        if(error != null) {
            Text(text = error, color = MaterialTheme.colors.error)
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}