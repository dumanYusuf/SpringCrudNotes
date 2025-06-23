package com.dumanyusuf.springcrudnotes.domain.use_case.update_note

import com.dumanyusuf.springcrudnotes.data.remote.dto.NotesDtoItem
import com.dumanyusuf.springcrudnotes.domain.model.DtoMyNotesIU
import com.dumanyusuf.springcrudnotes.domain.repo.NotesRepo
import com.dumanyusuf.springcrudnotes.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateNoteUseCase @Inject constructor(private val repo: NotesRepo) {


    fun updateNote(Id:Int,dtoMyNotesIU: DtoMyNotesIU):Flow<Resource<NotesDtoItem>> = flow {

      try {
          val response= repo.updateNote(Id,dtoMyNotesIU)
          emit(Resource.Success(response))
      }
      catch (e:Exception){
          emit(Resource.Error("hata cıktı:${e.localizedMessage}"))
      }


    }

}