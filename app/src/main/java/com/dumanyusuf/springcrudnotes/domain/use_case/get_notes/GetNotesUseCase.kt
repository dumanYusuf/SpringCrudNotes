package com.dumanyusuf.springcrudnotes.domain.use_case.get_notes

import android.annotation.SuppressLint
import android.util.Log
import com.dumanyusuf.springcrudnotes.data.remote.dto.toNoteModel
import com.dumanyusuf.springcrudnotes.domain.model.NoteModel
import com.dumanyusuf.springcrudnotes.domain.repo.NotesRepo
import com.dumanyusuf.springcrudnotes.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
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
         }

         catch (e: IOException) {
             Log.e("GetNotesUseCase", "İnternet bağlantı hatası: ${e.localizedMessage}")
             emit(Resource.Error("İnternet bağlantı hatası"))
         }


         catch (e: HttpException) {
             Log.e("GetNotesUseCase", "Sunucu hatası: ${e.code()} ${e.message}")
             emit(Resource.Error("Sunucu hatası: ${e.code()}"))
         }

         catch (e: Exception) {
             Log.e("GetNotesUseCase", "Bilinmeyen hata: ${e.localizedMessage}")
             emit(Resource.Error("Beklenmeyen bir hata oluştu: ${e.localizedMessage}"))
         }
     }



}