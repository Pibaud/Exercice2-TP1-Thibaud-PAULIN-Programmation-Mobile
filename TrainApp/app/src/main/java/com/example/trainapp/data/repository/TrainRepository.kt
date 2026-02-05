package com.example.trainapp.data.repository

import com.example.trainapp.data.api.SncfApiService
import com.example.trainapp.data.api.Place

class TrainRepository(private val apiService: SncfApiService) {

    suspend fun searchPlaces(query: String): List<Place> {
        return try {
            val response = apiService.searchPlaces(query)
            android.util.Log.d("TRAIN_APP", "Code HTTP : ${response.code()}")

            if (response.isSuccessful) {
                response.body()?.places?.filter { it.type == "stop_area" } ?: emptyList()
            } else {
                android.util.Log.e("TRAIN_APP", "Erreur API : ${response.errorBody()?.string()}")
                emptyList()
            }
        } catch (e: Exception) {
            android.util.Log.e("TRAIN_APP", "Erreur dans le Repository", e)
            throw e // On renvoie l'erreur au ViewModel pour qu'il la logge aussi
        }
    }
}