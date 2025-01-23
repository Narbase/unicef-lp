package sd.gov.moe.lp.data.access.subjectgrades

import sd.gov.moe.lp.data.access.utils.BasicDao
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.SubjectGradesTable
import org.jetbrains.exposed.dao.id.EntityID
import sd.gov.moe.lp.dto.common.enums.SubjectEnrollmentType
import org.joda.time.DateTime

data class SubjectGrade(
	override val id: UUID?,
	val gradeId: UUID,
	val subjectId: UUID,
	val enrollmentType: SubjectEnrollmentType,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object SubjectGradesDao:
	BasicDao<SubjectGradesTable, UUID, SubjectGrade>(SubjectGradesTable) {
	override fun toStatement(model: SubjectGrade, row: UpdateBuilder<Int>) {
		row[table.gradeId] = model.gradeId
		row[table.subjectId] = model.subjectId
		row[table.enrollmentType] = model.enrollmentType
	}

	override fun toModel(row: ResultRow): SubjectGrade {
		return SubjectGrade(
			id = row[table.id].value,
			gradeId = row[table.gradeId].value,
			subjectId = row[table.subjectId].value,
			enrollmentType = row[table.enrollmentType],
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

