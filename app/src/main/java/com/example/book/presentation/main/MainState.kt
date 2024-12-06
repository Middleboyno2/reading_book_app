@file:Suppress("UNCHECKED_CAST")

package com.example.book.presentation.main

import android.os.Build
import android.os.Parcelable
import androidx.annotation.Keep
import androidx.compose.runtime.Immutable
import com.example.book.data.util.Constants
import com.example.book.data.util.DataStoreConstants
import com.example.book.presentation.screen.settings.nested.browse.data.BrowseLayout
import com.example.book.ui.theme.DarkTheme
import com.example.book.ui.theme.PureDark
import com.example.book.ui.theme.Theme
import com.example.book.ui.theme.ThemeContrast
import com.example.book.ui.theme.toDarkTheme
import com.example.book.ui.theme.toPureDark
import com.example.book.ui.theme.toTheme
import com.example.book.ui.theme.toThemeContrast
import kotlinx.parcelize.Parcelize
import com.example.book.presentation.screen.settings.nested.browse.data.BrowseFilesStructure
import com.example.book.presentation.screen.settings.nested.browse.data.BrowseSortOrder
import com.example.book.presentation.screen.settings.nested.browse.data.toBrowseLayout
import com.example.book.presentation.screen.settings.nested.browse.data.toBrowseSortOrder
import com.example.book.presentation.screen.settings.nested.browse.data.toFilesStructure
import com.example.book.presentation.screen.settings.nested.reader.data.ReaderTextAlignment
import com.example.book.presentation.screen.settings.nested.reader.data.toTextAlignment

import java.util.Locale

/**
 * Main State.
 * All app's settings/preferences/permanent-variables are here.
 * Wrapped in SavedStateHandle, so it won't reset.
 */
@Immutable
@Keep
@Parcelize
data class MainState(
    val language: String? = null,
    val theme: Theme? = null,
    val darkTheme: DarkTheme? = null,
    val pureDark: PureDark? = null,
    val themeContrast: ThemeContrast? = null,
    val fontFamily: String? = null,
    val isItalic: Boolean? = null,
    val fontSize: Int? = null,
    val lineHeight: Int? = null,
    val paragraphHeight: Int? = null,
    val paragraphIndentation: Boolean? = null,
    val showStartScreen: Boolean? = null,

    val sidePadding: Int? = null,
    val doubleClickTranslation: Boolean? = null,
    val fastColorPresetChange: Boolean? = null,
    val browseFilesStructure: BrowseFilesStructure? = null,
    val browseLayout: BrowseLayout? = null,
    val browseAutoGridSize: Boolean? = null,
    val browseGridSize: Int? = null,
    val browsePinFavoriteDirectories: Boolean? = null,
    val browseSortOrder: BrowseSortOrder? = null,
    val browseSortOrderDescending: Boolean? = null,
    val browseIncludedFilterItems: List<String>? = null,
    val textAlignment: ReaderTextAlignment? = null,
) : Parcelable {
    companion object {
        /**
         * Initializes [MainState] by given [Map].
         */
        fun initialize(data: Map<String, Any>): MainState {
            DataStoreConstants.apply {
                val language: String = data[LANGUAGE.name] as? String ?: if (
                    Constants.LANGUAGES.any { Locale.getDefault().language.take(2) == it.first }
                ) {
                    Locale.getDefault().language.take(2)
                } else {
                    "en"
                }

                val theme: String = data[THEME.name] as? String ?: if (
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
                ) Theme.DYNAMIC.name else Theme.BLUE.name

                val darkTheme: String = data[DARK_THEME.name] as? String
                    ?: DarkTheme.FOLLOW_SYSTEM.name

                val pureDark: String = data[PURE_DARK.name] as? String
                    ?: PureDark.OFF.name

                val themeContrast: String = data[THEME_CONTRAST.name] as? String
                    ?: ThemeContrast.STANDARD.name

                val showStartScreen: Boolean = data[SHOW_START_SCREEN.name] as? Boolean
                    ?: true

                val fontFamily: String = data[FONT.name] as? String
                    ?: Constants.FONTS[0].id

                val isItalic: Boolean = data[IS_ITALIC.name] as? Boolean
                    ?: false

                val fontSize: Int = data[FONT_SIZE.name] as? Int
                    ?: 16

                val lineHeight: Int = data[LINE_HEIGHT.name] as? Int
                    ?: 4

                val paragraphHeight: Int = data[PARAGRAPH_HEIGHT.name] as? Int
                    ?: 8

                val paragraphIndentation: Boolean = data[PARAGRAPH_INDENTATION.name] as? Boolean
                    ?: false



                val sidePadding: Int = data[SIDE_PADDING.name] as? Int
                    ?: 6

                val doubleClickTranslation: Boolean =
                    data[DOUBLE_CLICK_TRANSLATION.name] as? Boolean
                        ?: false

                val fastColorPresetChange: Boolean =
                    data[FAST_COLOR_PRESET_CHANGE.name] as? Boolean
                        ?: true

                val browseFilesStructure: String = data[BROWSE_FILES_STRUCTURE.name] as? String
                    ?: BrowseFilesStructure.DIRECTORIES.name

                val browseLayout: String = data[BROWSE_LAYOUT.name] as? String
                    ?: BrowseLayout.LIST.name

                val browseAutoGridSize: Boolean =
                    data[BROWSE_AUTO_GRID_SIZE.name] as? Boolean
                        ?: true

                val browseGridSize: Int =
                    data[BROWSE_GRID_SIZE.name] as? Int
                        ?: 0

                val browsePinFavoriteDirectories: Boolean =
                    data[BROWSE_PIN_FAVORITE_DIRECTORIES.name] as? Boolean
                        ?: true

                val browseSortOrder: String =
                    data[BROWSE_SORT_ORDER.name] as? String
                        ?: BrowseSortOrder.LAST_MODIFIED.name

                val browseSortOrderDescending: Boolean =
                    data[BROWSE_SORT_ORDER_DESCENDING.name] as? Boolean
                        ?: true

                val browseIncludedFilterItems =
                    (data[BROWSE_INCLUDED_FILTER_ITEMS.name] as? Set<String>)?.toList()
                        ?: emptyList()

                val textAlignment = data[TEXT_ALIGNMENT.name] as? String
                    ?: ReaderTextAlignment.START.name

                return MainState(
                    language = language,
                    theme = theme.toTheme(),
                    darkTheme = darkTheme.toDarkTheme(),
                    pureDark = pureDark.toPureDark(),
                    themeContrast = themeContrast.toThemeContrast(),
                    showStartScreen = showStartScreen,
                    fontFamily = fontFamily,
                    isItalic = isItalic,
                    fontSize = fontSize,
                    lineHeight = lineHeight,
                    paragraphHeight = paragraphHeight,
                    paragraphIndentation = paragraphIndentation,
                    sidePadding = sidePadding,
                    doubleClickTranslation = doubleClickTranslation,
                    fastColorPresetChange = fastColorPresetChange,
                    browseFilesStructure = browseFilesStructure.toFilesStructure(),
                    browseLayout = browseLayout.toBrowseLayout(),
                    browseAutoGridSize = browseAutoGridSize,
                    browseGridSize = browseGridSize,
                    browsePinFavoriteDirectories = browsePinFavoriteDirectories,
                    browseSortOrder = browseSortOrder.toBrowseSortOrder(),
                    browseSortOrderDescending = browseSortOrderDescending,
                    browseIncludedFilterItems = browseIncludedFilterItems,
                    textAlignment = textAlignment.toTextAlignment()
                )
            }
        }
    }
}