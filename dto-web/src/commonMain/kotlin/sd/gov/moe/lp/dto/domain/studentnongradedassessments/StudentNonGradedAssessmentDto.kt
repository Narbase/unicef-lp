package sd.gov.moe.lp.dto.domain.studentnongradedassessments

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import kotlin.Double
import com.narbase.oss.dto.common.forms.AnswersListDto
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class StudentNonGradedAssessmentDto(
	val id: StringUUID?,
	val studentId: StringUUID,
	val lessonId: StringUUID,
	val progress: Double,
	val answers: AnswersListDto,
	val createdOn: DateTimeDto?,
)

