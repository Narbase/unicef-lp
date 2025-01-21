package sd.gov.moe.lp.data.models

import com.narbase.oss.dto.common.forms.EntryListDto
import sd.gov.moe.lp.dto.common.StringUUID
import sd.gov.moe.lp.dto.common.enums.LessonType

data class ReleaseDetails(
    val version: String,
    val releaseGrades: List<ReleaseGrade>
)

data class ReleaseGrade(
    val gradeId: StringUUID,
    val releaseSubjects: List<ReleaseSubjects>
)

data class ReleaseSubjects(
    val subjectId: StringUUID,
    val releaseLessons: List<ReleaseLessons>
)

data class ReleaseLessons(
    val lessonId: StringUUID,
    val lessonType: LessonType,
    val releaseStandardLesson: ReleaseStandardLesson?,
    val releaseNonGradedAssessment: ReleaseNonGradedAssessment?,
    val releaseGradedAssessment: ReleaseGradedAssessment?,
)

data class ReleaseStandardLesson(
    val lessonFilePath: String,
)

data class ReleaseNonGradedAssessment(
    val assessmentForm: EntryListDto,
)

data class ReleaseGradedAssessment(
    val assessmentForm: EntryListDto,
    val areQuestionsShuffled: Boolean,
    val maximumAllowedAttempts: Int,
    val areAnswersShown: Boolean,
    val areOptionsShuffled: Boolean,
    val passingPercentage: Double
)