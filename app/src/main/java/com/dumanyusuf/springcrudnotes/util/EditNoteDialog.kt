package com.dumanyusuf.springcrudnotes.util

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EditNoteDialog(
    initialTitle: String,
    initialContent: String,
    onDismiss: () -> Unit,
    onUpdate: (String, String) -> Unit
) {
    var updatedTitle by remember { mutableStateOf(initialTitle) }
    var updatedContent by remember { mutableStateOf(initialContent) }

    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = "Notu Güncelle") },
        text = {
            Column {
                OutlinedTextField(
                    value = updatedTitle,
                    onValueChange = { updatedTitle = it },
                    label = { Text("Başlık") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = updatedContent,
                    onValueChange = { updatedContent = it },
                    label = { Text("İçerik") },
                    maxLines = 5
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onUpdate(updatedTitle, updatedContent)
            }) {
                Text("Güncelle")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("İptal")
            }
        }
    )
}