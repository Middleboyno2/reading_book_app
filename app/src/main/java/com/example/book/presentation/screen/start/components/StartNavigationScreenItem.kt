package com.example.book.presentation.screen.start.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.example.book.presentation.components.custom.CustomAnimatedVisibility

import com.example.book.presentation.screen.start.data.StartNavigationScreen
import com.example.book.presentation.screen.start.data.StartState
import com.example.book.ui.theme.Transitions


/**
 * Navigation item screen. Uses sliding anim.
 */
@Composable
fun StartNavigationScreenItem(
    state: State<StartState>,
    screen: StartNavigationScreen,
    content: LazyListScope.() -> Unit
) {
    val listState = rememberLazyListState()

    CustomAnimatedVisibility(
        visible = state.value.currentScreen == screen,
        enter = if (state.value.useBackAnimation) Transitions.BackSlidingTransitionIn
        else Transitions.SlidingTransitionIn,
        exit = if (state.value.useBackAnimation) Transitions.BackSlidingTransitionOut
        else Transitions.SlidingTransitionOut
    ) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceContainerLow),
            state = listState
        ) {
            content()
        }
    }
}
