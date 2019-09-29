package com.pytel.notes.ui.note

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pytel.core.domain.common.Result
import com.pytel.core.domain.usecase.NoteUseCase
import com.pytel.notes.ui.base.BaseViewModel
import com.pytel.notes.framework.utils.logDebug
import com.pytel.notes.framework.utils.logError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NoteViewModel(private val noteUseCase: NoteUseCase) : BaseViewModel() {
    val viewState = MutableLiveData<NoteStates>()

    init {
        reset()
    }

    fun reset() {
        viewState.value = NoteStates.NotInitialized
    }

    fun loadNote(noteId:Int){
        viewState.value = NoteStates.Loading
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                noteUseCase.getNote(noteId)
            }
            when (result) {
                is Result.Success -> {
                    viewState.value = NoteStates.NoteLoaded(result.data)
                }
                is Result.Error -> {
                    viewState.value = NoteStates.NoteError(result.error)
                }
            }
        }
    }

    fun createNote(title:String){
        viewState.value = NoteStates.Loading
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                noteUseCase.createNote(title)
            }
            when (result) {
                is Result.Success -> {
                    viewState.value = NoteStates.NoteCreated(result.data)
                }
                is Result.Error -> {
                    viewState.value = NoteStates.NoteError(result.error)
                }
            }
        }
    }

    fun deleteNote(noteId:Int){
        viewState.value = NoteStates.Loading
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                noteUseCase.deleteNote(noteId)
            }
            when (result) {
                is Result.Success -> {
                    viewState.value = NoteStates.NoteDeleted
                }
                is Result.Error -> {
                    viewState.value = NoteStates.NoteError(result.error)
                }
            }
        }
    }

    fun updateNote(noteId:Int,title:String){
        GlobalScope.launch {
            val result = withContext(Dispatchers.IO) {
                noteUseCase.updateNote(noteId,title)
            }
            when (result) {
                is Result.Success -> {
                    logDebug ("Note updated")
                }
                is Result.Error -> {
                    logError ("Note update error: ${result.error}")
                }
            }
        }
    }



    fun checkIfValidData() {
        if (viewState.value == NoteStates.Invalid) {
            reset()
        }
    }

    fun invalidate() {
        viewState.value = NoteStates.Invalid
    }

}