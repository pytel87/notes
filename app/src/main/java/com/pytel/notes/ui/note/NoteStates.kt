package com.pytel.notes.ui.note

import com.pytel.core.domain.common.ErrorResult
import com.pytel.core.domain.model.Note

sealed class NoteStates(val showLoading: Boolean = false) {

    object NotInitialized : NoteStates()

    object Loading : NoteStates(showLoading = true)

    class NoteCreated(val note:Note) : NoteStates()

    class NoteLoaded(val note:Note) : NoteStates()

    object NoteUpdated : NoteStates()

    object NoteDeleted : NoteStates()

    class NoteError(val error: ErrorResult) : NoteStates()

    object Invalid : NoteStates()

}