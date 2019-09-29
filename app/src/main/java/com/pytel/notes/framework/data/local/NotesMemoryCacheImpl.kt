package com.pytel.notes.framework.data.local

import com.pytel.core.data.datasource.NotesCache
import com.pytel.core.domain.model.Note

class NotesMemoryCacheImpl : NotesCache {
    override suspend fun remove(noteId: Int) {
        cache.remove(noteId)
    }

    override suspend fun save(note: Note) {
        cache[note.id] = note
    }

    private val cache: HashMap<Int, Note> = LinkedHashMap()

    override suspend fun get(noteId: Int): Note? {
        return cache[noteId]
    }
    
    override suspend fun saveAll(notes: List<Note>) {
        notes.forEach { note ->
            cache[note.id] = note
        }
    }

    override suspend fun clear() {
        cache.clear()
    }

    override suspend fun isEmpty(): Boolean {
        return cache.isEmpty()
    }

    override suspend fun getAll() = cache.values.toList()

}