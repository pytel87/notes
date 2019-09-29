package com.pytel.notes.framework.data.local

import com.pytel.core.data.datasource.NotesCache
import com.pytel.core.data.datasource.NotesLocalDataSource
import com.pytel.core.domain.common.ErrorCode
import com.pytel.core.domain.common.ErrorCodeResult
import com.pytel.core.domain.common.Result
import com.pytel.core.domain.model.Note

/**
 * Created by Vladimir Skouy on 2019-09-17.
 */

class NotesLocalDataSourceImpl(private val notesCache: NotesCache) : NotesLocalDataSource{

    override suspend fun getAll(): Result<List<Note>> {
        val notes = notesCache.getAll()
        return if (notes.isNotEmpty()){
            Result.Success(notes)
        }else{
            Result.Error(
                ErrorCodeResult(
                    code = ErrorCode.NO_NOTES_IN_CACHE,
                    message = "No data in cache"
                )
            )
        }
    }

    override suspend fun get(noteId: Int): Result<Note> {
        val note = notesCache.get(noteId)
        return note?.let { Result.Success(it) } ?: run {
            Result.Error(
                ErrorCodeResult(
                    code = ErrorCode.NO_NOTES_IN_CACHE,
                    message = "No data in cache"
                )
            )
        }
    }

}