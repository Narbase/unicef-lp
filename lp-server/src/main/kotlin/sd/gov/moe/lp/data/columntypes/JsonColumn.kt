package sd.gov.moe.lp.data.columntypes

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.narbase.oss.dto.common.entries.AnswerDto
import com.narbase.oss.dto.common.entries.EntryDto
import com.narbase.oss.dto.common.entries.answerDtoToTypeMap
import com.narbase.oss.dto.common.entries.entryDtoToTypeMap
import sd.gov.moe.lp.core.MultiLingualText
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.statements.api.PreparedStatementApi
import org.postgresql.util.PGobject
import sd.gov.moe.lp.util.RuntimeTypeAdapterFactory
import java.lang.reflect.Type

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */


inline fun <reified T : Any> JsonColumn() = JsonColumn<T>(T::class.java)
class JsonColumn<T : Any>(val type: Type) : ColumnType<T>() {
    val gson = createGson()
    override fun sqlType(): String = "jsonb"


    override fun notNullValueToDB(value: T): Any {
        if (value is String) {
            return value
        }
        return gson.toJson(value)
    }

    override fun setParameter(stmt: PreparedStatementApi, index: Int, value: Any?) {
        val obj = PGobject()
        obj.type = "jsonb"
        obj.value = value as String?
        stmt[index] = obj
    }


    override fun valueFromDB(value: Any): T {
        val json: String = when (value) {
            is String -> value
            is PGobject -> value.value ?: "{}"
            else -> error("Unexpected value for json: $value of ${value::class.qualifiedName}")
        }

        return gson.fromJson(json, type)
    }

}

fun Table.array(name: String): Column<List<String>> =
    registerColumn(name, JsonColumn<List<String>>(object : TypeToken<List<String>>() {}.type))

fun Table.multiLingualText(name: String): Column<MultiLingualText> =
    registerColumn(name, JsonColumn<MultiLingualText>(object : TypeToken<MultiLingualText>() {}.type))


inline fun <reified T : Any> Table.jsonColumn(name: String): Column<T> = registerColumn(name, JsonColumn<T>())


fun createGson(): Gson {

    val entryFactory = RuntimeTypeAdapterFactory
        .of(EntryDto::class.java, "type", true)
        .apply {
            entryDtoToTypeMap.forEach { registerSubtype(it.key.java, it.value) }
        }
    val answerFactory = RuntimeTypeAdapterFactory
        .of(AnswerDto::class.java, "type", true)
        .apply {
            answerDtoToTypeMap.forEach { registerSubtype(it.key.java, it.value) }
        }

    return GsonBuilder()
        .registerTypeAdapterFactory(entryFactory)
        .registerTypeAdapterFactory(answerFactory)
        .create()
}





