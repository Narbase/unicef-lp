package sd.gov.moe.lp.data.conversions.teachers

import sd.gov.moe.lp.data.access.teachers.Teacher
import sd.gov.moe.lp.dto.domain.teachers.TeacherDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.dto.common.dto
import sd.gov.moe.lp.dto.common.enum
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun Teacher.toDto(): TeacherDto {
	return TeacherDto(
		id = id?.toStringUUID(),
		gender = gender.dto(),
		centerId = centerId.toStringUUID(),
		staffId = staffId.toStringUUID(),
		createdOn = createdOn?.toDto(),
	)
}

fun TeacherDto.toModel(): Teacher {
	return Teacher(
		id = id?.toUUID(),
		gender = gender.enum(),
		centerId = centerId.toUUID(),
		staffId = staffId.toUUID(),
		createdOn = createdOn?.toDateTime(),
	)
}
