package sd.gov.moe.lp.data.conversions.subjects

import sd.gov.moe.lp.data.access.subjects.Subject
import sd.gov.moe.lp.dto.domain.subjects.SubjectDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun Subject.toDto(): SubjectDto {
	return SubjectDto(
		id = id?.toStringUUID(),
		name = name,
		description = description,
		thumbnailId = thumbnailId?.toStringUUID(),
		hasCertificate = hasCertificate,
		isLessonsOrderRestrictive = isLessonsOrderRestrictive,
		feedbackFormId = feedbackFormId?.toStringUUID(),
		createdOn = createdOn?.toDto(),
	)
}

fun SubjectDto.toModel(): Subject {
	return Subject(
		id = id?.toUUID(),
		name = name,
		description = description,
		thumbnailId = thumbnailId?.toUUID(),
		hasCertificate = hasCertificate,
		isLessonsOrderRestrictive = isLessonsOrderRestrictive,
		feedbackFormId = feedbackFormId?.toUUID(),
		createdOn = createdOn?.toDateTime(),
	)
}
