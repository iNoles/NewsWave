package com.jonathansteele.news

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun FilterTabs(
    tabState: MutableState<TabState>,
    listState: LazyListState,
) {
    val coroutineScope = rememberCoroutineScope()

    Surface(
        tonalElevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        SecondaryScrollableTabRow(selectedTabIndex = TabState.entries.indexOf(tabState.value)) {
            TabState.entries.forEach { tab ->
                Tab(
                    selected = tabState.value == tab,
                    onClick = {
                        if (tabState.value != tab) {
                            tabState.value = tab
                            coroutineScope.launch { listState.scrollToItem(0) }
                        }
                    },
                    text = { Text(tab.name) },
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}
