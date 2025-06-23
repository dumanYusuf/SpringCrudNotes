package com.dumanyusuf.springcrudnotes.data.remote

import com.dumanyusuf.springcrudnotes.data.remote.dto.NotesDto
import com.dumanyusuf.springcrudnotes.data.remote.dto.NotesDtoItem
import com.dumanyusuf.springcrudnotes.domain.model.DtoMyNotesIU
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface NotesApi {


    @GET("/rest/api/notes/list")
    suspend fun getNotes():NotesDto

    @POST("/rest/api/notes/save")
    suspend fun saveNotes(@Body note: DtoMyNotesIU):NotesDtoItem

    @DELETE("/rest/api/notes/delete/{id}")
    suspend fun deleteNote(
        @retrofit2.http.Path("id") id: Int
    ): retrofit2.Response<Unit>

    @PUT("/rest/api/notes/update/{id}")
    suspend fun updateNote(
        @retrofit2.http.Path("id") id: Int,
        @Body updatedNote: DtoMyNotesIU
    ): NotesDtoItem




}