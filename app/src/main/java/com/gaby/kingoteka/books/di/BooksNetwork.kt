package com.gaby.kingoteka.books.di

import com.gaby.kingoteka.books.data.BookApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BooksNetwork {


    @get:Provides
    @Singleton
    val apiService: BookApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://us-central1-kingteca-c5900.cloudfunctions.net/app/") // Replace with your API base URL
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(BookApiService::class.java)
    }

}
