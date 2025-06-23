package com.dumanyusuf.springcrudnotes.data.repo

import com.dumanyusuf.springcrudnotes.data.remote.NotesApi
import com.dumanyusuf.springcrudnotes.data.remote.dto.NotesDto
import com.dumanyusuf.springcrudnotes.data.remote.dto.NotesDtoItem
import com.dumanyusuf.springcrudnotes.domain.model.DtoMyNotesIU
import com.dumanyusuf.springcrudnotes.domain.repo.NotesRepo
import retrofit2.Response
import javax.inject.Inject

class NoteRepoImpl @Inject constructor(private val api:NotesApi) :NotesRepo {


    override suspend fun getNotes(): NotesDto {
        return api.getNotes();
    }

    override suspend fun saveNotes(note: DtoMyNotesIU): NotesDtoItem {
        return api.saveNotes(note)
    }

    override suspend fun deleteNote(Id: Int): Response<Unit> {
        return api.deleteNote(id = Id)
    }

    override suspend fun updateNote(Id: Int, dtoNotesUI: DtoMyNotesIU): NotesDtoItem {
        return api.updateNote(Id, dtoNotesUI)
    }
}