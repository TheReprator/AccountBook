package dev.reprator.common.datasource.remote.modal

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SplashEntity(@SerialName("imageList") val imageList:List<String?>?, @SerialName("languageList") val languageList: List<LanguageEntity?>?)

@Serializable
data class LanguageEntity(@SerialName("id") val id: Int?, @SerialName("name") val name: String?)

@Serializable
data class DataResponseContainer(@SerialName("data")  val data: SplashEntity?,
                                    @SerialName("statusCode") val statusCode: Int?,
                                    @SerialName("message")  val message: String?)

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
