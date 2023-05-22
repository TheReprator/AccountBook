package dev.reprator.common.util

import dev.reprator.common.datasource.remote.modal.DataResponseContainer
import dev.reprator.common.datasource.remote.modal.Response
import dev.reprator.common.datasource.remote.modal.SplashEntity
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.statement.HttpResponseContainer
import io.ktor.client.statement.bodyAsChannel
import io.ktor.client.statement.readBytes
import io.ktor.http.isSuccess
import io.ktor.serialization.suitableCharset
import io.ktor.util.reflect.TypeInfo
import io.ktor.util.reflect.platformType
import io.ktor.utils.io.charsets.Charsets
import io.ktor.utils.io.core.internal.ChunkBuffer
import io.ktor.utils.io.readRemaining
import io.ktor.utils.io.readUTF8Line
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import kotlin.reflect.KClass

val DataTransformationPlugin = createClientPlugin(name = "DataTransformationPlugin") {

    transformResponseBody { response, content, requestedType ->
      val bo = content.readRemaining().readText()

        val apiResponse = if (response.status.isSuccess()) {
            val output = Json.decodeFromString(PolymorphicSerializer(Any::class),bo)
            val op = requestedType.ofInnerClassParameter()
            println("vikramTest::: ${op}")
            //converter.deserialize(context.request.headers.suitableCharset(), op, bo)
        } else {
            throw IllegalArgumentException("not a parsable error")
        }

        Json.decodeFromString<DataResponseContainer<*>>(bo)
        //proceedWith(HttpResponseContainer(info, apiResponse!!))

       // println("vikram :: DataResponseContainer 11111b $op")*/
        null


          /*  if(obj.message == null) {
                HttpResponseContainer(requestedType, obj.data!!)
            } else {
                content
            }*/

            // val tp= Json(JsonConfiguration()).parse(Error.serializer(), it)
        }
        //throw IllegalArgumentException("not a parsable error")

}

private fun TypeInfo.ofInnerClassParameter(): TypeInfo {
    val typeProjection = kotlinType?.arguments?.get(0)
    val kType = typeProjection!!.type!!
    return TypeInfo(kType.classifier as KClass<*>, kType.platformType)
}