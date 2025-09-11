package com.mertyigit0.yugiohdeck.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
@Composable
fun HomeScreen(
    onCardClick: (Int) -> Unit,
    onMyDeckClick: () -> Unit
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text("Home Screen")
        Button(onClick = { onCardClick(123) }) {
            Text("Go to Detail")
        }
        Button(onClick = onMyDeckClick) {
            Text("Go to My Deck")
        }
    }
}
