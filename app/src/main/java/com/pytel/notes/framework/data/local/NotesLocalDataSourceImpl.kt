package com.pytel.notes.framework.data.local

import com.pytel.notes.data.datasource.NotesLocalDataSource
import com.pytel.notes.framework.data.dao.NotesDao

/**
 * Created by Vladimir Skouy on 2019-09-17.
 */

class NotesLocalDataSourceImpl(private val notesDao:NotesDao) : NotesLocalDataSource{

}