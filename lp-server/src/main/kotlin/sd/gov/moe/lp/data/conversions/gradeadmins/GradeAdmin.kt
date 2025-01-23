package sd.gov.moe.lp.data.conversions.gradeadmins

import sd.gov.moe.lp.data.access.gradeadmins.GradeAdmin
import sd.gov.moe.lp.dto.domain.gradeadmins.GradeAdminDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun GradeAdmin.toDto(): GradeAdminDto {
	return GradeAdminDto(
		id = id?.toStringUUID(),
		gradeId = gradeId.toStringUUID(),
		staffId = staffId.toStringUUID(),
		createdOn = createdOn?.toDto(),
	)
}

fun GradeAdminDto.toModel(): GradeAdmin {
	return GradeAdmin(
		id = id?.toUUID(),
		gradeId = gradeId.toUUID(),
		staffId = staffId.toUUID(),
		createdOn = createdOn?.toDateTime(),
	)
}
