package sd.gov.moe.lp.data.conversions.staff

import sd.gov.moe.lp.data.access.staff.Staff
import sd.gov.moe.lp.dto.domain.staff.StaffDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.dto.common.dto
import sd.gov.moe.lp.dto.common.enum
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun Staff.toDto(): StaffDto {
	return StaffDto(
		id = id?.toStringUUID(),
		clientId = clientId.toStringUUID(),
		fullName = fullName,
		country = country.dto(),
		isInactive = isInactive,
		createdOn = createdOn?.toDto(),
	)
}

fun StaffDto.toModel(): Staff {
	return Staff(
		id = id?.toUUID(),
		clientId = clientId.toUUID(),
		fullName = fullName,
		country = country.enum(),
		isInactive = isInactive,
		createdOn = createdOn?.toDateTime(),
	)
}
