package com.pytel.notes.framework.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.pytel.notes.framework.data.dao.entity.NoteEntity

@Dao
interface NotesDao : BaseDao<NoteEntity> {

    @Query("SELECT * FROM note")
    suspend fun getAll(): List<NoteEntity>
}