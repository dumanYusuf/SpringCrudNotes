package com.dumanyusuf.springcrudnotes.presentation.home_page_view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dumanyusuf.springcrudnotes.data.remote.dto.toNoteModel
import com.dumanyusuf.springcrudnotes.domain.use_case.get_notes.GetNotesUseCase
import com.dumanyusuf.springcrudnotes.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase
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


}