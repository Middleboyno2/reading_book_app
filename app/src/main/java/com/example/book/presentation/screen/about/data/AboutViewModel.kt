package com.example.book.presentation.screen.about.data

import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.book.presentation.main.launchActivity
import com.example.book.presentation.screen.about.data.AboutEvent
import com.example.book.presentation.screen.about.data.AboutState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
    
) : ViewModel() {

    private val _state = MutableStateFlow(AboutState())
    val state = _state.asStateFlow()

    fun onEvent(event: AboutEvent) {
        when (event) {
            is AboutEvent.OnNavigateToBrowserPage -> {
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




        }
    }
}








