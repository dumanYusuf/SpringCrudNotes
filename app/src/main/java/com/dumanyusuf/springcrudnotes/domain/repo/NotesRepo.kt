package com.dumanyusuf.springcrudnotes.domain.repo

import com.dumanyusuf.springcrudnotes.data.remote.dto.NotesDto

interface NotesRepo {


    suspend fun getNotes():NotesDto

}