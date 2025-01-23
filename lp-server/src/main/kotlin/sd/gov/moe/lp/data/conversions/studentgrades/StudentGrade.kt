package sd.gov.moe.lp.data.conversions.studentgrades

import sd.gov.moe.lp.data.access.studentgrades.StudentGrade
import sd.gov.moe.lp.dto.domain.studentgrades.StudentGradeDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun StudentGrade.toDto(): StudentGradeDto {
	return StudentGradeDto(
		id = id?.toStringUUID(),
		studentId = studentId.toStringUUID(),
		gradeId = gradeId.toStringUUID(),
		progress = progress,
		createdOn = createdOn?.toDto(),
	)
}

fun StudentGradeDto.toModel(): StudentGrade {
	return StudentGrade(
		id = id?.toUUID(),
		studentId = studentId.toUUID(),
		gradeId = gradeId.toUUID(),
		progress = progress,
		createdOn = createdOn?.toDateTime(),
	)
}
