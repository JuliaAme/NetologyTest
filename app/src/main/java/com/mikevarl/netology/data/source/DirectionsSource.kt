package com.mikevarl.netology.data.source

import com.mikevarl.netology.data.model.Badge
import com.mikevarl.netology.data.model.Direction
import com.mikevarl.netology.data.network.JsonApi

interface DirectionsSource {

    suspend fun getDirections(): List<Direction>

}

/**
 * Основная реализация [DirectionsSource].
 * Делает запрос к серверу с помощью библиотеки retrofit.
 */
class NetworkDirectionsSource : DirectionsSource {
    override suspend fun getDirections(): List<Direction> {
        return JsonApi.retrofitService.getJsonData().data.map { it.toDirection() }
    }
}

/**
 * Реализация [DirectionsSource] без запросов к серверу.
 * Можно использовать для отладки отрисовки или Unit-тестирования.
 */
class MockDirectionsSource : DirectionsSource {
    override suspend fun getDirections(): List<Direction> {
        return listOf(
            mockDirection("1", "TestDirection"),
        )
    }

    private fun mockDirection(id: String, title: String): Direction {
        return Direction(id, "", title, emptyList(), Badge("", "#FFF", "#E04D7E"))
    }
}
