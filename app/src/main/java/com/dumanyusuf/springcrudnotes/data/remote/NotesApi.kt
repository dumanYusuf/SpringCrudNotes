package com.dumanyusuf.springcrudnotes.data.remote

import com.dumanyusuf.springcrudnotes.data.remote.dto.NotesDto
import retrofit2.http.GET

interface NotesApi {


    @GET("/rest/api/notes/list")
    suspend fun getNotes():NotesDto


}