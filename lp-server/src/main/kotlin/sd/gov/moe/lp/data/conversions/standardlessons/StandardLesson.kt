package sd.gov.moe.lp.data.conversions.standardlessons

import sd.gov.moe.lp.data.access.standardlessons.StandardLesson
import sd.gov.moe.lp.dto.domain.standardlessons.StandardLessonDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun StandardLesson.toDto(): StandardLessonDto {
	return StandardLessonDto(
		id = id?.toStringUUID(),
		lessonId = lessonId.toStringUUID(),
		fileId = fileId.toStringUUID(),
		createdOn = createdOn?.toDto(),
	)
}

fun StandardLessonDto.toModel(): StandardLesson {
	return StandardLesson(
		id = id?.toUUID(),
		lessonId = lessonId.toUUID(),
		fileId = fileId.toUUID(),
		createdOn = createdOn?.toDateTime(),
	)
}
