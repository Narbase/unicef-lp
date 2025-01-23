package sd.gov.moe.lp.dto.domain.studentsubjects

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import kotlin.Double
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto
import kotlin.Boolean
import com.narbase.oss.dto.common.forms.AnswersListDto

@JsExport
data class StudentSubjectDto(
	val id: StringUUID?,
	val studentId: StringUUID,
	val subjectId: StringUUID,
	val progress: Double,
	val completedOn: DateTimeDto?,
	val hasCertificate: Boolean?,
	val feedback: AnswersListDto?,
	val createdOn: DateTimeDto?,
)

