package sd.gov.moe.lp.dto.models

import sd.gov.moe.lp.dto.common.StringUUID
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto
import sd.gov.moe.lp.dto.domain.studentgradedassessments.StudentGradedAssessmentDto
import sd.gov.moe.lp.dto.domain.studentsubjects.StudentSubjectDto
import sd.gov.moe.lp.dto.domain.subjects.SubjectDto
import kotlin.js.JsExport

@Deprecated("replace with narrator")
@JsExport
data class ExtendedStudentSubjectDto(
    val subject: SubjectDto,
    val studentSubject: StudentSubjectDto,
    val studentSubjectAssessments: Array<StudentGradedAssessmentDto>,
)
