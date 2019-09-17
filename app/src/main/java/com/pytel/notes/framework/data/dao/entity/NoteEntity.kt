package com.pytel.notes.framework.data.dao.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pytel.notes.domain.model.Note

@Entity(tableName = "note")
data class NoteEntity(

    @PrimaryKey
    var id: Int = -1,
    var title: String

) {
    fun mapToDomain():Note = Note(id,title)
}

fun Note.mapToEntity():NoteEntity = NoteEntity(id,title)