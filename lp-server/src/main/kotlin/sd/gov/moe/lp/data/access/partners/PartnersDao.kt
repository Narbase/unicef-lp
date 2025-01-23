package sd.gov.moe.lp.data.access.partners

import sd.gov.moe.lp.data.access.utils.BasicDao
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.PartnersTable
import kotlin.String
import org.jetbrains.exposed.dao.id.EntityID
import org.joda.time.DateTime

data class Partner(
	override val id: UUID?,
	val name: String,
	val stateId: UUID,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object PartnersDao:
	BasicDao<PartnersTable, UUID, Partner>(PartnersTable) {
	override fun toStatement(model: Partner, row: UpdateBuilder<Int>) {
		row[table.name] = model.name
		row[table.stateId] = model.stateId
	}

	override fun toModel(row: ResultRow): Partner {
		return Partner(
			id = row[table.id].value,
			name = row[table.name],
			stateId = row[table.stateId].value,
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

