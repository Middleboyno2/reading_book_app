package com.example.book.presentation.screen.help.data

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.book.presentation.main.Screen
import com.example.book.presentation.main.launchActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class HelpViewModel @Inject constructor(

) : ViewModel() {

    private val _state = MutableStateFlow(HelpState())
    val state = _state.asStateFlow()

    fun onEvent(event: HelpEvent) {
        when (event) {
            is HelpEvent.OnNavigateToBrowserPage -> {
                viewModelScope.launch {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(event.page)
                    )

                    intent.launchActivity(event.context as ComponentActivity) {
                        event.noAppsFound()
                    }
                }
            }

            is HelpEvent.OnSearchInWeb -> {
                viewModelScope.launch {
                    if (event.page.isBlank()) {
                        event.error()
                        return@launch
                    }

                    val intent = Intent()

                    intent.action = Intent.ACTION_WEB_SEARCH
                    intent.putExtra(
                        SearchManager.QUERY,
                        "${event.page.trim()} filetype:txt OR filetype:pdf"
                    )

                    intent.launchActivity(event.context as ComponentActivity) {
                        event.noAppsFound()
                    }
                }
            }
        }
    }

    fun init(screen: Screen.Help) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    fromStart = screen.fromStart
                )
            }
        }
    }
}








