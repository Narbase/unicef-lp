package sd.gov.moe.lp.data.access.nongradedassessments

import sd.gov.moe.lp.data.access.utils.BasicDao
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.NonGradedAssessmentsTable
import org.jetbrains.exposed.dao.id.EntityID
import com.narbase.oss.dto.common.forms.EntryListDto
import org.joda.time.DateTime

data class NonGradedAssessment(
	override val id: UUID?,
	val lessonId: UUID,
	val form: EntryListDto,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object NonGradedAssessmentsDao:
	BasicDao<NonGradedAssessmentsTable, UUID, NonGradedAssessment>(NonGradedAssessmentsTable) {
	override fun toStatement(model: NonGradedAssessment, row: UpdateBuilder<Int>) {
		row[table.lessonId] = model.lessonId
		row[table.form] = model.form
	}

	override fun toModel(row: ResultRow): NonGradedAssessment {
		return NonGradedAssessment(
			id = row[table.id].value,
			lessonId = row[table.lessonId].value,
			form = row[table.form],
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

