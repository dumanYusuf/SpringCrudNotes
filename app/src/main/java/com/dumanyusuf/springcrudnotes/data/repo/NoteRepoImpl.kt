package com.dumanyusuf.springcrudnotes.data.repo

import com.dumanyusuf.springcrudnotes.data.remote.NotesApi
import com.dumanyusuf.springcrudnotes.data.remote.dto.NotesDto
import com.dumanyusuf.springcrudnotes.domain.repo.NotesRepo
import javax.inject.Inject

class NoteRepoImpl @Inject constructor(private val api:NotesApi) :NotesRepo {


    override suspend fun getNotes(): NotesDto {
        return api.getNotes();
    }
}