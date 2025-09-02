package com.proxiserve.proximobilite.modules.intervention.feaures.intervention_validation.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * SelectDropString
 * @author Edson De CARVALHO
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectDropString(
    options: List<String> = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5"),
    initialSelectedOption: String = options[0],
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(initialSelectedOption) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.background(Color.White, shape = RoundedCornerShape(10.dp))
        ) {
            TextField(
                modifier = Modifier
                    .menuAnchor()
                    .border(3.dp, Color.Black, RoundedCornerShape(0.dp)),
                textStyle = TextStyle.Default.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                ),
                readOnly = true,
                value = selectedOptionText,
                shape = RoundedCornerShape(10.dp),
                onValueChange = {},
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White
                )
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption, color = Color.Black) },
                        onClick = {
                            selectedOptionText = selectionOption
                            expanded = false
                            onOptionSelected(selectionOption)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}
@Preview(showBackground = false)
@Composable
fun SelectDropStringPreview() {
    var selectedOption by remember { mutableStateOf("Option 1") }
    SelectDropString(
        options = listOf("Option A", "Option B", "Option C"),
        initialSelectedOption = "Option B",
    ) { selected ->
        selectedOption = selected
    }
}