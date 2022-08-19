package com.mikevarl.netology.data.network

import com.mikevarl.netology.data.model.Badge
import com.mikevarl.netology.data.model.Direction
import com.mikevarl.netology.data.model.Group

/**
 * Вспомогательные классы, чтобы распарсить исходный json файл с сервера.
 */

/**
 * Мы хотим получить список направлений для отображения, однако в файле нам приходит элемент [data].
 */
data class JsonData constructor(
    val data: List<JsonElement>
)

/**
 * [JsonData] содержит список из пар данных о направлении [direction] и списка групп этого направления [groups]
 */
data class JsonElement constructor(
    val direction: JsonDirection,
    val groups: List<Group>
) {
    /**
     * Метод для преобразования приходящих с сервера данных в удобную для работы нашего приложения модель [Direction]
     */
    fun toDirection() = Direction(
            id = direction.id,
            link = direction.link,
            title = direction.title,
            groups = groups,
            badge = direction.badge
        )
}

data class JsonDirection constructor(
    var id: String,
    var link:String,
    var title: String,
    var badge: Badge,
)
