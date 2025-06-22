package com.dumanyusuf.springcrudnotes.data.di

import com.dumanyusuf.springcrudnotes.data.remote.NotesApi
import com.dumanyusuf.springcrudnotes.data.repo.NoteRepoImpl
import com.dumanyusuf.springcrudnotes.domain.repo.NotesRepo
import com.dumanyusuf.springcrudnotes.util.Constans
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRetrofit(): NotesApi {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080") // ← Emulator için doğru adres bu!
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NotesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepo(api: NotesApi): NotesRepo {
        return NoteRepoImpl(api)
    }
}
