package dev.reprator.common.util

import dev.reprator.common.datasource.remote.modal.SplashEntity
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.statement.HttpResponseContainer
import io.ktor.client.statement.bodyAsChannel
import io.ktor.client.statement.readBytes
import io.ktor.utils.io.charsets.Charsets
import io.ktor.utils.io.core.internal.ChunkBuffer
import io.ktor.utils.io.readUTF8Line
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

val DataTransformationPlugin = createClientPlugin(name = "DataTransformationPlugin") {

    transformResponseBody { response, content, requestedType ->
        println("vikram :: DataResponseContainer 1111")


          /*  if(obj.message == null) {
                HttpResponseContainer(requestedType, obj.data!!)
            } else {
                content
            }*/

            // val tp= Json(JsonConfiguration()).parse(Error.serializer(), it)
        }
        //throw IllegalArgumentException("not a parsable error")

}