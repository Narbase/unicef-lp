package sd.gov.moe.lp.data.conversions.administrativeunits

import sd.gov.moe.lp.data.access.administrativeunits.AdministrativeUnit
import sd.gov.moe.lp.dto.domain.administrativeunits.AdministrativeUnitDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun AdministrativeUnit.toDto(): AdministrativeUnitDto {
	return AdministrativeUnitDto(
		id = id?.toStringUUID(),
		name = name,
		localityId = localityId.toStringUUID(),
		createdOn = createdOn?.toDto(),
	)
}

fun AdministrativeUnitDto.toModel(): AdministrativeUnit {
	return AdministrativeUnit(
		id = id?.toUUID(),
		name = name,
		localityId = localityId.toUUID(),
		createdOn = createdOn?.toDateTime(),
	)
}
