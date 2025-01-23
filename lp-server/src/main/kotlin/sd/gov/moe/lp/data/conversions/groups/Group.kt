package sd.gov.moe.lp.data.conversions.groups

import sd.gov.moe.lp.data.access.groups.Group
import sd.gov.moe.lp.dto.domain.groups.GroupDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDate
import sd.gov.moe.lp.dto.common.dto
import sd.gov.moe.lp.dto.common.enum
import sd.gov.moe.lp.domain.utils.toDateTime

fun Group.toDto(): GroupDto {
	return GroupDto(
		id = id?.toStringUUID(),
		name = name,
		centerId = centerId.toStringUUID(),
		teacherId = teacherId.toStringUUID(),
		startDate = startDate.toDto(),
		endDate = endDate.toDto(),
		education = education.dto(),
		partnerId = partnerId.toStringUUID(),
		isDeleteRequested = isDeleteRequested,
		createdOn = createdOn?.toDto(),
	)
}

fun GroupDto.toModel(): Group {
	return Group(
		id = id?.toUUID(),
		name = name,
		centerId = centerId.toUUID(),
		teacherId = teacherId.toUUID(),
		startDate = startDate.toDate(),
		endDate = endDate.toDate(),
		education = education.enum(),
		partnerId = partnerId.toUUID(),
		isDeleteRequested = isDeleteRequested,
		createdOn = createdOn?.toDateTime(),
	)
}
