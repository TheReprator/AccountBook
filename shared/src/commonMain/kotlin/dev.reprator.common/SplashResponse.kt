package dev.reprator.common

data class SplashResponse(val imageList:List<String>, val languageList: List<LanguageResponse>)
data class LanguageResponse(val id: Int, val name: String)

data class DataResponseContainer<T>(val data: T)
data class WrappedResponse(val statusCode: Int, val data: DataResponseContainer<Any>)

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
