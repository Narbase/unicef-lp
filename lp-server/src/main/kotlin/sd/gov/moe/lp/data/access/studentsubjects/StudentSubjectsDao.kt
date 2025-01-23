package sd.gov.moe.lp.data.access.studentsubjects

import sd.gov.moe.lp.data.access.utils.BasicDao
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.StudentSubjectsTable
import org.jetbrains.exposed.dao.id.EntityID
import kotlin.Double
import org.joda.time.DateTime
import kotlin.Boolean
import com.narbase.oss.dto.common.forms.AnswersListDto

data class StudentSubject(
	override val id: UUID?,
	val studentId: UUID,
	val subjectId: UUID,
	val progress: Double,
	val completedOn: DateTime?,
	val hasCertificate: Boolean?,
	val feedback: AnswersListDto?,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object StudentSubjectsDao:
	BasicDao<StudentSubjectsTable, UUID, StudentSubject>(StudentSubjectsTable) {
	override fun toStatement(model: StudentSubject, row: UpdateBuilder<Int>) {
		row[table.studentId] = model.studentId
		row[table.subjectId] = model.subjectId
		row[table.progress] = model.progress
		row[table.completedOn] = model.completedOn
		row[table.hasCertificate] = model.hasCertificate
		row[table.feedback] = model.feedback
	}

	override fun toModel(row: ResultRow): StudentSubject {
		return StudentSubject(
			id = row[table.id].value,
			studentId = row[table.studentId].value,
			subjectId = row[table.subjectId].value,
			progress = row[table.progress],
			completedOn = row[table.completedOn],
			hasCertificate = row[table.hasCertificate],
			feedback = row[table.feedback],
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

