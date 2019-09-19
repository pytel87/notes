package com.pytel.notes.framework.data.local

import com.pytel.notes.data.datasource.NotesCache
import com.pytel.notes.data.datasource.NotesLocalDataSource
import com.pytel.notes.domain.common.ErrorCode
import com.pytel.notes.domain.common.ErrorCodeResult
import com.pytel.notes.domain.common.Result
import com.pytel.notes.domain.model.Note
import com.pytel.notes.framework.data.dao.NotesDao

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