package dev.reprator.common.datasource.remote.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SplashEntity(val imageList:List<String?>?, val languageList: List<LanguageEntity>?)

@Serializable
data class LanguageEntity(val id: Int?, val name: String?)

@Serializable
data class DataResponseContainer<T>(@SerialName("data")  val data: T,
                                    @SerialName("statusCode") val statusCode: Int,
                                    @SerialName("message")  val message: String)

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
