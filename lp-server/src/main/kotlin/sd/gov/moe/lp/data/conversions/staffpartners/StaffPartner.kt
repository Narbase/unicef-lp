package sd.gov.moe.lp.data.conversions.staffpartners

import sd.gov.moe.lp.data.access.staffpartners.StaffPartner
import sd.gov.moe.lp.dto.domain.staffpartners.StaffPartnerDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun StaffPartner.toDto(): StaffPartnerDto {
	return StaffPartnerDto(
		id = id?.toStringUUID(),
		staffId = staffId.toStringUUID(),
		partnerId = partnerId.toStringUUID(),
		createdOn = createdOn?.toDto(),
	)
}

fun StaffPartnerDto.toModel(): StaffPartner {
	return StaffPartner(
		id = id?.toUUID(),
		staffId = staffId.toUUID(),
		partnerId = partnerId.toUUID(),
		createdOn = createdOn?.toDateTime(),
	)
}
