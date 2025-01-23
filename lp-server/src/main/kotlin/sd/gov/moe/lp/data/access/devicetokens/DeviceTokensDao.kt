package sd.gov.moe.lp.data.access.devicetokens

import com.narbase.oss.data.access.utils.BasicDaoWithoutDelete
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.DeviceTokensTable
import kotlin.String
import org.jetbrains.exposed.dao.id.EntityID
import org.joda.time.DateTime

data class DeviceToken(
	override val id: UUID?,
	val token: String,
	val clientId: UUID,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object DeviceTokensDao:
	BasicDaoWithoutDelete<DeviceTokensTable, UUID, DeviceToken>(DeviceTokensTable) {
	override fun toStatement(model: DeviceToken, row: UpdateBuilder<Int>) {
		row[table.token] = model.token
		row[table.clientId] = model.clientId
	}

	override fun toModel(row: ResultRow): DeviceToken {
		return DeviceToken(
			id = row[table.id].value,
			token = row[table.token],
			clientId = row[table.clientId].value,
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

