package com.example.book.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteDirectoryEntity(
    @PrimaryKey(autoGenerate = false)
    val path: String
)