package sd.gov.moe.lp.dto.models

import sd.gov.moe.lp.dto.common.StringUUID
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto
import kotlin.js.JsExport

@Deprecated("replace with narrator")
@JsExport
data class StudentAssessmentDto(
    val id: StringUUID?,
    val studentId: StringUUID,
    val assessmentId: StringUUID,
    val progress: Double,
)
