package sd.gov.moe.lp.data.access.gradeadmins

import sd.gov.moe.lp.data.access.utils.BasicDao
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.GradeAdminsTable
import org.jetbrains.exposed.dao.id.EntityID
import org.joda.time.DateTime

data class GradeAdmin(
	override val id: UUID?,
	val gradeId: UUID,
	val staffId: UUID,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object GradeAdminsDao:
	BasicDao<GradeAdminsTable, UUID, GradeAdmin>(GradeAdminsTable) {
	override fun toStatement(model: GradeAdmin, row: UpdateBuilder<Int>) {
		row[table.gradeId] = model.gradeId
		row[table.staffId] = model.staffId
	}

	override fun toModel(row: ResultRow): GradeAdmin {
		return GradeAdmin(
			id = row[table.id].value,
			gradeId = row[table.gradeId].value,
			staffId = row[table.staffId].value,
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

