package com.dumanyusuf.springcrudnotes.domain.model

data class DtoMyNotesIU(
    val titleNote: String,
    val contentNote: String,
    val isCompleted: Boolean = false
)

