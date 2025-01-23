package sd.gov.moe.lp.data.access.studentnongradedassessments

import sd.gov.moe.lp.data.access.utils.BasicDao
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.StudentNonGradedAssessmentsTable
import org.jetbrains.exposed.dao.id.EntityID
import kotlin.Double
import com.narbase.oss.dto.common.forms.AnswersListDto
import org.joda.time.DateTime

data class StudentNonGradedAssessment(
	override val id: UUID?,
	val studentId: UUID,
	val lessonId: UUID,
	val progress: Double,
	val answers: AnswersListDto,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object StudentNonGradedAssessmentsDao:
	BasicDao<StudentNonGradedAssessmentsTable, UUID, StudentNonGradedAssessment>(StudentNonGradedAssessmentsTable) {
	override fun toStatement(model: StudentNonGradedAssessment, row: UpdateBuilder<Int>) {
		row[table.studentId] = model.studentId
		row[table.lessonId] = model.lessonId
		row[table.progress] = model.progress
		row[table.answers] = model.answers
	}

	override fun toModel(row: ResultRow): StudentNonGradedAssessment {
		return StudentNonGradedAssessment(
			id = row[table.id].value,
			studentId = row[table.studentId].value,
			lessonId = row[table.lessonId].value,
			progress = row[table.progress],
			answers = row[table.answers],
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

