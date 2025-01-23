package sd.gov.moe.lp.data.tables.utils

import org.jetbrains.exposed.dao.id.UUIDTable
import sd.gov.moe.lp.data.tables.LoggedTable
import sd.gov.moe.lp.data.tables.createdOnColumn

open class ListTable(name: String = "") : UUIDTable(name),
    LoggedTable {
    val name = text("name")
    override val createdOn = createdOnColumn()
}