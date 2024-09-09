package com.example.book.presentation.screen.reader.components.app_bar

import androidx.activity.ComponentActivity
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.book.R
import com.example.book.data.util.OnNavigate
import com.example.book.presentation.components.custom.CustomIconButton
import com.example.book.presentation.main.Screen
import com.example.book.presentation.main.removeDigits
import com.example.book.presentation.main.removeTrailingZero

import com.example.book.presentation.screen.history.data.HistoryEvent
import com.example.book.presentation.screen.library.data.LibraryEvent
import com.example.book.presentation.screen.reader.data.ReaderEvent
import com.example.book.presentation.screen.reader.data.ReaderState
import com.example.book.ui.theme.Colors


/**
 * Reader top bar. Displays title of the book.
 *
 * @param state [ReaderState].
 * @param onNavigate Navigator callback.
 * @param onEvent [ReaderEvent] callback.
 * @param onLibraryUpdateEvent [LibraryEvent] callback.
 * @param onHistoryUpdateEvent [HistoryEvent] callback.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReaderTopBar(
    state: State<ReaderState>,
    onNavigate: OnNavigate,
    onEvent: (ReaderEvent) -> Unit,
    onLibraryUpdateEvent: (LibraryEvent.OnUpdateBook) -> Unit,
    onHistoryUpdateEvent: (HistoryEvent.OnUpdateBook) -> Unit
) {
    val context = LocalContext.current as ComponentActivity
    val progress by remember(state.value.book.progress) {
        derivedStateOf {
            (state.value.book.progress * 100)
                .toDouble()
                .removeDigits(2)
                .removeTrailingZero()
                .dropWhile { it == '-' } + "%"
        }
    }

    TopAppBar(
        navigationIcon = {
            CustomIconButton(
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = R.string.go_back_content_desc,
                disableOnClick = true
            ) {
                onEvent(
                    ReaderEvent.OnGoBack(
                        context,
                        refreshList = {
                            onLibraryUpdateEvent(LibraryEvent.OnUpdateBook(it))
                            onHistoryUpdateEvent(HistoryEvent.OnUpdateBook(it))
                        },
                        navigate = {
                            onNavigate {
                                navigateBack()
                            }
                        }
                    )
                )
            }
        },
        title = {
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    state.value.book.title,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    lineHeight = 20.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    modifier = Modifier
                        .clickable(
                            enabled = !state.value.lockMenu,
                            interactionSource = null,
                            indication = null,
                            onClick = {
                                onEvent(
                                    ReaderEvent.OnGoBack(
                                        context,
                                        refreshList = {
                                            onLibraryUpdateEvent(LibraryEvent.OnUpdateBook(it))
                                            onHistoryUpdateEvent(HistoryEvent.OnUpdateBook(it))
                                        },
                                        navigate = {
                                            onNavigate {
                                                navigate(
                                                    Screen.BookInfo(state.value.book.id),
                                                    useBackAnimation = true,
                                                    saveInBackStack = false
                                                )
                                            }
                                        }
                                    )
                                )
                            }
                        )
                        .basicMarquee(
                            iterations = Int.MAX_VALUE
                        )
                )
                Text(
                    stringResource(
                        id = R.string.read_query,
                        progress
                    ),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        },
        actions = {
            CustomIconButton(
                icon = Icons.Default.Settings,
                contentDescription = R.string.open_reader_settings_content_desc,
                disableOnClick = false,
                enabled = !state.value.lockMenu
            ) {
                onEvent(ReaderEvent.OnShowHideSettingsBottomSheet)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Colors.readerSystemBarsColor
        )
    )
}