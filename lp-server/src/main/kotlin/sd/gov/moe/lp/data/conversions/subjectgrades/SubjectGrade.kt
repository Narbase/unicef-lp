package sd.gov.moe.lp.data.conversions.subjectgrades

import sd.gov.moe.lp.data.access.subjectgrades.SubjectGrade
import sd.gov.moe.lp.dto.domain.subjectgrades.SubjectGradeDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.dto.common.dto
import sd.gov.moe.lp.dto.common.enum
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun SubjectGrade.toDto(): SubjectGradeDto {
	return SubjectGradeDto(
		id = id?.toStringUUID(),
		gradeId = gradeId.toStringUUID(),
		subjectId = subjectId.toStringUUID(),
		enrollmentType = enrollmentType.dto(),
		createdOn = createdOn?.toDto(),
	)
}

fun SubjectGradeDto.toModel(): SubjectGrade {
	return SubjectGrade(
		id = id?.toUUID(),
		gradeId = gradeId.toUUID(),
		subjectId = subjectId.toUUID(),
		enrollmentType = enrollmentType.enum(),
		createdOn = createdOn?.toDateTime(),
	)
}
