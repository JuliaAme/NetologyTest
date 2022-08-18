package com.mikevarl.netology.data

import com.mikevarl.netology.data.model.Badge
import com.mikevarl.netology.data.model.Direction
import com.mikevarl.netology.data.model.Group

data class JsonData constructor(
    val data: List<JsonElement>
)

data class JsonElement constructor(
    val direction: JsonDirection,
    val groups: List<Group>
) {
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
