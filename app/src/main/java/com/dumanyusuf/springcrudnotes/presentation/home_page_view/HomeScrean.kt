package com.dumanyusuf.springcrudnotes.presentation.home_page_view

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dumanyusuf.springcrudnotes.domain.model.DtoMyNotesIU
import com.dumanyusuf.springcrudnotes.util.CustomSearchTextField
import com.dumanyusuf.springcrudnotes.util.SaveNoteDialog

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScrean(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val stateNoteList = viewModel.getStateNote.collectAsState()
    var search by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.loadNotes()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Crud Spring") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Text("+", style = MaterialTheme.typography.titleLarge)
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {


                CustomSearchTextField(
                    search = search,
                    onSearchChange = { search = it }
                )

                Spacer(modifier = Modifier.height(8.dp))


                when {
                    stateNoteList.value.loading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 30.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = Color.Red)
                        }
                    }

                    stateNoteList.value.error.isNotBlank() -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 30.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stateNoteList.value.error,
                                color = Color.Red
                            )
                        }
                    }

                    else -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(12.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(stateNoteList.value.noteList.filter {
                                it.titleNote.contains(search, ignoreCase = true) ||
                                        it.contentNote.contains(search, ignoreCase = true)
                            }) { note ->
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                                ) {
                                    Column(modifier = Modifier.padding(16.dp)) {
                                        Text(
                                            text = note.titleNote,
                                            style = MaterialTheme.typography.titleMedium.copy(
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
                                            Text(
                                                text = note.contentNote,
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                            Icon(
                                                modifier = Modifier.size(30.dp).clickable {
                                                    viewModel.deleteNote(note.id)
                                                },
                                                imageVector = Icons.Default.Close, contentDescription = "")
                                        }
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            text = note.createdAt,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = Color.Gray
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )


    if (showDialog) {
        SaveNoteDialog(
            onDismiss = { showDialog = false },
            onSave = { title, content ->
                showDialog = false
                viewModel.saveNote(
                    DtoMyNotesIU(
                        titleNote = title,
                        contentNote = content,
                        isCompleted = false
                    )
                )
            }
        )
    }
}






