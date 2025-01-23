/*
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2017] - [2022] Narbase Technologies
 * All Rights Reserved.
 * Created by shalaga44
 * On: 22/Nov/2022.
 */


package sd.gov.moe.lp.data.access.utils

import com.narbase.oss.data.access.utils.BasicDaoWithoutDelete
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.*
import sd.gov.moe.lp.data.tables.DeletableTable

abstract class BasicDao<Table, TableKey : Comparable<TableKey>, ModelType : ModelWithId<TableKey>>(
    table: Table
) : BasicDaoWithoutDelete<Table, TableKey, ModelType>(table) where Table : IdTable<TableKey>, Table : DeletableTable {


    context(Transaction)
            open fun delete(id: TableKey) {
        logger.info("delete() called with: id = $id")
        val deleteCode = table.update({ table.id eq id }) { row ->
            row[table.isDeleted] = true
        }
        logger.info("delete() returned: $deleteCode")
    }


}