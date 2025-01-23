package sd.gov.moe.lp.data.access.clients

import com.narbase.oss.data.access.utils.BasicDaoWithoutDelete
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.joda.time.DateTime
import sd.gov.moe.lp.common.auth.basic.PasswordEncoder
import sd.gov.moe.lp.data.access.utils.ModelWithId
import sd.gov.moe.lp.data.tables.ClientsTable
import java.util.*


/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
data class Client(
    override val id: UUID?,
    val username: String,
    val passwordHash: String,
    val lastLogin: DateTime?,
    val createdOn: DateTime?,
) : ModelWithId<UUID>

object ClientsDao :
    BasicDaoWithoutDelete<ClientsTable, UUID, Client>(ClientsTable) {
    override fun toStatement(model: Client, row: UpdateBuilder<Int>) {
        row[table.username] = model.username
        row[table.passwordHash] = model.passwordHash
        row[table.lastLogin] = model.lastLogin
    }

    override fun filterWithSearchTerm(query: Query, searchTerm: String) {
        TODO("Not yet implemented")
    }

    fun create(username: String, password: String): UUID {
        val id = ClientsTable.insert {
            it[ClientsTable.username] = username
            it[passwordHash] = PasswordEncoder().encode(password)
        } get ClientsTable.id
        return id.value
    }

    fun update(id: UUID, username: String?, password: String?) {
        ClientsTable.update({ ClientsTable.id eq id }) { row ->
            username?.let { row[ClientsTable.username] = it }
            password?.let { row[ClientsTable.username] = PasswordEncoder().encode(it) }
        }
    }

    fun getClients(pageNo: Long, pageSize: Int): List<Client> {
        return ClientsTable
            .selectAll()
            .limit(pageSize, pageNo * pageSize)
            .map(::toModel)
    }

    fun getByUsername(username: String) = ClientsTable
        .selectAll().where { ClientsTable.username eq username }
        .map(::toModel)
        .first()

    fun updateLastLogin(id: UUID) {
        ClientsTable.update({ (ClientsTable.id eq id) }) {
            it[lastLogin] = DateTime.now()
        }
    }

    override fun toModel(row: ResultRow): Client {
        return Client(
            id = row[table.id].value,
            username = row[table.username],
            passwordHash = row[table.passwordHash],
            lastLogin = row[table.lastLogin],
            createdOn = row[table.createdOn],
        )
    }

}