package com.example.denuncieagora.presentation.new_report.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.denuncieagora.domain.enums.HateCrimeTypeEnum
import com.example.denuncieagora.presentation.ui.theme.DenuncieAgoraTheme

@Composable
fun DropdownComponent(
    error: String?,
    onValueChange: (HateCrimeTypeEnum) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val suggestions = HateCrimeTypeEnum.values()
    var selectedItem by remember {
        mutableStateOf<HateCrimeTypeEnum?>(null)
    }
    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    val icon = if(expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedItem?.name ?: "Selecione um crime",
            onValueChange = { value ->
                selectedItem = suggestions.find { it.name == value }
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            trailingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 6.dp)
                        .clickable { expanded = !expanded }
                )
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false},
            modifier = Modifier
                .width(with(LocalDensity.current){textFieldSize.width.toDp()})
        ) {
            suggestions.forEach { item ->
                DropdownMenuItem(onClick = {
                    selectedItem = item
                    expanded = false
                    onValueChange(item)
                }) {
                    Text(
                        text = item.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W800,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                }
            }
        }

        if(error != null) {
            Text(text = error, color = MaterialTheme.colors.error)
        }
        Spacer(modifier = Modifier.height(16.dp))

        LaunchedEffect(selectedItem) {
            selectedItem?.let { onValueChange(it) }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    DenuncieAgoraTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            DropdownComponent(error = null, onValueChange = {})
        }
    }
}