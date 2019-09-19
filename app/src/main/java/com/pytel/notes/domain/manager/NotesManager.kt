package com.pytel.notes.domain.manager

import com.pytel.notes.domain.common.Result
import com.pytel.notes.domain.model.Note

interface NotesManager {
    suspend fun getAll(): Result<List<Note>>
    suspend fun create(title: String): Result<Note>
    suspend fun get(noteId: Int): Result<Note>
    suspend fun update(noteId: Int, title:String): Result<Note>
    suspend fun delete(noteId: Int): Result<Boolean>
}