package com.pytel.notes.ui.notes

import com.pytel.core.domain.common.ErrorResult
import com.pytel.core.domain.model.Note

sealed class NotesStates(val showLoading: Boolean) {

    object NotInitialized : NotesStates(showLoading = false)

    object Loading : NotesStates(showLoading = true)

    data class NotesLoaded(val notes: List<Note>) : NotesStates(showLoading = false)

    data class NotesError(val error: ErrorResult) : NotesStates(showLoading = false)

    object Invalid : NotesStates(showLoading = false)

}