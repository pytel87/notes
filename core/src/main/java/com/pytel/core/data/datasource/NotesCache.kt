package com.pytel.core.data.datasource

import com.pytel.core.domain.model.Note

interface NotesCache {

    suspend fun saveAll(notes: List<Note>)

    suspend fun save(note:Note)

    suspend fun isEmpty(): Boolean

    suspend fun clear()

    suspend fun getAll():List<Note>

    suspend fun get(noteId: Int): Note?

    suspend fun remove(noteId: Int)
}