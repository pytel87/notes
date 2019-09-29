package com.pytel.notes.framework.data.remote

import com.pytel.core.data.datasource.NotesRemoteDataSource
import com.pytel.core.domain.common.ApiErrorResult
import com.pytel.core.domain.common.ErrorResult
import com.pytel.core.domain.common.Result
import com.pytel.core.domain.common.callSafe
import com.pytel.core.domain.model.Note
import com.pytel.notes.framework.utils.logError
import retrofit2.awaitResponse
import java.io.IOException

class NotesRemoteDataSourceImpl(private val notesApi: NotesApi) : NotesRemoteDataSource {
    override suspend fun getAll(): Result<List<Note>> = callSafe({fetchAll()}, "Can't get notes")

    private suspend fun fetchAll(): Result<List<Note>> {
        val response = notesApi.getAll().awaitResponse()
        return if (response.isSuccessful) {
            val body = response.body()
            body?.let {
                Result.Success(data = body.map { item -> item.mapToDomain() })
            } ?: run {
                Result.Success(data = listOf<Note>())
            }
        } else {
            val errorResult = ApiErrorResult(code = response.code(), errorMessage = response.message())
            logError { "Error getAll [$errorResult]" }
            return Result.Error(ErrorResult(message = response.message()))
        }
    }

    override suspend fun update(noteId: Int, title:String): Result<Note> = callSafe({updateNote(noteId, title)},"Can't update note")

    private suspend fun updateNote(noteId:Int,title: String):Result<Note>{
        val response = notesApi.update(noteId,title).awaitResponse()
        return if (response.isSuccessful) {
            val body = response.body()
            body?.let {
                Result.Success(data = body.mapToDomain())
            } ?: run {
                Result.Error(
                    ErrorResult(
                        response.message(),
                        IOException("Error empty body")
                    )
                )
            }
        } else {
            val errorResult = ApiErrorResult(code = response.code(), errorMessage = response.message())
            logError { "Error update [$errorResult]" }
            return Result.Error(ErrorResult(message = response.message()))
        }
    }

    override suspend fun delete(noteId: Int): Result<Boolean> = callSafe({deleteNote(noteId)},"Can't delete note")

    private suspend fun deleteNote(noteId:Int):Result<Boolean> {
        val response = notesApi.delete(noteId).awaitResponse()
        return if (response.isSuccessful) {
            Result.Success(data = true)
        } else {
            val errorResult = ApiErrorResult(code = response.code(), errorMessage = response.message())
            logError { "Error delete [$errorResult]" }
            return Result.Error(ErrorResult(message = response.message()))
        }
    }

    override suspend fun create(title: String): Result<Note> = callSafe({createNote(title)},"Can't create note")

    private suspend fun createNote(title:String) :Result<Note>{
        val response = notesApi.create(title).awaitResponse()
        return if (response.isSuccessful) {
            val body = response.body()
            body?.let {
                Result.Success(data = body.mapToDomain())
            } ?: run {
                Result.Error(
                    ErrorResult(
                        response.message(),
                        IOException("Error empty body")
                    )
                )
            }
        } else {
            val errorResult = ApiErrorResult(code = response.code(), errorMessage = response.message())
            logError { "Error create [$errorResult]" }
            return Result.Error(ErrorResult(message = response.message()))
        }
    }

    override suspend fun get(noteId: Int): Result<Note> = callSafe({getNote(noteId)},"Can't get note")

    private suspend fun getNote(noteId: Int):Result<Note>{
        val response = notesApi.get(noteId).awaitResponse()
        return if (response.isSuccessful) {
            val body = response.body()
            body?.let {
                Result.Success(data = body.mapToDomain())
            } ?: run {
                Result.Error(
                    ErrorResult(
                        response.message(),
                        IOException("Error empty body")
                    )
                )
            }
        } else {
            val errorResult = ApiErrorResult(code = response.code(), errorMessage = response.message())
            logError { "Error get [$errorResult]" }
            return Result.Error(ErrorResult(message = response.message()))
        }
    }

}