package com.pytel.notes.framework.di

import androidx.room.Room
import com.pytel.core.data.datasource.NotesCache
import com.pytel.core.data.datasource.NotesLocalDataSource
import com.pytel.core.data.datasource.NotesRemoteDataSource
import com.pytel.core.data.manager.NotesManagerImpl
import com.pytel.core.domain.manager.NotesManager
import com.pytel.core.domain.usecase.NoteUseCase
import com.pytel.core.domain.usecase.NotesUseCase
import com.pytel.notes.ui.base.CoroutineContextProvider
import com.pytel.notes.framework.data.dao.NotesDatabase
import com.pytel.notes.framework.data.local.NotesLocalDataSourceImpl
import com.pytel.notes.framework.data.local.NotesMemoryCacheImpl
import com.pytel.notes.framework.data.remote.NOTES_URL
import com.pytel.notes.framework.data.remote.NotesApi
import com.pytel.notes.framework.data.remote.NotesRemoteDataSourceImpl
import com.pytel.notes.ui.note.NoteViewModel
import com.pytel.notes.ui.notes.NotesViewModel
import com.pytel.notes.framework.utils.GlideImageLoader
import com.pytel.notes.framework.utils.ImageLoader
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val apiServiceModule = module {

    single { provideRetrofit() }

    single { provideNotesApi(retrofit = get()) }

    single<NotesRemoteDataSource>{
        NotesRemoteDataSourceImpl(notesApi = get())
    }


}

val appModule = module {

    single<NotesManager> { NotesManagerImpl(notesCache = get(),notesRemoteDataSource = get(),notesLocalDataSource = get()) }
    single<ImageLoader> {GlideImageLoader(context = get())}
    single<NotesCache> {NotesMemoryCacheImpl()}

}

val databaseModule = module {

    single { Room.databaseBuilder(get(), NotesDatabase::class.java, "notes.db").build() }

    single<NotesLocalDataSource> {
        NotesLocalDataSourceImpl(notesCache = get())
    }
}

val useCaseModule: Module = module {
    factory { NotesUseCase(notesManager = get()) }
    factory { NoteUseCase(notesManager = get()) }
}

val viewModelModule: Module = module {
    viewModel { NotesViewModel(notesUseCase = get(),contextProvider = CoroutineContextProvider()) }
    viewModel { NoteViewModel(noteUseCase = get()) }
}

private fun provideNotesApi(retrofit: Retrofit): NotesApi {
    return retrofit.create(NotesApi::class.java)
}

private fun provideRetrofit(): Retrofit {
    val clientBuilder = OkHttpClient.Builder()

    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    clientBuilder.interceptors().add(logging)

    return Retrofit.Builder()
        .baseUrl(NOTES_URL)
        .client(clientBuilder.build())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}