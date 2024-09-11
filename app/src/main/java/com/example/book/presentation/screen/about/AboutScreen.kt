package com.example.book.presentation.screen.about

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.book.R
import com.example.book.data.util.OnNavigate
import com.example.book.presentation.components.custom.GoBackButton
import com.example.book.presentation.components.custom.collapsibleUntilExitScrollBehaviorWithLazyListState
import com.example.book.presentation.main.LocalNavigator
import com.example.book.presentation.main.Screen
import com.example.book.presentation.screen.about.components.AboutItem

import com.example.book.presentation.screen.about.components.badges.AboutBadges
import com.example.book.presentation.screen.about.data.AboutEvent
import com.example.book.presentation.screen.about.data.AboutState
import com.example.book.presentation.screen.about.data.AboutViewModel

@Composable
fun AboutScreenRoot() {
    val navigator = LocalNavigator.current
    val aboutViewModel: AboutViewModel = hiltViewModel()

    val state = aboutViewModel.state.collectAsState()

    AboutScreen(
        state = state,
        onNavigate = { navigator.it() },
        onEvent = aboutViewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AboutScreen(
    state: State<AboutState>,
    onNavigate: OnNavigate,
    onEvent: (AboutEvent) -> Unit
) {
    val context = LocalContext.current
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
                    Text(stringResource(id = R.string.about_screen))
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
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painterResource(id = R.drawable.app_icon_monochrome),
                        contentDescription = stringResource(id = R.string.app_icon_content_desc),
                        modifier = Modifier
                            .padding(14.dp)
                            .size(120.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(36.dp))
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            }

            item {
                AboutItem(
                    title = stringResource(id = R.string.app_version_option),
                    description = buildAnnotatedString {
                        append(
                            stringResource(
                                id = R.string.app_version_option_desc_1,
                                context.getString(R.string.app_version)
                            )
                        )
                        append("\n")
                        append(stringResource(id = R.string.app_version_option_desc_2))
                    },
                    showLoading = state.value.updateLoading
                ) {

                }

            }

            item {
                AboutItem(
                    title = stringResource(id = R.string.report_bug_option),
                    description = null
                ) {
                    onEvent(
                        AboutEvent.OnNavigateToBrowserPage(
                            context.getString(R.string.issues_page),
                            context,
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



            item {
                AboutItem(
                    title = stringResource(id = R.string.licenses_option),
                    description = null
                ) {
                    onNavigate {
                        navigate(Screen.About.Licenses)
                    }
                }
            }

            item {
                AboutItem(
                    title = stringResource(id = R.string.credits_option),
                    description = null
                ) {
                    onNavigate {
                        navigate(Screen.About.Credits)
                    }
                }
            }

            item {
                AboutItem(
                    title = stringResource(id = R.string.support_option),
                    description = null
                ) {
                    onEvent(
                        AboutEvent.OnNavigateToBrowserPage(
                            context.getString(R.string.support_page),
                            context,
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

            item {
                AboutBadges(onEvent = onEvent)
            }
        }
    }
}