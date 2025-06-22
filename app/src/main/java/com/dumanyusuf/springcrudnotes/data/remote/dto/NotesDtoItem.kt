package com.dumanyusuf.springcrudnotes.data.remote.dto

data class NotesDtoItem(
    val contentNote: String,
    val createdAt: String,
    val id: Int,
    val isCompleted: Boolean,
    val titleNote: String
)