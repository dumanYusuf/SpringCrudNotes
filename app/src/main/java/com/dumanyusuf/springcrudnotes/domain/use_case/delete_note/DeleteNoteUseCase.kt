package com.dumanyusuf.springcrudnotes.domain.use_case.delete_note

import android.util.Log
import com.dumanyusuf.springcrudnotes.domain.repo.NotesRepo
import com.dumanyusuf.springcrudnotes.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(private val repo: NotesRepo) {


    fun deleteNote(Id:Int):Flow<Resource<Unit>> = flow {
        try {
           val response= repo.deleteNote(Id)
            if (response.isSuccessful){
                emit(Resource.Success(Unit))
                Log.e("silme","silme işlemi basarılı")
            }
            else{
                emit(Resource.Error("silinemedi:${response.code()}"))
            }
        }
        catch (e:Exception){
            emit(Resource.Error("hata cıktı:${e.localizedMessage}"))
        }
    }


}