package sd.gov.moe.lp.data.access.staffpartners

import com.narbase.oss.data.access.utils.BasicDaoWithoutDelete
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.StaffPartnersTable
import org.jetbrains.exposed.dao.id.EntityID
import org.joda.time.DateTime

data class StaffPartner(
	override val id: UUID?,
	val staffId: UUID,
	val partnerId: UUID,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object StaffPartnersDao:
	BasicDaoWithoutDelete<StaffPartnersTable, UUID, StaffPartner>(StaffPartnersTable) {
	override fun toStatement(model: StaffPartner, row: UpdateBuilder<Int>) {
		row[table.staffId] = model.staffId
		row[table.partnerId] = model.partnerId
	}

	override fun toModel(row: ResultRow): StaffPartner {
		return StaffPartner(
			id = row[table.id].value,
			staffId = row[table.staffId].value,
			partnerId = row[table.partnerId].value,
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

