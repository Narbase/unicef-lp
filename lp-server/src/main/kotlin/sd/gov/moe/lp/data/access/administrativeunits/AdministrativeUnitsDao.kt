package sd.gov.moe.lp.data.access.administrativeunits

import sd.gov.moe.lp.data.access.utils.BasicDao
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.AdministrativeUnitsTable
import kotlin.String
import org.jetbrains.exposed.dao.id.EntityID
import org.joda.time.DateTime

data class AdministrativeUnit(
	override val id: UUID?,
	val name: String,
	val localityId: UUID,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object AdministrativeUnitsDao:
	BasicDao<AdministrativeUnitsTable, UUID, AdministrativeUnit>(AdministrativeUnitsTable) {
	override fun toStatement(model: AdministrativeUnit, row: UpdateBuilder<Int>) {
		row[table.name] = model.name
		row[table.localityId] = model.localityId
	}

	override fun toModel(row: ResultRow): AdministrativeUnit {
		return AdministrativeUnit(
			id = row[table.id].value,
			name = row[table.name],
			localityId = row[table.localityId].value,
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

