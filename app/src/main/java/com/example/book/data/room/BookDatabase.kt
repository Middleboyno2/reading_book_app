package com.example.book.data.room

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.book.data.dto.BookEntity
import com.example.book.data.dto.ColorPresetEntity
import com.example.book.data.dto.FavoriteDirectoryEntity
import com.example.book.data.dto.HistoryEntity

@Database(
    entities = [
        BookEntity::class,
        HistoryEntity::class,
        ColorPresetEntity::class,
        FavoriteDirectoryEntity::class,
    ],
    version = 1,

    exportSchema = true
)
abstract class BookDatabase : RoomDatabase() {
    abstract val dao: BookDao
}

