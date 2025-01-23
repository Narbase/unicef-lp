package sd.gov.moe.lp.data.conversions.studentstandardlessons

import sd.gov.moe.lp.data.access.studentstandardlessons.StudentStandardLesson
import sd.gov.moe.lp.dto.domain.studentstandardlessons.StudentStandardLessonDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun StudentStandardLesson.toDto(): StudentStandardLessonDto {
	return StudentStandardLessonDto(
		id = id?.toStringUUID(),
		studentId = studentId.toStringUUID(),
		lessonId = lessonId.toStringUUID(),
		isCompleted = isCompleted,
		createdOn = createdOn?.toDto(),
	)
}

fun StudentStandardLessonDto.toModel(): StudentStandardLesson {
	return StudentStandardLesson(
		id = id?.toUUID(),
		studentId = studentId.toUUID(),
		lessonId = lessonId.toUUID(),
		isCompleted = isCompleted,
		createdOn = createdOn?.toDateTime(),
	)
}
