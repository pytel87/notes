package com.pytel.notes.data.model

import com.pytel.notes.domain.model.Note

/**
 * Created by Vladimir Skouy on 2019-09-17.
 */
class NoteDto(val id:Int,val title:String) {
    fun mapToDomain():Note = Note(id,title)
}