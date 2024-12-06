package com.example.book.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class NoteEntity (

    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Sử dụng Long cho id và autoGenerate để tự động tăng giá trị
    val title: String, // Tiêu đề của ghi chú
    val content: String? = null, // Nội dung văn bản (có thể null nếu chỉ có hình ảnh/vẽ)
    val imagePath: String? = null, // Đường dẫn tới hình ảnh (có thể null nếu không có hình ảnh)
    val drawingPath: String? = null, // Đường dẫn tới dữ liệu vẽ (có thể null nếu không có vẽ)
    val audioPath: String? = null, // Đường dẫn tới file âm thanh (có thể null nếu không có âm thanh)
    val createdAt: Long = System.currentTimeMillis(), // Thời gian tạo ghi chú
    val modifiedAt: Long = System.currentTimeMillis() // Thời gian sửa đổi ghi chú lần cuối

)