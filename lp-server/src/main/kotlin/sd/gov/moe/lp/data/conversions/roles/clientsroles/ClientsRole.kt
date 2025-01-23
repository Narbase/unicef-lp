package sd.gov.moe.lp.data.conversions.roles.clientsroles

import sd.gov.moe.lp.data.access.roles.clientsroles.ClientsRole
import sd.gov.moe.lp.dto.domain.roles.clientsroles.ClientsRoleDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun ClientsRole.toDto(): ClientsRoleDto {
	return ClientsRoleDto(
		id = id?.toStringUUID(),
		clientId = clientId.toStringUUID(),
		dynamicRoleId = dynamicRoleId.toStringUUID(),
		createdOn = createdOn?.toDto(),
	)
}

fun ClientsRoleDto.toModel(): ClientsRole {
	return ClientsRole(
		id = id?.toUUID(),
		clientId = clientId.toUUID(),
		dynamicRoleId = dynamicRoleId.toUUID(),
		createdOn = createdOn?.toDateTime(),
	)
}
