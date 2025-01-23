package sd.gov.moe.lp.data.conversions.groupadmins

import sd.gov.moe.lp.data.access.groupadmins.GroupAdmin
import sd.gov.moe.lp.dto.domain.groupadmins.GroupAdminDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun GroupAdmin.toDto(): GroupAdminDto {
	return GroupAdminDto(
		id = id?.toStringUUID(),
		groupId = groupId.toStringUUID(),
		staffId = staffId.toStringUUID(),
		createdOn = createdOn?.toDto(),
	)
}

fun GroupAdminDto.toModel(): GroupAdmin {
	return GroupAdmin(
		id = id?.toUUID(),
		groupId = groupId.toUUID(),
		staffId = staffId.toUUID(),
		createdOn = createdOn?.toDateTime(),
	)
}
