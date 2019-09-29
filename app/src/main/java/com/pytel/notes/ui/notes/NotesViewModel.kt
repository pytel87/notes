package com.pytel.notes.ui.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pytel.core.domain.common.Result
import com.pytel.core.domain.usecase.NotesUseCase
import com.pytel.notes.ui.base.BaseViewModel
import com.pytel.notes.ui.base.CoroutineContextProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NotesViewModel(private val notesUseCase: NotesUseCase, private val contextProvider: CoroutineContextProvider) : BaseViewModel() {

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
            val result = withContext(contextProvider.IO) {
                notesUseCase.getAllNotes()
            }
            when (result) {
                is Result.Success -> {
                    viewState.value = NotesStates.NotesLoaded(result.data)
                }
                is Result.Error -> {
                    viewState.value = NotesStates.NotesError(result.error)
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