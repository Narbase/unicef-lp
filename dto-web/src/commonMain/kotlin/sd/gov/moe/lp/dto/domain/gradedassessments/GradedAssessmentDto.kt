package sd.gov.moe.lp.dto.domain.gradedassessments

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import com.narbase.oss.dto.common.forms.EntryListDto
import kotlin.Boolean
import kotlin.Int
import kotlin.Double
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class GradedAssessmentDto(
	val id: StringUUID?,
	val lessonId: StringUUID,
	val form: EntryListDto,
	val areQuestionsShuffled: Boolean,
	val maximumAllowedAttempts: Int,
	val areAnswersShown: Boolean,
	val areOptionsShuffled: Boolean,
	val passingPercentage: Double,
	val createdOn: DateTimeDto?,
)

