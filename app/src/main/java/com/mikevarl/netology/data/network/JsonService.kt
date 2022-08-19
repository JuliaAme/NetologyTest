package com.mikevarl.netology.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

/**
 * Ссылка на сервер.
 */
private const val BASE_URL =
    "https://raw.githubusercontent.com/"

/**
 * Объект, позволяющий парсить приходящий с сервера файл в модели приложения.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory()) // Добавляем автоматический адаптер, который связывает поля json файла с полями наших моделей
    .build()

/**
 * Библиотека Retrofit позволяет описывать запросы к серверу с помощью аннотаций к методам.
 * Retrofit сам генерирует классы и методы для запросов.
 * Этот объект нужен для конфигурации (тут мы указываем ссылку на сервер и фабрику для парсинга ответа).
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi)) // используем moshi для парсинга файла
    .baseUrl(BASE_URL)
    .build()

interface JsonService {

    /**
     * GET запрос к серверу. Получает конкретный файл по ссылке.
     */
    @GET("netology-code/rn-task/master/netology.json")
    suspend fun getJsonData(): JsonData

}

/**
 * Статический объект для удобного доступа к сервису.
 * В более объёмном приложении лучше использовать dependency injection.
 */
object JsonApi {
    val retrofitService: JsonService by lazy { retrofit.create(JsonService::class.java) }
}
