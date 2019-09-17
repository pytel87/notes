package com.pytel.notes.data.manager

import com.pytel.notes.data.datasource.NotesRemoteDataSource
import com.pytel.notes.domain.common.Result
import com.pytel.notes.domain.manager.NotesManager
import com.pytel.notes.domain.model.Note

class NotesManagerImpl(private val notesRemoteDataSource: NotesRemoteDataSource) : NotesManager {
    override suspend fun getAll(): Result<List<Note>> {
        return notesRemoteDataSource.getAll()
    }

    override suspend fun create(title: String): Result<Note> {
        return notesRemoteDataSource.create(title)
    }

    override suspend fun get(noteId: Int): Result<Note> {
        return  notesRemoteDataSource.get(noteId)
    }

    override suspend fun update(note: Note): Result<Boolean> {
        return  notesRemoteDataSource.update(note)
    }

    override suspend fun delete(noteId: Int): Result<Boolean> {
        return notesRemoteDataSource.delete(noteId)
    }

}