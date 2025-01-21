package sd.gov.moe.lp.data.models

import com.narbase.oss.dto.common.forms.AnswersListDto
import sd.gov.moe.lp.dto.common.StringUUID

data class StudentActivityData(
    val enrollmentData: StudentEnrollmentData?,
    val lessonData: StudentLessonData?
)

data class StudentEnrollmentData(
    val subjectId: StringUUID,
    val gradeId: StringUUID,
)

data class StudentLessonData(
    val lessonId: StringUUID,
    val score: Double, // completed is a 100.0
    val assessmentAnswers: AnswersListDto?,
    val attemptNumber: Int?,
)
