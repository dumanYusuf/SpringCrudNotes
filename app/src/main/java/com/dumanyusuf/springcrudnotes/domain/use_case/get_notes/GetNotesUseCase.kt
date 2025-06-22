package com.dumanyusuf.springcrudnotes.domain.use_case.get_notes

import android.annotation.SuppressLint
import android.util.Log
import com.dumanyusuf.springcrudnotes.data.remote.dto.toNoteModel
import com.dumanyusuf.springcrudnotes.domain.model.NoteModel
import com.dumanyusuf.springcrudnotes.domain.repo.NotesRepo
import com.dumanyusuf.springcrudnotes.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(private val repo: NotesRepo) {


     @SuppressLint("SuspiciousIndentation")
     fun getNotesUseCase(): Flow<Resource<List<NoteModel>>> = flow {
         try {
             emit(Resource.Loading())
             val noteList = repo.getNotes()
             Log.d("GetNotesUseCase", "Çekilen not sayısı: ${noteList.size}")

             if (noteList.isNotEmpty()) {
                 emit(Resource.Success(noteList.toNoteModel()))
             } else {
                 emit(Resource.Error("Not bulunamadı"))
             }
         } catch (e: Exception) {
             Log.e("GetNotesUseCase", "Hata oluştu: ${e.localizedMessage}")
             emit(Resource.Error("errorr:${e.localizedMessage}"))
         }
     }



}