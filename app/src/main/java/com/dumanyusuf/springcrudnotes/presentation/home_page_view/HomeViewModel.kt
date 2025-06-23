package com.dumanyusuf.springcrudnotes.presentation.home_page_view

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dumanyusuf.springcrudnotes.data.remote.dto.toNoteModel
import com.dumanyusuf.springcrudnotes.domain.model.DtoMyNotesIU
import com.dumanyusuf.springcrudnotes.domain.use_case.delete_note.DeleteNoteUseCase
import com.dumanyusuf.springcrudnotes.domain.use_case.get_notes.GetNotesUseCase
import com.dumanyusuf.springcrudnotes.domain.use_case.post_use_case.SaveNoteUseCase
import com.dumanyusuf.springcrudnotes.domain.use_case.update_note.UpdateNoteUseCase
import com.dumanyusuf.springcrudnotes.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
) :ViewModel() {


     private val _getNoteState=MutableStateFlow<HomePageState>(HomePageState())
     val getStateNote:StateFlow<HomePageState> = _getNoteState.asStateFlow()




    fun loadNotes(){
        getNotesUseCase.getNotesUseCase().onEach {
            when(it){
                is Resource.Success->{
                    _getNoteState.value= HomePageState(noteList = it.data?: emptyList())
                    Log.e("basarırlı","notlar basarı ile geldi")
                }
                is Resource.Loading->{
                    _getNoteState.value= HomePageState(loading = true)
                    Log.e("loading","notlar loading ile geldi")
                }
                is Resource.Error->{
                    _getNoteState.value= HomePageState(error = it.message?:"hata cıktı")
                    Log.e("basarısız","notlar basarısız:${it.message}")
                }
            }
        }.launchIn(viewModelScope)
    }


    fun saveNote(notesIU: DtoMyNotesIU){
        viewModelScope.launch {
            Log.e("saveNote", "saveNote çağrıldı")
            saveNoteUseCase.saveNote(notesIU).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        Log.e("saveNote", "Başarıyla kaydedildi: ${result.data}")
                        loadNotes()
                    }
                    is Resource.Error -> {
                        Log.e("saveNote", "Hata: ${result.message}")
                    }
                    is Resource.Loading -> {
                        Log.d("saveNote", "Yükleniyor...")
                    }
                }
            }
        }
    }


    fun deleteNote(id: Int) {
        viewModelScope.launch {
            deleteNoteUseCase.deleteNote(id).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        Log.d("DeleteNote", "Başarıyla silindi.")
                        loadNotes()
                    }
                    is Resource.Error -> {
                        Log.e("DeleteNote", "Hata: ${result.message}")
                    }
                    is Resource.Loading -> {
                        Log.d("DeleteNote", "Siliniyor...")
                    }
                }
            }
        }
    }

    fun updateNote(id: Int,dtoMyNotesIU: DtoMyNotesIU) {
        viewModelScope.launch {
            updateNoteUseCase.updateNote(id,dtoMyNotesIU).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        Log.d("UpdateNote", "Başarıyla silindi.")
                        loadNotes()
                    }
                    is Resource.Error -> {
                        Log.e("UpdateNote", "Hata: ${result.message}")
                    }
                    is Resource.Loading -> {
                        Log.d("UpdateNote", "güncelleniyor...")
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatCreatedAt(isoString: String): String {
        return try {
            val dateTime = LocalDateTime.parse(isoString)
            val formatter = DateTimeFormatter.ofPattern("yyyy MMMM dd HH:mm", Locale("tr"))
            dateTime.format(formatter)
        } catch (e: Exception) {
            isoString
        }
    }



}