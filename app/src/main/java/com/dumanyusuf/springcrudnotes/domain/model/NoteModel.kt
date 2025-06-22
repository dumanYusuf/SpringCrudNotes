package com.dumanyusuf.springcrudnotes.domain.model

data class NoteModel(
    val contentNote: String,
    val createdAt: String,
    val id: Int,
    val isCompleted: Boolean,
    val titleNote: String
)
