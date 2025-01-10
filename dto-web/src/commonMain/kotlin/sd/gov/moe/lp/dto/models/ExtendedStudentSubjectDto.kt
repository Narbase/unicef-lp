package sd.gov.moe.lp.dto.models

import sd.gov.moe.lp.dto.common.StringUUID
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto
import kotlin.js.JsExport

@Deprecated("replace with narrator")
@JsExport
data class ExtendedStudentSubjectDto(
    val subject: SubjectDto,
    val studentSubject: StudentSubjectDto,
    val studentSubjectAssessments: Array<StudentAssessmentDto>,
)
