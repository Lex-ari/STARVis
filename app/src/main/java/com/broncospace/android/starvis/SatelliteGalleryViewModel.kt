package com.broncospace.android.starvis

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.broncospace.android.starvis.spacecraft.SpacecraftItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "SatelliteGalleryViewModel"
class SatelliteGalleryViewModel : ViewModel() {
    private val satelliteRepository = SatelliteRepository.get()
    private val _galleryItems: MutableStateFlow<List<SpacecraftItem>> =
        MutableStateFlow(emptyList())
    val galleryItems: StateFlow<List<SpacecraftItem>>
        get() = _galleryItems.asStateFlow()
    init {
        viewModelScope.launch {
            try {
                val spacecraft = satelliteRepository.getSpacecraft()
                Log.d(TAG, "Spacecraft: $spacecraft")
                _galleryItems.value = spacecraft


                val items = satelliteRepository.fetchSatellites()
                Log.d(TAG, "Items received: $items")



            } catch (ex: Exception) {
                Log.e(TAG, "Failed to fetch gallery items", ex)
            }
        }
    }
}