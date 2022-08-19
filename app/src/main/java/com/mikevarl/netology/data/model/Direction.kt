package com.mikevarl.netology.data.model

/**
 * Направление обучения.
 * Эти данные ([title], количество курсов из [getCoursesAmount], [badge]) показываются в списке на основном экране приложения.
 */
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
