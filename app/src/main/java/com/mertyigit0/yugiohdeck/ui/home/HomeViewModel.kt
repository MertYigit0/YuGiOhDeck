package com.mertyigit0.yugiohdeck.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertyigit0.yugiohdeck.data.remote.CardDto
import com.mertyigit0.yugiohdeck.data.repository.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: CardRepository
) : ViewModel() {

    var cards by mutableStateOf<List<CardDto>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var searchQuery by mutableStateOf("")
        private set

    val filteredCards: List<CardDto>
        get() {
            val query = searchQuery.trim()
            if (query.isBlank()) return cards
            return cards.filter { card ->
                card.name.contains(query, ignoreCase = true) ||
                        card.id.toString().contains(query)
            }
        }

    init {
        fetchCards()
    }

    fun fetchCards() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                cards = repository.getAllCards()
            } catch (e: Exception) {
                errorMessage = e.message ?: "Unknown error"
            } finally {
                isLoading = false
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        searchQuery = query.trimStart()
    }
}
