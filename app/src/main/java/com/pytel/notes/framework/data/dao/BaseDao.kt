package com.pytel.notes.framework.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<in T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg entities: T)

    @Update
    suspend fun update(entity: T)

    @Update
    suspend fun update(vararg entities: T)

    @Delete
    suspend fun delete(entity: T)

}