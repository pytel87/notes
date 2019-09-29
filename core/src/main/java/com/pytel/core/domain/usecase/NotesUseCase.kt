package com.pytel.core.domain.usecase

import com.pytel.core.domain.common.Result
import com.pytel.core.domain.manager.NotesManager
import com.pytel.core.domain.model.Note

/**
 * Created by Vladimir Skouy on 2019-09-17.
 */

class  NotesUseCase(private val notesManager: NotesManager) {

    suspend fun getAllNotes(): Result<List<Note>> {
        return notesManager.getAll()
    }

}