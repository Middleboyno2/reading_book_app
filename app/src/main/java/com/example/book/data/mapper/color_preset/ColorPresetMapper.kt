package com.example.book.data.mapper.color_preset

import com.example.book.data.dto.ColorPresetEntity
import com.example.book.data.model.ColorPreset

interface ColorPresetMapper {
    suspend fun toColorPresetEntity(colorPreset: ColorPreset, order: Int): ColorPresetEntity

    suspend fun toColorPreset(colorPresetEntity: ColorPresetEntity): ColorPreset
}