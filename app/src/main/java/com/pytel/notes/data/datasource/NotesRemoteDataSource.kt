package com.pytel.notes.data.datasource

import com.pytel.notes.domain.common.Result
import com.pytel.notes.domain.model.Note

/**
 * Created by Vladimir Skouy on 2019-09-17.
 */
interface NotesRemoteDataSource {

    suspend fun getAll(): Result<List<Note>>

    suspend fun update(note:Note): Result<Boolean>

    suspend fun delete(noteId:Int): Result<Boolean>

    suspend fun create(title:String): Result<Note>

    suspend fun get(noteId:Int): Result<Note>

}