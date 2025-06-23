package com.dumanyusuf.springcrudnotes.domain.use_case.post_use_case

import android.util.Log
import com.dumanyusuf.springcrudnotes.data.remote.dto.NotesDtoItem
import com.dumanyusuf.springcrudnotes.domain.model.DtoMyNotesIU
import com.dumanyusuf.springcrudnotes.domain.repo.NotesRepo
import com.dumanyusuf.springcrudnotes.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveNoteUseCase @Inject constructor(private val repo: NotesRepo) {



    fun saveNote(notesIU: DtoMyNotesIU): Flow<Resource<NotesDtoItem>> = flow {
        try {
            Log.d("SaveNoteUseCase", "Not kaydediliyor: $notesIU")

            val notesItem = repo.saveNotes(note = notesIU)

            Log.d("SaveNoteUseCase", "Sunucudan dönen cevap: $notesItem")

            emit(Resource.Success(notesItem))
        } catch (e: Exception) {
            Log.e("SaveNoteUseCase", "Hata oluştu: ${e.localizedMessage}", e)
            emit(Resource.Error("Hata çıktı: ${e.localizedMessage}"))
        }
    }


}