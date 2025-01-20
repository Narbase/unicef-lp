package sd.gov.moe.lp.common


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.narbase.oss.dto.common.entries.AnswerDto
import com.narbase.oss.dto.common.entries.EntryDto
import com.narbase.oss.dto.common.entries.answerDtoToTypeMap
import com.narbase.oss.dto.common.entries.entryDtoToTypeMap
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*
import sd.gov.moe.lp.common.auth.loggedin.AuthorizedClientData
import sd.gov.moe.lp.util.RuntimeTypeAdapterFactory
import kotlin.reflect.KClass

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
abstract class BasicHandler<out D : Any>(
) {

    suspend fun handle(call: ApplicationCall) {
        val clientData = call.principal<AuthorizedClientData>()
        val dataResponse = process(call, clientData)
        call.respond(dataResponse)
    }

    abstract suspend fun process(call: ApplicationCall, clientData: AuthorizedClientData?): DataResponse<D>


}


abstract class Handler<V : Any, out D : Any>(
    private val requestDtoClass: KClass<V>,
) : BasicHandler<D>() {

    override suspend fun process(call: ApplicationCall, clientData: AuthorizedClientData?): DataResponse<D> {
        val requestDto = call.extractDto()
        return process(requestDto, clientData)

    }

    abstract fun process(requestDto: V, clientData: AuthorizedClientData?): DataResponse<D>


    open suspend fun ApplicationCall.extractDto(): V {
        return try {
            val text = receiveTextWithCorrectEncoding()
            gson.fromJson(text, requestDtoClass.java) ?: throw GsonParsingContentTransformationException()
        } catch (e: UnsupportedMediaTypeException) {
            Gson().fromJson("{}", requestDtoClass.java)
        } catch (e: ContentTransformationException) {
            Gson().fromJson("{}", requestDtoClass.java)
        }
    }

    companion object {
        private val entryTypeAdapterFactory = RuntimeTypeAdapterFactory.of(EntryDto::class.java, "type", true)
            .apply { entryDtoToTypeMap.forEach { this.registerSubtype(it.key.java, it.value) } }
        private val answerTypeAdapterFactory = RuntimeTypeAdapterFactory.of(AnswerDto::class.java, "type", true)
            .apply { answerDtoToTypeMap.forEach { this.registerSubtype(it.key.java, it.value) } }

        val gson = GsonBuilder()
            .registerAdapters()
            .create()


        fun GsonBuilder.registerAdapters() = this
            .registerTypeAdapterFactory(entryTypeAdapterFactory)
            .registerTypeAdapterFactory(answerTypeAdapterFactory)
    }


    class GsonParsingContentTransformationException :
        ContentTransformationException("Cannot transform this request's content to the desired type")
}

