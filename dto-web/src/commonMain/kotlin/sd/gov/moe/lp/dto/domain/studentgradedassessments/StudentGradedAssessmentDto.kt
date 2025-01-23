package sd.gov.moe.lp.dto.domain.studentgradedassessments

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import kotlin.Double
import com.narbase.oss.dto.common.forms.AnswersListDto
import kotlin.Int
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class StudentGradedAssessmentDto(
	val id: StringUUID?,
	val studentId: StringUUID,
	val lessonId: StringUUID,
	val progress: Double,
	val answers: AnswersListDto,
	val attempts: Int,
	val createdOn: DateTimeDto?,
)

