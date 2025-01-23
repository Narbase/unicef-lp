package sd.gov.moe.lp.data.conversions.studentnongradedassessments

import sd.gov.moe.lp.data.access.studentnongradedassessments.StudentNonGradedAssessment
import sd.gov.moe.lp.dto.domain.studentnongradedassessments.StudentNonGradedAssessmentDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun StudentNonGradedAssessment.toDto(): StudentNonGradedAssessmentDto {
	return StudentNonGradedAssessmentDto(
		id = id?.toStringUUID(),
		studentId = studentId.toStringUUID(),
		lessonId = lessonId.toStringUUID(),
		progress = progress,
		answers = answers,
		createdOn = createdOn?.toDto(),
	)
}

fun StudentNonGradedAssessmentDto.toModel(): StudentNonGradedAssessment {
	return StudentNonGradedAssessment(
		id = id?.toUUID(),
		studentId = studentId.toUUID(),
		lessonId = lessonId.toUUID(),
		progress = progress,
		answers = answers,
		createdOn = createdOn?.toDateTime(),
	)
}
