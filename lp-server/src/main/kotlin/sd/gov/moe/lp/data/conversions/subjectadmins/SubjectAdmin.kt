package sd.gov.moe.lp.data.conversions.subjectadmins

import sd.gov.moe.lp.data.access.subjectadmins.SubjectAdmin
import sd.gov.moe.lp.dto.domain.subjectadmins.SubjectAdminDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun SubjectAdmin.toDto(): SubjectAdminDto {
	return SubjectAdminDto(
		id = id?.toStringUUID(),
		subjectId = subjectId.toStringUUID(),
		clientId = clientId.toStringUUID(),
		createdOn = createdOn?.toDto(),
	)
}

fun SubjectAdminDto.toModel(): SubjectAdmin {
	return SubjectAdmin(
		id = id?.toUUID(),
		subjectId = subjectId.toUUID(),
		clientId = clientId.toUUID(),
		createdOn = createdOn?.toDateTime(),
	)
}
