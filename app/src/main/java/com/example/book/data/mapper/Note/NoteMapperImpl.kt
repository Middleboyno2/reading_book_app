package com.example.book.data.mapper.Note


import com.example.book.data.dto.NoteEntity

import com.example.book.data.model.Note
import javax.inject.Inject

class NoteMapperImpl @Inject constructor() : NoteMapper {
    override suspend fun toNoteEntity(note: Note):NoteEntity {
        return NoteEntity(
            id = note.id,
            title = note.title,
            content = note.content,
            imagePath = note.imagePath,
            drawingPath = note.drawingPath,
            audioPath = note.audioPath,
            createdAt = note.createdAt,
            modifiedAt = note.modifiedAt
        )
    }

    override suspend fun toNote(note: NoteEntity): Note {
        return Note(
            note.id,
            title = note.title,
            content = note.content,
            imagePath = note.imagePath,
            drawingPath = note.drawingPath,
            audioPath = note.audioPath,
            createdAt = note.createdAt,
            modifiedAt = note.modifiedAt
        )
    }
}