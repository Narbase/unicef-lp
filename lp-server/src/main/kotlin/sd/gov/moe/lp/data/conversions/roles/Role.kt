package sd.gov.moe.lp.data.conversions.roles

import sd.gov.moe.lp.data.access.roles.Role
import sd.gov.moe.lp.dto.domain.roles.RoleDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime
import sd.gov.moe.lp.data.conversions.columntypes.toDto
import sd.gov.moe.lp.data.conversions.columntypes.toModel
import sd.gov.moe.lp.data.tables.roles.RolesTable.role

fun Role.toDto(): RoleDto {
	return RoleDto(
		id = id?.toStringUUID(),
		createdOn = createdOn?.toDto(),
		name = name,
		role = role.toDto(),
	)
}

fun RoleDto.toModel(): Role {
	return Role(
		id = id?.toUUID(),
		createdOn = createdOn?.toDateTime(),
		name = name,
		role = role.toModel(),
	)
}
