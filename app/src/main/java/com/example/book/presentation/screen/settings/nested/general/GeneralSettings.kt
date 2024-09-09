package com.example.book.presentation.screen.settings.nested.general

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.book.R
import com.example.book.data.util.OnNavigate
import com.example.book.presentation.components.custom.GoBackButton
import com.example.book.presentation.components.custom.collapsibleUntilExitScrollBehaviorWithLazyListState
import com.example.book.presentation.main.LocalNavigator
import com.example.book.presentation.main.MainEvent
import com.example.book.presentation.main.MainState

import com.example.book.presentation.screen.settings.data.SettingsEvent
import com.example.book.presentation.screen.settings.data.SettingsViewModel
import com.example.book.presentation.screen.settings.nested.general.components.GeneralSettingsCategory
import com.example.book.presentation.main.MainViewModel

@Composable
fun GeneralSettingsRoot() {
    val navigator = LocalNavigator.current
    val settingsViewModel: SettingsViewModel = hiltViewModel()
    val mainViewModel: MainViewModel = hiltViewModel()

    val state = mainViewModel.state.collectAsState()

    GeneralSettings(
        state = state,
        onNavigate = { navigator.it() },
        onSettingsEvent = settingsViewModel::onEvent,
        onMainEvent = mainViewModel::onEvent
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GeneralSettings(
    state: State<MainState>,
    onNavigate: OnNavigate,
    onSettingsEvent: (SettingsEvent) -> Unit,
    onMainEvent: (MainEvent) -> Unit
) {
    val scrollState = TopAppBarDefaults.collapsibleUntilExitScrollBehaviorWithLazyListState()

    Scaffold(
        Modifier
            .fillMaxSize()
            .nestedScroll(scrollState.first.nestedScrollConnection)
            .windowInsetsPadding(WindowInsets.navigationBars),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(stringResource(id = R.string.general_settings))
                },
                navigationIcon = {
                    GoBackButton(onNavigate = onNavigate)
                },
                scrollBehavior = scrollState.first,
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    scrolledContainerColor = MaterialTheme.colorScheme.surfaceContainer
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding()),
            state = scrollState.second
        ) {
            GeneralSettingsCategory(
                state = state,
                onMainEvent = onMainEvent,
                onSettingsEvent = onSettingsEvent
            )
        }
    }
}