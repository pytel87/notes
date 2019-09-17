package com.pytel.notes.framework.di

import androidx.room.Room
import com.pytel.notes.data.datasource.NotesLocalDataSource
import com.pytel.notes.data.datasource.NotesRemoteDataSource
import com.pytel.notes.data.manager.NotesManagerImpl
import com.pytel.notes.domain.manager.NotesManager
import com.pytel.notes.domain.usecase.NotesUseCase
import com.pytel.notes.framework.data.dao.NotesDatabase
import com.pytel.notes.framework.data.local.NotesLocalDataSourceImpl
import com.pytel.notes.framework.data.remote.NOTES_URL
import com.pytel.notes.framework.data.remote.NotesApi
import com.pytel.notes.framework.data.remote.NotesRemoteDataSourceImpl
import com.pytel.notes.framework.ui.notes.NotesViewModel
import com.pytel.notes.framework.ui.splash.SplashViewModel
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

    single<NotesManager> { NotesManagerImpl(notesRemoteDataSource = get()) }
    single<ImageLoader> {GlideImageLoader(context = get())}

}

val databaseModule = module {

    single { Room.databaseBuilder(get(), NotesDatabase::class.java, "notes.db").build() }

    single<NotesLocalDataSource> {
        NotesLocalDataSourceImpl(notesDao = get<NotesDatabase>().notesDao)
    }
}

val useCaseModule: Module = module {
    factory { NotesUseCase(notesManager = get()) }
}

val viewModelModule: Module = module {
    viewModel { SplashViewModel() }
    viewModel { NotesViewModel(notesUseCase = get()) }
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