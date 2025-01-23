/*
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2017] - [2024] Narbase Technologies
 * All Rights Reserved.
 * Created by shalaga44
 * On: 08/Apr/2024.
 */

package sd.gov.moe.lp.data.access.lists

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import sd.gov.moe.lp.data.models.lists.ListItem
import sd.gov.moe.lp.data.models.utils.ListAndTotal
import sd.gov.moe.lp.data.tables.utils.ListTable
import sd.gov.moe.lp.data.tables.utils.ilike

import java.util.*


interface ModelDBConverter<ModelType> {
    fun toModel(row: ResultRow): ModelType
    fun toStatement(model: ModelType, row: UpdateBuilder<Int>)
}

open class ListItemDao(val listTable: ListTable) : ModelDBConverter<ListItem> {
    context (Transaction)
    fun getList(
        pageNo: Long,
        pageSize: Int,
        searchTerm: String,
    ): ListAndTotal<ListItem> {

        val query = listTable
            .selectAll()

        if (searchTerm.isNotBlank()) {
            query.andWhere {
                (listTable.name ilike "%$searchTerm%")
            }
        }
        val count = query.count()
        val list = query
            .orderBy(listTable.name to SortOrder.ASC)
            .limit(pageSize, pageNo * pageSize)
            .map(::toModel)
        return ListAndTotal(list, count)
    }

    fun get(id: UUID) = listTable
        .selectAll().where { listTable.id eq id }
        .map(::toModel)
        .first()

    fun get(ids: List<UUID>) = listTable
        .selectAll().where { listTable.id inList ids.map { EntityID(it, listTable) } }
        .map(::toModel)

    fun getAll() = listTable.selectAll()
        .map(::toModel)


    override fun toModel(row: ResultRow): ListItem {
        return ListItem(
            row[listTable.id].value,
            row[listTable.name],
        )
    }


    fun create(name: String): UUID {
        val id = listTable.insert {
            it[listTable.name] = name
        } get listTable.id
        return id.value
    }

    context(Transaction)
    fun create(model: ListItem): UUID {
        val id = listTable.insert {
            it[listTable.name] = model.name
        } get listTable.id
        return id.value
    }

    fun createBatch(names: List<String>): List<UUID> {
        val id = listTable.batchInsert(names) {
            this[listTable.name] = it
        }.map { it[listTable.id].value }
        return id
    }

    context (Transaction)
    fun createAndGet(name: String): ListItem {
        val id = create(name)
        return get(id)
    }

    fun update(model: ListItem) {
        listTable.update({
            listTable.id eq model.id
        }) {
            toStatement(model, it)
        }
    }

    override fun toStatement(model: ListItem, row: UpdateBuilder<Int>) {
        row[listTable.name] = model.name
    }

}

fun <T> Result<T>.getOrNullAndPrintStackTrace(): T? {
    return getOrElse {
        System.err.println("Called: getOrNullAndPrintStackTrace")
        it.printStackTrace()
        null
    }
}
