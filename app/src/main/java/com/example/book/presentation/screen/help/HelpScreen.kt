package com.example.book.presentation.screen.help

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.book.R
import com.example.book.data.util.Constants
import com.example.book.data.util.OnNavigate
import com.example.book.presentation.components.custom.GoBackButton
import com.example.book.presentation.components.custom.collapsibleUntilExitScrollBehaviorWithLazyListState
import com.example.book.presentation.components.custom.customItems
import com.example.book.presentation.main.LocalNavigator
import com.example.book.presentation.main.MainEvent

import com.example.book.presentation.main.MainViewModel
import com.example.book.presentation.main.Screen

import com.example.book.presentation.screen.browse.data.BrowseEvent
import com.example.book.presentation.screen.browse.data.BrowseViewModel
import com.example.book.presentation.screen.help.components.HelpItem
import com.example.book.presentation.screen.help.components.items.HelpClickMeNoteItem
import com.example.book.presentation.screen.help.data.HelpEvent
import com.example.book.presentation.screen.help.data.HelpState
import com.example.book.presentation.screen.help.data.HelpViewModel


@Composable
fun HelpScreenRoot(screen: Screen.Help) {
    val navigator = LocalNavigator.current
    val helpViewModel: HelpViewModel = hiltViewModel()
    val browseViewModel: BrowseViewModel = hiltViewModel()
    val mainViewModel: MainViewModel = hiltViewModel()
//    val startViewModel: StartViewModel = hiltViewModel()

    val state = helpViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        helpViewModel.init(screen = screen)
    }

    HelpScreen(
        state = state,
        onNavigate = { navigator.it() },
        onEvent = helpViewModel::onEvent,
        onMainEvent = mainViewModel::onEvent,
        onBrowseEvent = browseViewModel::onEvent
//        onStartEvent = startViewModel::onEvent
    )
}

@OptIn(
    ExperimentalMaterial3Api::class
)
@Composable
private fun HelpScreen(
    state: State<HelpState>,
    onNavigate: OnNavigate,
    onEvent: (HelpEvent) -> Unit,
    onMainEvent: (MainEvent) -> Unit,
    onBrowseEvent: (BrowseEvent) -> Unit,
//    onStartEvent: (StartEvent) -> Unit
) {
    val scrollState = TopAppBarDefaults.collapsibleUntilExitScrollBehaviorWithLazyListState()

    Scaffold(
        Modifier
            .fillMaxSize()
            .nestedScroll(scrollState.first.nestedScrollConnection)
            .windowInsetsPadding(WindowInsets.navigationBars),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(id = R.string.help_screen))
                },
                navigationIcon = {
                    if (!state.value.fromStart) {
                        GoBackButton(onNavigate = onNavigate)
                    }
                },
                actions = {
//                    if (!state.value.fromStart) {
//                        CustomIconButton(
//                            icon = Icons.Default.RestartAlt,
//                            contentDescription = R.string.reset_start_content_desc,
//                            disableOnClick = false
//                        ) {
//                            onStartEvent(StartEvent.OnResetStartScreen)
//                            onMainEvent(MainEvent.OnChangeShowStartScreen(true))
//                            onNavigate {
//                                navigate(Screen.Start, saveInBackStack = false)
//                                clearBackStack()
//                            }
//                        }
//                    }
                },
                scrollBehavior = scrollState.first,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    scrolledContainerColor = MaterialTheme.colorScheme.surfaceContainer
                )
            )
        },
        bottomBar = {
            if (state.value.fromStart) {
                Column {
                    Spacer(modifier = Modifier.height(18.dp))
                    Button(
                        modifier = Modifier
                            .navigationBarsPadding()
                            .padding(bottom = 8.dp)
                            .padding(horizontal = 18.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(100),
                        onClick = {
                            onBrowseEvent(BrowseEvent.OnLoadList)
                            onMainEvent(MainEvent.OnChangeShowStartScreen(false))
                            onNavigate {
                                navigate(Screen.Browse, saveInBackStack = false)
                            }
                        }
                    ) {
                        Text(text = stringResource(id = R.string.done))
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .imePadding(),
            state = scrollState.second,
            contentPadding = PaddingValues(horizontal = 18.dp)
        ) {
            item {
                Spacer(
                    modifier = Modifier
                        .animateItem()
                        .height(16.dp)
                )
            }

            item {
                HelpClickMeNoteItem()
            }

            item {
                Spacer(
                    modifier = Modifier
                        .animateItem()
                        .height(16.dp)
                )
            }

            customItems(Constants.HELP_TIPS, key = { it.title }) { helpTip ->
                HelpItem(
                    helpTip = helpTip,
                    onNavigate = onNavigate,
                    fromStart = state.value.fromStart,
                    onHelpEvent = onEvent
                )
            }

            item {
                Spacer(
                    modifier = Modifier
                        .animateItem()
                        .height(48.dp)
                )
            }
        }
    }
}