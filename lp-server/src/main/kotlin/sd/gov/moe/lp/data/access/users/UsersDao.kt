package sd.gov.moe.lp.data.access.users

import sd.gov.moe.lp.data.models.users.User
import sd.gov.moe.lp.data.models.users.UserId
import sd.gov.moe.lp.data.tables.ClientsTable
import sd.gov.moe.lp.data.tables.StaffTable
import sd.gov.moe.lp.data.tables.utils.toEntityId
import org.jetbrains.exposed.sql.*
import java.util.*

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
object UsersDao {

    fun create(clientId: UUID, fullName: String, callingCode: String, localPhone: String): UserId {
        val id = StaffTable.insert {
            it[StaffTable.clientId] = clientId.toEntityId(ClientsTable)
            it[StaffTable.fullName] = fullName
            it[StaffTable.callingCode] = callingCode
            it[StaffTable.localPhone] = localPhone
        } get StaffTable.id
        return UserId(id.value)
    }

    fun update(clientId: UUID, fullName: String?, callingCode: String?, localPhone: String?) {
        StaffTable.update({ StaffTable.clientId eq clientId }) { row ->
            fullName?.let { row[StaffTable.fullName] = it }
            callingCode?.let { row[StaffTable.callingCode] = it }
            localPhone?.let { row[StaffTable.localPhone] = it }
        }
    }

    fun update(id: UserId, fullName: String?, callingCode: String?, localPhone: String?) {
        StaffTable.update({ StaffTable.id eq id.value }) { row ->
            fullName?.let { row[StaffTable.fullName] = it }
            callingCode?.let { row[StaffTable.callingCode] = it }
            localPhone?.let { row[StaffTable.localPhone] = it }
        }
    }

    fun get(id: UserId) = StaffTable
        .selectAll().where { StaffTable.id eq id.value }
        .map(::toModel)
        .first()

    fun get(clientId: UUID) = StaffTable
        .selectAll().where { StaffTable.clientId eq clientId }
        .map(::toModel)
        .first()


    fun toModel(row: ResultRow): User {
        return User(
            UserId(row[StaffTable.id].value),
            row[StaffTable.createdOn],
            row[StaffTable.clientId].value,
            row[StaffTable.fullName],
            row[StaffTable.callingCode],
            row[StaffTable.localPhone],
            row[StaffTable.isInactive],
            row[StaffTable.isDeleted],
        )
    }

}

