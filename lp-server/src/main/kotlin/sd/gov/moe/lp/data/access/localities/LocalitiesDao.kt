package sd.gov.moe.lp.data.access.localities

import sd.gov.moe.lp.data.access.utils.BasicDao
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.LocalitiesTable
import kotlin.String
import org.jetbrains.exposed.dao.id.EntityID
import org.joda.time.DateTime

data class Locality(
	override val id: UUID?,
	val name: String,
	val stateId: UUID,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object LocalitiesDao:
	BasicDao<LocalitiesTable, UUID, Locality>(LocalitiesTable) {
	override fun toStatement(model: Locality, row: UpdateBuilder<Int>) {
		row[table.name] = model.name
		row[table.stateId] = model.stateId
	}

	override fun toModel(row: ResultRow): Locality {
		return Locality(
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

