package com.pytel.notes.framework.ui.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pytel.notes.domain.common.Result
import com.pytel.notes.domain.usecase.NotesUseCase
import com.pytel.notes.framework.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NotesViewModel(private val notesUseCase: NotesUseCase) : BaseViewModel() {
    val viewState = MutableLiveData<NotesStates>()

    init {
        reset()
    }

    fun reset() {
        viewState.value = NotesStates.NotInitialized
    }

    fun loadNotes() {
        viewState.value = NotesStates.Loading
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                notesUseCase.getAllNotes()
            }
            when (result) {
                is Result.Success -> {
                    viewState.value = NotesStates.NotesLoaded(result.data)
                }
                is Result.Error -> {
                    viewState.value = NotesStates.LoadingError(result.error)
                }
            }
        }
    }

    fun checkIfValidData() {
        if (viewState.value == NotesStates.Invalid) {
            reset()
        }
    }

    fun invalidate() {
        viewState.value = NotesStates.Invalid
    }

}