package com.proxiserve.proximobilite.modules.accueil.features.mode_admin.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.proxiserve.proximobilite.modules.intervention.feaures.intervention_validation.presentation.components.SelectDropString
import com.proxiserve.proximobilite.ui.theme.Gris_100
import com.proxiserve.proximobilite.ui.theme.Gris_200
import com.proxiserve.proximobilite.ui.theme.Gris_700

/**
 * Created by dloriot on 15/07/2024.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSpinner(options: List<String>, label: String) {
    var expanded by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(options[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {

        OutlinedTextField(
            modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable),
            value = text,
            onValueChange = {},
            readOnly = true,
            singleLine = true,
            label = { Text(label, fontSize = 20.sp, color = MaterialTheme.colorScheme.primary) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedTextColor = MaterialTheme.colorScheme.primary,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                focusedBorderColor = MaterialTheme.colorScheme.secondary
            ),
            shape = RoundedCornerShape(10.dp)
        )

        ExposedDropdownMenu(
            modifier = Modifier
                .background(Gris_100)
                .padding(10.dp),
            expanded = expanded,
            onDismissRequest = { expanded = false },
            shape = RoundedCornerShape(10.dp)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    modifier = Modifier.background(Gris_100),
                    text = {
                        Text(
                            text = option,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    },
                    onClick = {
                        text = option
                        expanded = false
                    },
                    contentPadding = PaddingValues(vertical = 0.dp),
                )

            }
        }

    }
}

@Preview(showBackground = false)
@Composable
fun SelectDropStringPreview() {
    AppSpinner(listOf("Option 1", "Option 2", "Option 3"),"Edson")
}