package sd.gov.moe.lp.dto.domain.studentstandardlessons

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import kotlin.Boolean
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class StudentStandardLessonDto(
	val id: StringUUID?,
	val studentId: StringUUID,
	val lessonId: StringUUID,
	val isCompleted: Boolean,
	val createdOn: DateTimeDto?,
)

