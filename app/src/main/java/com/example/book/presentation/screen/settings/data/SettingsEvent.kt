@file:OptIn(ExperimentalPermissionsApi::class)

package com.example.book.presentation.screen.settings.data

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.example.book.data.util.ID
import com.example.book.data.util.UIText
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState


@Immutable
sealed class SettingsEvent {
    data class OnGeneralChangeCheckForUpdates(
        val enable: Boolean,
        val activity: ComponentActivity,
        val notificationsPermissionState: PermissionState,
        val onChangeCheckForUpdates: (Boolean) -> Unit
    ) : SettingsEvent()

    data class OnSelectPreviousPreset(
        val onSelected: suspend (name: UIText) -> Unit
    ) : SettingsEvent()

    data class OnSelectNextPreset(
        val onSelected: suspend (name: UIText) -> Unit
    ) : SettingsEvent()

    data class OnSelectColorPreset(val id: ID) : SettingsEvent()
    data class OnDeleteColorPreset(val id: ID) : SettingsEvent()
    data class OnScrollToColorPreset(val scrollTo: Int) : SettingsEvent()
    data class OnUpdateColorPresetTitle(val id: ID, val title: String) : SettingsEvent()
    data class OnShuffleColorPreset(val id: ID) : SettingsEvent()
    data class OnUpdateColorPresetColor(
        val id: ID,
        val backgroundColor: Color?,
        val fontColor: Color?
    ) : SettingsEvent()

    data object OnAddColorPreset : SettingsEvent()
    data class OnReorderColorPresets(val from: Int, val to: Int) : SettingsEvent()
    data object OnConfirmReorderColorPresets : SettingsEvent()
}