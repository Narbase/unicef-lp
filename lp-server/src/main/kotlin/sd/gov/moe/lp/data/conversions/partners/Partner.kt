package sd.gov.moe.lp.data.conversions.partners

import sd.gov.moe.lp.data.access.partners.Partner
import sd.gov.moe.lp.dto.domain.partners.PartnerDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun Partner.toDto(): PartnerDto {
	return PartnerDto(
		id = id?.toStringUUID(),
		name = name,
		stateId = stateId.toStringUUID(),
		createdOn = createdOn?.toDto(),
	)
}

fun PartnerDto.toModel(): Partner {
	return Partner(
		id = id?.toUUID(),
		name = name,
		stateId = stateId.toUUID(),
		createdOn = createdOn?.toDateTime(),
	)
}
