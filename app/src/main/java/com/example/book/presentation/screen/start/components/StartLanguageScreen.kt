package com.example.book.presentation.screen.start.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.book.R
import com.example.book.data.model.ButtonItem
import com.example.book.presentation.components.custom.CategoryTitle
import com.example.book.presentation.components.custom.customItems
import com.example.book.presentation.components.custom_dialog.SelectableDialogItem
import com.example.book.presentation.main.MainEvent


/**
 * Language settings.
 */
fun LazyListScope.startLanguageScreen(
    onMainEvent: (MainEvent) -> Unit,
    languages: List<ButtonItem>
) {
    item {
        Spacer(modifier = Modifier.height(16.dp))
        CategoryTitle(
            title = stringResource(id = R.string.start_language_preferences),
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(12.dp))
    }

    customItems(languages, key = { it.id }) {
        SelectableDialogItem(
            selected = it.selected,
            title = it.title,
            horizontalPadding = 18.dp
        ) {
            onMainEvent(MainEvent.OnChangeLanguage(it.id))
        }
    }

    item {
        Spacer(modifier = Modifier.height(8.dp))
    }
}