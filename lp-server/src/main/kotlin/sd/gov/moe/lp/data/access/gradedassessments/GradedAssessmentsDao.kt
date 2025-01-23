package sd.gov.moe.lp.data.access.gradedassessments

import sd.gov.moe.lp.data.access.utils.BasicDao
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.GradedAssessmentsTable
import org.jetbrains.exposed.dao.id.EntityID
import com.narbase.oss.dto.common.forms.EntryListDto
import kotlin.Boolean
import kotlin.Int
import kotlin.Double
import org.joda.time.DateTime

data class GradedAssessment(
	override val id: UUID?,
	val lessonId: UUID,
	val form: EntryListDto,
	val areQuestionsShuffled: Boolean,
	val maximumAllowedAttempts: Int,
	val areAnswersShown: Boolean,
	val areOptionsShuffled: Boolean,
	val passingPercentage: Double,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object GradedAssessmentsDao:
	BasicDao<GradedAssessmentsTable, UUID, GradedAssessment>(GradedAssessmentsTable) {
	override fun toStatement(model: GradedAssessment, row: UpdateBuilder<Int>) {
		row[table.lessonId] = model.lessonId
		row[table.form] = model.form
		row[table.areQuestionsShuffled] = model.areQuestionsShuffled
		row[table.maximumAllowedAttempts] = model.maximumAllowedAttempts
		row[table.areAnswersShown] = model.areAnswersShown
		row[table.areOptionsShuffled] = model.areOptionsShuffled
		row[table.passingPercentage] = model.passingPercentage
	}

	override fun toModel(row: ResultRow): GradedAssessment {
		return GradedAssessment(
			id = row[table.id].value,
			lessonId = row[table.lessonId].value,
			form = row[table.form],
			areQuestionsShuffled = row[table.areQuestionsShuffled],
			maximumAllowedAttempts = row[table.maximumAllowedAttempts],
			areAnswersShown = row[table.areAnswersShown],
			areOptionsShuffled = row[table.areOptionsShuffled],
			passingPercentage = row[table.passingPercentage],
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

