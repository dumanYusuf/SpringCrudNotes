package com.dumanyusuf.springcrudnotes.presentation.home_page_view

import com.dumanyusuf.springcrudnotes.domain.model.NoteModel

data class HomePageState(
    val noteList:List<NoteModel> = emptyList(),
    val loading:Boolean=false,
    val error:String=""


)
