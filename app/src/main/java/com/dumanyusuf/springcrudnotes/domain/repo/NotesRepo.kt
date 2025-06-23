package com.dumanyusuf.springcrudnotes.domain.repo

import com.dumanyusuf.springcrudnotes.data.remote.dto.NotesDto
import com.dumanyusuf.springcrudnotes.data.remote.dto.NotesDtoItem
import com.dumanyusuf.springcrudnotes.domain.model.DtoMyNotesIU
import retrofit2.Response

interface NotesRepo {


    suspend fun getNotes():NotesDto
    suspend fun saveNotes(note:DtoMyNotesIU):NotesDtoItem
    suspend fun deleteNote(Id:Int):Response<Unit>
    suspend fun updateNote(Id: Int,dtoNotesUI:DtoMyNotesIU):NotesDtoItem

}