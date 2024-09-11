package com.example.book.presentation.screen.about.nested.credits

import android.widget.Toast
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.book.R
import com.example.book.data.util.Constants
import com.example.book.data.util.OnNavigate
import com.example.book.presentation.components.custom.GoBackButton
import com.example.book.presentation.components.custom.collapsibleUntilExitScrollBehaviorWithLazyListState
import com.example.book.presentation.components.custom.customItems
import com.example.book.presentation.main.LocalNavigator

import com.example.book.presentation.screen.about.data.AboutEvent
import com.example.book.presentation.screen.about.data.AboutViewModel
import com.example.book.presentation.screen.about.nested.credits.components.CreditItem

@Composable
fun CreditsScreenRoot() {
    val navigator = LocalNavigator.current
    val aboutViewModel: AboutViewModel = hiltViewModel()

    CreditsScreen(
        onNavigate = { navigator.it() },
        onAboutNavigateEvent = aboutViewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreditsScreen(
    onNavigate: OnNavigate,
    onAboutNavigateEvent: (AboutEvent.OnNavigateToBrowserPage) -> Unit
) {
    val scrollState = TopAppBarDefaults.collapsibleUntilExitScrollBehaviorWithLazyListState()
    val context = LocalContext.current

    Scaffold(
        Modifier
            .fillMaxSize()
            .nestedScroll(scrollState.first.nestedScrollConnection)
            .windowInsetsPadding(WindowInsets.navigationBars),
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(stringResource(id = R.string.credits_option))
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
            customItems(Constants.CREDITS, key = { it.name }) {
                CreditItem(credit = it) {
                    it.website?.let { website ->
                        onAboutNavigateEvent(
                            AboutEvent.OnNavigateToBrowserPage(
                                page = website,
                                context = context,
                                noAppsFound = {
                                    Toast.makeText(
                                        context,
                                        context.getString(R.string.error_no_browser),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            )
                        )
                    }
                }
            }
        }
    }
}







