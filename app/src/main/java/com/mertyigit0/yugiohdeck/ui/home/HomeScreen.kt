package com.mertyigit0.yugiohdeck.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.mertyigit0.yugiohdeck.data.remote.CardDto
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage



@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onCardClick: (Int) -> Unit,
    onMyDeckClick: () -> Unit
) {
    val cards = viewModel.filteredCards
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage
    val searchQuery = viewModel.searchQuery

    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),


    ) {

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { viewModel.onSearchQueryChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            label = { Text("Search Cards") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondary,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                disabledContainerColor = MaterialTheme.colorScheme.secondary,
                errorContainerColor = MaterialTheme.colorScheme.secondary
            )
        )

        // MyDeck Button
        Button(
            onClick = onMyDeckClick,
            modifier = Modifier
                .align(Alignment.End)
                .padding(8.dp)
        ) {
            Text("My Deck")
        }

        // Content
        when {
            isLoading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            errorMessage != null -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(errorMessage)
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = { viewModel.fetchCards() }) {
                            Text("Retry")
                        }
                    }
                }
            }
            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(8.dp)
                ) {
                    items(cards) { card ->
                        CardItem(card = card, onClick = { onCardClick(card.id) })
                    }
                }
            }
        }
    }
}

@Composable
fun CardItem(card: CardDto, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            AsyncImage(
                model = card.images.firstOrNull()?.imageUrl ?: "",
                contentDescription = card.name,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Text(
                card.name,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}


