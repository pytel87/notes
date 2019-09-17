package com.pytel.notes.framework.data.remote

import com.pytel.notes.data.datasource.NotesRemoteDataSource
import com.pytel.notes.domain.common.ApiErrorResult
import com.pytel.notes.domain.common.ErrorResult
import com.pytel.notes.domain.common.Result
import com.pytel.notes.domain.model.Note
import com.pytel.notes.framework.utils.logError
import retrofit2.awaitResponse
import java.io.IOException

class NotesRemoteDataSourceImpl(private val notesApi: NotesApi) : NotesRemoteDataSource {
    override suspend fun getAll(): Result<List<Note>> {
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

    override suspend fun update(note: Note): Result<Boolean> {
        val response = notesApi.update(note.id,note.title).awaitResponse()
        return if (response.isSuccessful) {
            Result.Success(data = true)
        } else {
            val errorResult = ApiErrorResult(code = response.code(), errorMessage = response.message())
            logError { "Error update [$errorResult]" }
            return Result.Error(ErrorResult(message = response.message()))
        }
    }

    override suspend fun delete(noteId: Int): Result<Boolean> {
        val response = notesApi.delete(noteId).awaitResponse()
        return if (response.isSuccessful) {
            Result.Success(data = true)
        } else {
            val errorResult = ApiErrorResult(code = response.code(), errorMessage = response.message())
            logError { "Error delete [$errorResult]" }
            return Result.Error(ErrorResult(message = response.message()))
        }
    }

    override suspend fun create(title: String): Result<Note> {
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

    override suspend fun get(noteId: Int): Result<Note> {
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