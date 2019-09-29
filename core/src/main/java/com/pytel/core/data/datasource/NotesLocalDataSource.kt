package com.pytel.core.data.datasource

import com.pytel.core.domain.model.Note
import com.pytel.core.domain.common.Result

/**
 * Created by Vladimir Skouy on 2019-09-17.
 */
interface NotesLocalDataSource {
    suspend fun getAll(): Result<List<Note>>

    suspend fun get(noteId: Int): Result<Note>
}