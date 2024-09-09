package com.example.book.presentation.screen.settings.nested.appearance.components.settings

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.book.R
import com.example.book.data.model.ButtonItem
import com.example.book.presentation.main.MainEvent
import com.example.book.presentation.main.MainState
import com.example.book.presentation.screen.settings.components.SegmentedButtonWithTitle
import com.example.book.ui.theme.BookStoryTheme
import com.example.book.ui.theme.SlidingTransition
import com.example.book.ui.theme.Theme
import com.example.book.ui.theme.ThemeContrast
import com.example.book.ui.theme.isDark
import com.example.book.ui.theme.isPureDark


/**
 * Theme Contrast setting.
 * Lets user change theme contrast levels.
 */
@Composable
fun LazyItemScope.ThemeContrastSetting(
    state: State<MainState>,
    onMainEvent: (MainEvent) -> Unit
) {
    val themeContrastTheme = remember { mutableStateOf(state.value.theme!!) }

    LaunchedEffect(state.value.theme) {
        if (themeContrastTheme.value != state.value.theme && state.value.theme != Theme.DYNAMIC) {
            themeContrastTheme.value = state.value.theme!!
        }
    }

    BookStoryTheme(
        theme = themeContrastTheme.value,
        isDark = state.value.darkTheme!!.isDark(),
        isPureDark = state.value.pureDark!!.isPureDark(context = LocalContext.current),
        themeContrast = state.value.themeContrast!!
    ) {
        SlidingTransition(
            modifier = Modifier.animateItem(
                fadeInSpec = null,
                fadeOutSpec = null
            ),
            visible = state.value.theme != Theme.DYNAMIC,
        ) {
            SegmentedButtonWithTitle(
                title = stringResource(id = R.string.theme_contrast_option),
                enabled = state.value.theme != Theme.DYNAMIC,
                buttons = ThemeContrast.entries.map {
                    ButtonItem(
                        id = it.toString(),
                        title = when (it) {
                            ThemeContrast.STANDARD -> stringResource(id = R.string.theme_contrast_standard)
                            ThemeContrast.MEDIUM -> stringResource(id = R.string.theme_contrast_medium)
                            ThemeContrast.HIGH -> stringResource(id = R.string.theme_contrast_high)
                        },
                        textStyle = MaterialTheme.typography.labelLarge,
                        selected = it == state.value.themeContrast
                    )
                }
            ) {
                onMainEvent(
                    MainEvent.OnChangeThemeContrast(
                        it.id
                    )
                )
            }
        }
    }
}