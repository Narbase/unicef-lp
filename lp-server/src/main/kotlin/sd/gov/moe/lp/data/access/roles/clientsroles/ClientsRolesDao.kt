package sd.gov.moe.lp.data.access.roles.clientsroles

import com.narbase.oss.data.access.utils.BasicDaoWithoutDelete
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.roles.ClientsRolesTable
import org.jetbrains.exposed.dao.id.EntityID
import org.joda.time.DateTime

data class ClientsRole(
	override val id: UUID?,
	val clientId: UUID,
	val dynamicRoleId: UUID,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object ClientsRolesDao:
	BasicDaoWithoutDelete<ClientsRolesTable, UUID, ClientsRole>(ClientsRolesTable) {
	override fun toStatement(model: ClientsRole, row: UpdateBuilder<Int>) {
		row[table.clientId] = model.clientId
		row[table.dynamicRoleId] = model.dynamicRoleId
	}

	override fun toModel(row: ResultRow): ClientsRole {
		return ClientsRole(
			id = row[table.id].value,
			clientId = row[table.clientId].value,
			dynamicRoleId = row[table.dynamicRoleId].value,
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

