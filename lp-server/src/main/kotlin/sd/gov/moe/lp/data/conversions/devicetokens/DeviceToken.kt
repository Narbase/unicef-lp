package sd.gov.moe.lp.data.conversions.devicetokens

import sd.gov.moe.lp.data.access.devicetokens.DeviceToken
import sd.gov.moe.lp.dto.domain.devicetokens.DeviceTokenDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun DeviceToken.toDto(): DeviceTokenDto {
	return DeviceTokenDto(
		id = id?.toStringUUID(),
		token = token,
		clientId = clientId.toStringUUID(),
		createdOn = createdOn?.toDto(),
	)
}

fun DeviceTokenDto.toModel(): DeviceToken {
	return DeviceToken(
		id = id?.toUUID(),
		token = token,
		clientId = clientId.toUUID(),
		createdOn = createdOn?.toDateTime(),
	)
}
