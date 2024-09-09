package com.example.book.presentation.components.bottom_navigation_bar

import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.book.data.util.Constants
import com.example.book.data.util.Route
import com.example.book.presentation.main.LocalNavigator


/**
 * Bottom navigation bar, uses default [NavigationBar].
 */
@Composable
fun BottomNavigationBar() {
    var currentScreen: Route? by remember { mutableStateOf(null) }
    val navigator = LocalNavigator.current

    LaunchedEffect(Unit) {
        navigator.currentScreen.collect { route ->
            if (
                Constants.NAVIGATION_ITEMS.any {
                    navigator.run { it.screen.getRoute() } == route
                }
            ) {
                currentScreen = route
            }
        }
    }

    NavigationBar {
        Constants.NAVIGATION_ITEMS.forEach {
            BottomNavigationBarItem(
                item = it,
                isSelected = currentScreen == navigator.run { it.screen.getRoute() }
            ) {
                navigator.navigate(it.screen, false)
            }
        }
    }
}
