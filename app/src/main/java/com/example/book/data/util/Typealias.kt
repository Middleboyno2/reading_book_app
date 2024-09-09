package com.example.book.data.util

import android.graphics.Bitmap
import com.example.book.presentation.main.Navigator


typealias CoverImage = Bitmap
typealias Selected = Boolean
typealias ID = Int
typealias Route = String
typealias OnNavigate = (Navigator.() -> Unit) -> Unit
