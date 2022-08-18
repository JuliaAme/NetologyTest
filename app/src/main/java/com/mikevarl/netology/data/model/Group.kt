package com.mikevarl.netology.data.model

data class Group constructor(
    var id: String,
    var title: String,
    var link: String,
    var courses: List<Course>,
    var badge: Badge,
)
