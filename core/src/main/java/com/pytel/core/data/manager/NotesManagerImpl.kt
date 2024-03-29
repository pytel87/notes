package com.pytel.core.data.manager

import com.pytel.core.data.datasource.NotesCache
import com.pytel.core.data.datasource.NotesLocalDataSource
import com.pytel.core.data.datasource.NotesRemoteDataSource
import com.pytel.core.domain.common.Result
import com.pytel.core.domain.manager.NotesManager
import com.pytel.core.domain.model.Note

class NotesManagerImpl(val notesRemoteDataSource: NotesRemoteDataSource, val notesLocalDataSource: NotesLocalDataSource, val notesCache:NotesCache) : NotesManager {
    override suspend fun getAll(): Result<List<Note>> {
        return when (val localResult = notesLocalDataSource.getAll()) {
            is Result.Success -> localResult
            else -> {
                val result = notesRemoteDataSource.getAll()
                if (result is Result.Success) {
                    notesCache.saveAll(result.data)
                }
                result
            }
        }
    }

    override suspend fun create(title: String): Result<Note> {
        val result = notesRemoteDataSource.create(title)
        if (result is Result.Success){
            notesCache.save(result.data)
        }
        return result
    }

    override suspend fun get(noteId: Int): Result<Note> {
        return  notesLocalDataSource.get(noteId)
    }

    override suspend fun update(noteId: Int, title:String): Result<Note> {
        val result = notesRemoteDataSource.update(noteId,title)
        if (result is Result.Success){
            notesCache.save(result.data)
        }
        return  result
    }

    override suspend fun delete(noteId: Int): Result<Boolean> {
        val result = notesRemoteDataSource.delete(noteId)
        if (result is Result.Success){
            notesCache.remove(noteId)
        }
        return result
    }

}