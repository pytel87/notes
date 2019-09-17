package com.pytel.notes.framework.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pytel.notes.framework.data.dao.entity.NoteEntity


@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

    abstract val notesDao: NotesDao

}