package sd.gov.moe.lp.data.conversions.grades

import sd.gov.moe.lp.data.access.grades.Grade
import sd.gov.moe.lp.dto.domain.grades.GradeDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun Grade.toDto(): GradeDto {
	return GradeDto(
		id = id?.toStringUUID(),
		name = name,
		thumbnailId = thumbnailId?.toStringUUID(),
		createdOn = createdOn?.toDto(),
	)
}

fun GradeDto.toModel(): Grade {
	return Grade(
		id = id?.toUUID(),
		name = name,
		thumbnailId = thumbnailId?.toUUID(),
		createdOn = createdOn?.toDateTime(),
	)
}
