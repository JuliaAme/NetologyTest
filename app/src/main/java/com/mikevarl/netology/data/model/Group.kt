package com.mikevarl.netology.data.model

import com.squareup.moshi.Json

data class Group constructor(
    var id: String,
    var title: String,
    var link: String,
    @Json(name = "items") var courses: List<Course>,
    var badge: Badge,
)
