package com.example.trainapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trainapp.data.api.Place
import com.example.trainapp.data.repository.TrainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TrainViewModel(private val repository: TrainRepository) : ViewModel() {

    private val _suggestions = MutableStateFlow<List<Place>>(emptyList())
    val suggestions: StateFlow<List<Place>> = _suggestions

    fun onQueryChanged(query: String) {
        if (query.length < 3) return

        viewModelScope.launch {
            try {
                val results = repository.searchPlaces(query)
                android.util.Log.d("TRAIN_APP", "Nombre de gares reçues : ${results.size}")
                _suggestions.value = results// .value car StateFlow
            } catch (e: Exception) {
                // C'EST ICI QUE LE CRASH SERA CAPTURÉ
                android.util.Log.e("TRAIN_APP", "ERREUR CRITIQUE DANS LE VIEWMODEL", e)
            }
        }
    }
}