package com.gaby.kingoteka.books.di

import com.gaby.kingoteka.books.data.BookApiService
import com.gaby.kingoteka.movies.data.MovieApiService
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
    val bookapiService: BookApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://us-central1-kingteca-c5900.cloudfunctions.net/app/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(BookApiService::class.java)
    }


}
