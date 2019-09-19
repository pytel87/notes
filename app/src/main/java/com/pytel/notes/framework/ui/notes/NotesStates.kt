package com.pytel.notes.framework.ui.notes

import com.pytel.notes.domain.common.ErrorResult
import com.pytel.notes.domain.model.Note

sealed class NotesStates(val showLoading: Boolean) {

    object NotInitialized : NotesStates(showLoading = false)

    object Loading : NotesStates(showLoading = true)

    class NotesLoaded(val notes: List<Note>) : NotesStates(showLoading = false)

    class NotesError(val error: ErrorResult) : NotesStates(showLoading = false)

    object Invalid : NotesStates(showLoading = false)

}