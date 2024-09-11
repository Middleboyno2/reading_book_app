package com.example.book.data

import android.app.Application
import androidx.room.Room
import com.example.book.data.room.BookDao
import com.example.book.data.room.BookDatabase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBookDao(app: Application): BookDao {
        return Room.databaseBuilder(
            app,
            BookDatabase::class.java,
            "book_db"
        )
            .allowMainThreadQueries()
            .build()
            .dao
    }
}