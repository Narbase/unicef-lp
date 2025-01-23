package sd.gov.moe.lp.data.access.teachers

import sd.gov.moe.lp.data.access.utils.BasicDao
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.TeachersTable
import sd.gov.moe.lp.dto.common.enums.Gender
import org.jetbrains.exposed.dao.id.EntityID
import org.joda.time.DateTime

data class Teacher(
	override val id: UUID?,
	val gender: Gender,
	val centerId: UUID,
	val staffId: UUID,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object TeachersDao:
	BasicDao<TeachersTable, UUID, Teacher>(TeachersTable) {
	override fun toStatement(model: Teacher, row: UpdateBuilder<Int>) {
		row[table.gender] = model.gender
		row[table.centerId] = model.centerId
		row[table.staffId] = model.staffId
	}

	override fun toModel(row: ResultRow): Teacher {
		return Teacher(
			id = row[table.id].value,
			gender = row[table.gender],
			centerId = row[table.centerId].value,
			staffId = row[table.staffId].value,
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

