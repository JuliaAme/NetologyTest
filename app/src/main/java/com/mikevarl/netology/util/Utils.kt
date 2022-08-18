package com.mikevarl.netology.util

import android.util.Log
import kotlinx.coroutines.delay
import java.io.IOException

/**
 * Метод, используемый, чтобы повторно вызывать переданную функцию, если при её выполнении было брошено исключение.
 * В коде используем, чтобы повторять запрос к серверу, если предыдущий не вернул успешный результат.
 * @param block - функция, которую необходимо повторить
 * @param times - максимальное количество повторов
 * @param initialDelay - количество миллисекунд между первым и вторым вызовом [block]
 * @param maxDelay - максимальное количество миллисекунд между повторами [block]
 * @param delayStep - количество миллисекунд, которое прибавляется к [initialDelay] при каждом повторе [block]
 */
suspend fun <T> retry(
    times: Int = Int.MAX_VALUE,
    initialDelay: Long = 100, // 0.1 second
    maxDelay: Long = 1000,    // 1 second
    delayStep: Long = 200,
    block: suspend () -> T
): T {
    var currentDelay = initialDelay
    repeat(times - 1) {
        try {
            Log.d("Retry", "Function retried $it times")
            return block()
        } catch (e: IOException) {
            Log.e("Retry", e.toString())
        }
        delay(currentDelay)
        currentDelay = (currentDelay + delayStep).coerceAtMost(maxDelay)
    }
    return block() // last attempt
}