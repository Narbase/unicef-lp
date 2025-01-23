package sd.gov.moe.lp.data.conversions.lessons

import sd.gov.moe.lp.data.access.lessons.Lesson
import sd.gov.moe.lp.dto.domain.lessons.LessonDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.dto.common.dto
import sd.gov.moe.lp.dto.common.enum
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun Lesson.toDto(): LessonDto {
	return LessonDto(
		id = id?.toStringUUID(),
		subjectId = subjectId.toStringUUID(),
		title = title,
		type = type.dto(),
		order = order,
		createdOn = createdOn?.toDto(),
	)
}

fun LessonDto.toModel(): Lesson {
	return Lesson(
		id = id?.toUUID(),
		subjectId = subjectId.toUUID(),
		title = title,
		type = type.enum(),
		order = order,
		createdOn = createdOn?.toDateTime(),
	)
}
