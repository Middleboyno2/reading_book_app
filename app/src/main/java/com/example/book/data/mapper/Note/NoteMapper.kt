package com.example.book.data.mapper.Note


import com.example.book.data.dto.NoteEntity
import com.example.book.data.model.Note

interface NoteMapper {
    suspend fun toNoteEntity(note: Note): NoteEntity

    suspend fun toNote(noteEntity: NoteEntity): Note
}