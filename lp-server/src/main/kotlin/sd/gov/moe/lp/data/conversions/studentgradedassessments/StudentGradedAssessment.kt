package sd.gov.moe.lp.data.conversions.studentgradedassessments

import sd.gov.moe.lp.data.access.studentgradedassessments.StudentGradedAssessment
import sd.gov.moe.lp.dto.domain.studentgradedassessments.StudentGradedAssessmentDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun StudentGradedAssessment.toDto(): StudentGradedAssessmentDto {
	return StudentGradedAssessmentDto(
		id = id?.toStringUUID(),
		studentId = studentId.toStringUUID(),
		lessonId = lessonId.toStringUUID(),
		progress = progress,
		answers = answers,
		attempts = attempts,
		createdOn = createdOn?.toDto(),
	)
}

fun StudentGradedAssessmentDto.toModel(): StudentGradedAssessment {
	return StudentGradedAssessment(
		id = id?.toUUID(),
		studentId = studentId.toUUID(),
		lessonId = lessonId.toUUID(),
		progress = progress,
		answers = answers,
		attempts = attempts,
		createdOn = createdOn?.toDateTime(),
	)
}
