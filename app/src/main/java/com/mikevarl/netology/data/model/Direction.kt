package com.mikevarl.netology.data.model


data class Direction constructor(
    var id: String,
    var link: String,
    var title: String,
    var groups: List<Group>,
    var badge: Badge,
) {
    fun getCoursesAmount(): Int {
        var amount = 0
        for (group in groups) {
            amount += group.courses.size
        }
        return amount
    }

}
