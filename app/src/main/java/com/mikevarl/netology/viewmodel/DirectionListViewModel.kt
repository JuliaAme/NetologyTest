package com.mikevarl.netology.viewmodel

import androidx.lifecycle.*
import com.mikevarl.netology.data.model.Direction
import com.mikevarl.netology.data.source.DirectionsSource
import com.mikevarl.netology.data.source.NetworkDirectionsSource
import com.mikevarl.netology.util.retry
import kotlinx.coroutines.launch

class DirectionListViewModel(private val directionsSource: DirectionsSource) : ViewModel() {

    private val _directions = MutableLiveData<List<Direction>>()
    val directions: LiveData<List<Direction>> = _directions

    private val _directionsLoaded = MutableLiveData<Boolean>()
    val directionsLoaded: LiveData<Boolean> = _directionsLoaded

    /**
     * При инициализации ViewModel пытаемся получить направления
     */
    init {
        _directionsLoaded.value = false
        getDirections()
    }

    /**
     * Получаем направления из [directionsSource].
     * Так как запрос по сети может завершиться неуспешно, пытаемся повторять запрос, пока не получим список.
     */
    private fun getDirections() {
        viewModelScope.launch {
            retry {
                _directions.value = directionsSource.getDirections()
                if (_directions.value?.isNotEmpty() == true) _directionsLoaded.value = true
            }
        }
    }
}

/**
 * Этот объект описывает, как создавать [DirectionListViewModel].
 * Мы хотим использовать реализацию [NetworkDirectionsSource] и указываем это здесь.
 * Внутри [MainActivity] вызов "by viewModels { DirectionListViewModelFactory }" будет использовать этот метод.
 * С настроенным Dependency Injection создание объектов может выглядеть по-другому.
 */
@Suppress("UNCHECKED_CAST")
val DirectionListViewModelFactory = object : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(DirectionListViewModel::class.java) ->
                    DirectionListViewModel(NetworkDirectionsSource()) // используем [NetworkDirectionsSource]
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}
