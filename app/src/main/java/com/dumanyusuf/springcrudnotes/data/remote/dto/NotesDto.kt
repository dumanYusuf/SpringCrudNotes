package com.dumanyusuf.springcrudnotes.data.remote.dto

import com.dumanyusuf.springcrudnotes.domain.model.NoteModel

class NotesDto : ArrayList<NotesDtoItem>()

fun NotesDto.toNoteModel(): List<NoteModel> {
    return map {
        NoteModel(
            contentNote = it.contentNote,
            createdAt = it.createdAt,
            id = it.id,
            titleNote = it.titleNote,
            isCompleted = it.isCompleted
        )
    }
}



