package dev.reprator.khatabook.datasource.remote.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SplashEntity(val imageList:List<String?>?, val languageList: List<LanguageEntity?>?)

@Serializable
data class LanguageEntity(val id: Int?, val name: String?)

@Serializable
@SerialName("DataResponseContainer")
data class DataResponseContainer<out T>(val data: T?,
                                        val statusCode: Int?,
                                        val message: String?): Response<T>()


@Serializable
abstract class Response<out T>


/*

{
    "statusCode": 200,
    "data": {
    "imageList": [
    "/Users/vikramsingh/Desktop/code/backend/Khatabook/splashFileDirectory/one.png"
    ],
    "languageList": [
    {
        "type": "LanguageModal$DTO",
        "id": 9,
        "name": "English"
    }
    ]
}
}*/
