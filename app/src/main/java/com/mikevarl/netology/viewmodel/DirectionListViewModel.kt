package com.mikevarl.netology.viewmodel

import androidx.lifecycle.*
import com.mikevarl.netology.data.model.Direction
import com.mikevarl.netology.data.source.DirectionsSource
import com.mikevarl.netology.data.source.MockDirectionsSource
import kotlinx.coroutines.launch

class DirectionListViewModel(private val directionsSource: DirectionsSource) : ViewModel() {

    private val _directions = MutableLiveData<List<Direction>>()
    val directions: LiveData<List<Direction>> = _directions

    init {
        getDirections()
    }

    private fun getDirections() {
        viewModelScope.launch {
            try {
                _directions.value = directionsSource.getDirections()
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
val DirectionListViewModelFactory = object : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(DirectionListViewModel::class.java) ->
                    DirectionListViewModel(MockDirectionsSource())
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}