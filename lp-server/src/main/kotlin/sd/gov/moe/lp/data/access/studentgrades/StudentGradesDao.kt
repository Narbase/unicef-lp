package sd.gov.moe.lp.data.access.studentgrades

import sd.gov.moe.lp.data.access.utils.BasicDao
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.StudentGradesTable
import org.jetbrains.exposed.dao.id.EntityID
import kotlin.Double
import org.joda.time.DateTime

data class StudentGrade(
	override val id: UUID?,
	val studentId: UUID,
	val gradeId: UUID,
	val progress: Double,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object StudentGradesDao:
	BasicDao<StudentGradesTable, UUID, StudentGrade>(StudentGradesTable) {
	override fun toStatement(model: StudentGrade, row: UpdateBuilder<Int>) {
		row[table.studentId] = model.studentId
		row[table.gradeId] = model.gradeId
		row[table.progress] = model.progress
	}

	override fun toModel(row: ResultRow): StudentGrade {
		return StudentGrade(
			id = row[table.id].value,
			studentId = row[table.studentId].value,
			gradeId = row[table.gradeId].value,
			progress = row[table.progress],
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

