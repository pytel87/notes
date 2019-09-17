package com.pytel.notes.domain.usecase

import com.pytel.notes.domain.common.Result
import com.pytel.notes.domain.manager.NotesManager
import com.pytel.notes.domain.model.Note

/**
 * Created by Vladimir Skouy on 2019-09-17.
 */

class  NotesUseCase(private val notesManager: NotesManager) {

    suspend fun getAllNotes(): Result<List<Note>> {
        return notesManager.getAll()
    }

    suspend fun createNote(title:String):Result<Note>{
        return notesManager.create(title)
    }

    suspend fun deleteNote(noteId:Int):Result<Boolean>{
        return notesManager.delete(noteId)
    }

    suspend fun updateNote(note:Note):Result<Boolean>{
        return notesManager.update(note)
    }

}