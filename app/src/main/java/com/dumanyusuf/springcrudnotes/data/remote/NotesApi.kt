package com.dumanyusuf.springcrudnotes.data.remote

import com.dumanyusuf.springcrudnotes.data.remote.dto.NotesDto
import com.dumanyusuf.springcrudnotes.data.remote.dto.NotesDtoItem
import com.dumanyusuf.springcrudnotes.domain.model.DtoMyNotesIU
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NotesApi {


    @GET("/rest/api/notes/list")
    suspend fun getNotes():NotesDto

    @POST("/rest/api/notes/save")
    suspend fun saveNotes(@Body note: DtoMyNotesIU):NotesDtoItem


}