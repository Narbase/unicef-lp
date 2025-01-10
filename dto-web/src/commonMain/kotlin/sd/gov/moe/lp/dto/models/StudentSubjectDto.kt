package sd.gov.moe.lp.dto.models

import sd.gov.moe.lp.dto.common.StringUUID
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto
import kotlin.js.JsExport

@Deprecated("replace with narrator")
@JsExport
data class StudentSubjectDto(
    val id: StringUUID?,
    val studentId: StringUUID,
    val subjectId: StringUUID,
    val progress: Double,
    val completedOn: DateTimeDto?,
    val enrolledOn: DateTimeDto,
)
