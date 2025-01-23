package sd.gov.moe.lp.data.conversions.gradedassessments

import sd.gov.moe.lp.data.access.gradedassessments.GradedAssessment
import sd.gov.moe.lp.dto.domain.gradedassessments.GradedAssessmentDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun GradedAssessment.toDto(): GradedAssessmentDto {
	return GradedAssessmentDto(
		id = id?.toStringUUID(),
		lessonId = lessonId.toStringUUID(),
		form = form,
		areQuestionsShuffled = areQuestionsShuffled,
		maximumAllowedAttempts = maximumAllowedAttempts,
		areAnswersShown = areAnswersShown,
		areOptionsShuffled = areOptionsShuffled,
		passingPercentage = passingPercentage,
		createdOn = createdOn?.toDto(),
	)
}

fun GradedAssessmentDto.toModel(): GradedAssessment {
	return GradedAssessment(
		id = id?.toUUID(),
		lessonId = lessonId.toUUID(),
		form = form,
		areQuestionsShuffled = areQuestionsShuffled,
		maximumAllowedAttempts = maximumAllowedAttempts,
		areAnswersShown = areAnswersShown,
		areOptionsShuffled = areOptionsShuffled,
		passingPercentage = passingPercentage,
		createdOn = createdOn?.toDateTime(),
	)
}
