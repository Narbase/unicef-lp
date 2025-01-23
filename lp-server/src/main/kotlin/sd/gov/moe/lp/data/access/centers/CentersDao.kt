package sd.gov.moe.lp.data.access.centers

import sd.gov.moe.lp.data.access.utils.BasicDao
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.CentersTable
import kotlin.String
import java.math.BigDecimal
import org.jetbrains.exposed.dao.id.EntityID
import org.joda.time.DateTime

data class Center(
	override val id: UUID?,
	val name: String,
	val lat: BigDecimal,
	val lng: BigDecimal,
	val administrativeUnitId: UUID,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object CentersDao:
	BasicDao<CentersTable, UUID, Center>(CentersTable) {
	override fun toStatement(model: Center, row: UpdateBuilder<Int>) {
		row[table.name] = model.name
		row[table.lat] = model.lat
		row[table.lng] = model.lng
		row[table.administrativeUnitId] = model.administrativeUnitId
	}

	override fun toModel(row: ResultRow): Center {
		return Center(
			id = row[table.id].value,
			name = row[table.name],
			lat = row[table.lat],
			lng = row[table.lng],
			administrativeUnitId = row[table.administrativeUnitId].value,
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

