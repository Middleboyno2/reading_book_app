package com.example.book.presentation.main

import android.annotation.SuppressLint
import android.database.CursorWindow
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.example.book.presentation.components.bottom_navigation_bar.BottomNavigationBar
import com.example.book.presentation.components.custom_navigation_rail.CustomNavigationRail
import com.example.book.presentation.screen.about.AboutScreenRoot
import com.example.book.presentation.screen.about.nested.credits.CreditsScreenRoot
import com.example.book.presentation.screen.about.nested.license_info.LicenseInfoScreenRoot
import com.example.book.presentation.screen.about.nested.licenses.LicensesScreenRoot
import com.example.book.presentation.screen.book_info.BookInfoScreenRoot
import com.example.book.presentation.screen.browse.BrowseScreenRoot
import com.example.book.presentation.screen.browse.data.BrowseViewModel
import com.example.book.presentation.screen.help.HelpScreenRoot
import com.example.book.presentation.screen.history.HistoryScreenRoot
import com.example.book.presentation.screen.history.data.HistoryViewModel
import com.example.book.presentation.screen.library.LibraryScreenRoot
import com.example.book.presentation.screen.library.data.LibraryViewModel
import com.example.book.presentation.screen.reader.ReaderScreenRoot
import com.example.book.presentation.screen.settings.SettingsScreenRoot
import com.example.book.presentation.screen.settings.data.SettingsViewModel
import com.example.book.presentation.screen.settings.nested.appearance.AppearanceSettingsRoot
import com.example.book.presentation.screen.settings.nested.browse.BrowseSettingsRoot
import com.example.book.presentation.screen.settings.nested.general.GeneralSettingsRoot
import com.example.book.presentation.screen.settings.nested.reader.ReaderSettingsRoot
import com.example.book.presentation.screen.start.StartScreenRoot
import com.example.book.ui.theme.BookStoryTheme
import com.example.book.ui.theme.Transitions
import com.example.book.ui.theme.isDark
import com.example.book.ui.theme.isPureDark
import dagger.hilt.android.AndroidEntryPoint

import java.lang.reflect.Field


@Suppress("unused")
@SuppressLint("DiscouragedPrivateApi")
@AndroidEntryPoint
class Activity : AppCompatActivity() {
    // Initializing all required viewModels on app startup
    private val mainViewModel: MainViewModel by viewModels()
    private val libraryViewModel: LibraryViewModel by viewModels()
    private val historyViewModel: HistoryViewModel by viewModels()
    private val browseViewModel: BrowseViewModel by viewModels()
    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // New Cursor size for Room
        try {
            val field: Field = CursorWindow::class.java.getDeclaredField("sCursorWindowSize")
            field.isAccessible = true
            field.set(null, 100 * 1024 * 1024)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Initializing all variables
        mainViewModel.init(libraryViewModel, settingsViewModel)

        // Splash screen
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !mainViewModel.isReady.value
            }
        }

        // Edge to edge
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val isLoaded = mainViewModel.isReady.collectAsState()
            val state = mainViewModel.state.collectAsState()

            val density = LocalDensity.current
            val imeInsets = WindowInsets.ime
            val focusManager = LocalFocusManager.current
            val isKeyboardVisible by remember {
                derivedStateOf {
                    imeInsets.getBottom(density) > 0
                }
            }

            LaunchedEffect(isKeyboardVisible) {
                if (!isKeyboardVisible) {
                    focusManager.clearFocus()
                }
            }

            if (isLoaded.value) {
                BookStoryTheme(
                    theme = state.value.theme!!,
                    isDark = state.value.darkTheme!!.isDark(),
                    isPureDark = state.value.pureDark!!.isPureDark(this),
                    themeContrast = state.value.themeContrast!!
                ) {
                    NavigationHost(
                        startScreen = if (state.value.showStartScreen!!) Screen.Start
                        else Screen.Library
                    ) {
                        navigation(
                            screens = arrayOf(
                                Screen.Library.getRoute(),
                                Screen.History.getRoute(),
                                Screen.Browse.getRoute()
                            ),
                            bottomBar = {
                                BottomNavigationBar()
                            },
                            navigationRail = {
                                CustomNavigationRail()
                            }
                        ) {
                            // Library
                            composable<Screen.Library>(
                                enterAnim = Transitions.FadeTransitionIn,
                                exitAnim = Transitions.FadeTransitionOut
                            ) {
                                LibraryScreenRoot()
                            }

                            // History
                            composable<Screen.History>(
                                enterAnim = Transitions.FadeTransitionIn,
                                exitAnim = Transitions.FadeTransitionOut
                            ) {
                                HistoryScreenRoot()
                            }

                            // Browse
                            composable<Screen.Browse>(
                                enterAnim = Transitions.FadeTransitionIn,
                                exitAnim = Transitions.FadeTransitionOut
                            ) {
                                BrowseScreenRoot()
                            }
                        }

                        // Book Info
                        composable<Screen.BookInfo> {
                            BookInfoScreenRoot(it)
                        }

                        // Reader
                        composable<Screen.Reader> {
                            ReaderScreenRoot(it)
                        }

                        // Settings
                        composable<Screen.Settings> {
                            SettingsScreenRoot()
                        }

                        // Nested settings categories
                        composable<Screen.Settings.General> {
                            GeneralSettingsRoot()
                        }
                        composable<Screen.Settings.Appearance> {
                            AppearanceSettingsRoot()
                        }
                        composable<Screen.Settings.ReaderSettings> {
                            ReaderSettingsRoot()
                        }
                        composable<Screen.Settings.BrowseSettings> {
                            BrowseSettingsRoot()
                        }

                        // About screen
                        composable<Screen.About> {
                            AboutScreenRoot()
                        }

                        // Nested about categories
                        composable<Screen.About.Licenses> {
                            LicensesScreenRoot()
                        }
                        composable<Screen.About.LicenseInfo> {
                            LicenseInfoScreenRoot(it)
                        }
                        composable<Screen.About.Credits> {
                            CreditsScreenRoot()
                        }

                        // Help screen
                        composable<Screen.Help> {
                            HelpScreenRoot(it)
                        }

                        // Start screen
                        composable<Screen.Start>(enterAnim = Transitions.FadeTransitionIn) {
                            StartScreenRoot()
                        }
                    }
                }
            }
        }
    }
}