package sd.gov.moe.lp.data.conversions.clients

import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.data.access.clients.Client
import sd.gov.moe.lp.domain.utils.toDateTime
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.dto.domain.clients.ClientDto

fun Client.toDto(): ClientDto {
	return ClientDto(
		id = id?.toStringUUID(),
		username = username,
		passwordHash = passwordHash,
		lastLogin = lastLogin?.toDto(),
		createdOn = createdOn?.toDto(),
	)
}

fun ClientDto.toModel(): Client {
	return Client(
		id = id?.toUUID(),
		username = username,
		passwordHash = passwordHash,
		lastLogin = lastLogin?.toDateTime(),
		createdOn = createdOn?.toDateTime(),
	)
}
