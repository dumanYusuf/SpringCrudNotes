package com.dumanyusuf.springcrudnotes.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomSearchTextField(
    search: String,
    onSearchChange: (String) -> Unit
) {
    OutlinedTextField(
        value = search,
        onValueChange = onSearchChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        placeholder = { Text("Notlarda ara...") },
        shape = MaterialTheme.shapes.medium,
        trailingIcon = {
            if (search.isNotBlank()){
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Kapat",
                    modifier = Modifier.size(30.dp).clickable {
                        onSearchChange("")
                    }
                )
            }
        },
        singleLine = true,
        minLines = 1,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = Color.Gray,
            cursorColor = MaterialTheme.colorScheme.primary
        )
    )
}