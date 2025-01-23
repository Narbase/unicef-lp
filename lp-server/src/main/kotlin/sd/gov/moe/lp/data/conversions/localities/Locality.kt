package sd.gov.moe.lp.data.conversions.localities

import sd.gov.moe.lp.data.access.localities.Locality
import sd.gov.moe.lp.dto.domain.localities.LocalityDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun Locality.toDto(): LocalityDto {
	return LocalityDto(
		id = id?.toStringUUID(),
		name = name,
		stateId = stateId.toStringUUID(),
		createdOn = createdOn?.toDto(),
	)
}

fun LocalityDto.toModel(): Locality {
	return Locality(
		id = id?.toUUID(),
		name = name,
		stateId = stateId.toUUID(),
		createdOn = createdOn?.toDateTime(),
	)
}
