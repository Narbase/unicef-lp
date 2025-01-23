package sd.gov.moe.lp.data.conversions.nongradedassessments

import sd.gov.moe.lp.data.access.nongradedassessments.NonGradedAssessment
import sd.gov.moe.lp.dto.domain.nongradedassessments.NonGradedAssessmentDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun NonGradedAssessment.toDto(): NonGradedAssessmentDto {
	return NonGradedAssessmentDto(
		id = id?.toStringUUID(),
		lessonId = lessonId.toStringUUID(),
		form = form,
		createdOn = createdOn?.toDto(),
	)
}

fun NonGradedAssessmentDto.toModel(): NonGradedAssessment {
	return NonGradedAssessment(
		id = id?.toUUID(),
		lessonId = lessonId.toUUID(),
		form = form,
		createdOn = createdOn?.toDateTime(),
	)
}
