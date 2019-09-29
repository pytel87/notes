package com.pytel.core.data.datasource

import com.pytel.core.domain.common.Result
import com.pytel.core.domain.model.Note

/**
 * Created by Vladimir Skouy on 2019-09-17.
 */
interface NotesRemoteDataSource {

    suspend fun getAll(): Result<List<Note>>

    suspend fun update(noteId:Int, title:String): Result<Note>

    suspend fun delete(noteId:Int): Result<Boolean>

    suspend fun create(title:String): Result<Note>

    suspend fun get(noteId:Int): Result<Note>

}