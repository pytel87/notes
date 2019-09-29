package com.pytel.notes.framework.data.remote

import com.pytel.core.data.model.NoteDto
import retrofit2.Call
import retrofit2.http.*

interface NotesApi {

    @GET("notes")
    fun getAll(): Call<List<NoteDto>>

    @POST("notes")
    fun create(@Body title:String): Call<NoteDto>

    @PUT("notes/{id}")
    fun update(@Path("id") id:Int, @Body title:String): Call<NoteDto>

    @DELETE("notes/{id}")
    fun delete(@Path("id") id:Int): Call<Unit>

    @PUT("notes/{id}")
    fun get(@Path("id") id:Int): Call<NoteDto>

}