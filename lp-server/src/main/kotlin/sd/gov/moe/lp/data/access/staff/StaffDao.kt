package sd.gov.moe.lp.data.access.staff

import sd.gov.moe.lp.data.access.utils.BasicDao
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.StaffTable
import org.jetbrains.exposed.dao.id.EntityID
import kotlin.String
import sd.gov.moe.lp.dto.common.enums.Country
import kotlin.Boolean
import org.joda.time.DateTime

data class Staff(
	override val id: UUID?,
	val clientId: UUID,
	val fullName: String,
	val country: Country,
	val isInactive: Boolean,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object StaffDao:
	BasicDao<StaffTable, UUID, Staff>(StaffTable) {
	override fun toStatement(model: Staff, row: UpdateBuilder<Int>) {
		row[table.clientId] = model.clientId
		row[table.fullName] = model.fullName
		row[table.country] = model.country
		row[table.isInactive] = model.isInactive
	}

	override fun toModel(row: ResultRow): Staff {
		return Staff(
			id = row[table.id].value,
			clientId = row[table.clientId].value,
			fullName = row[table.fullName],
			country = row[table.country],
			isInactive = row[table.isInactive],
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

