package com.example.book.data.mapper.history

import com.example.book.data.dto.HistoryEntity
import com.example.book.data.model.History

interface HistoryMapper {
    suspend fun toHistoryEntity(history: History): HistoryEntity

    suspend fun toHistory(historyEntity: HistoryEntity): History
}