package sd.gov.moe.lp.data.conversions.studentcontrols

import sd.gov.moe.lp.data.access.studentcontrols.StudentControls
import sd.gov.moe.lp.dto.domain.studentcontrols.StudentControlsDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.dto.common.dto
import sd.gov.moe.lp.dto.common.enum
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDate
import sd.gov.moe.lp.domain.utils.toDateTime

fun StudentControls.toDto(): StudentControlsDto {
	return StudentControlsDto(
		id = id?.toStringUUID(),
		studentId = studentId.toStringUUID(),
		status = status.dto(),
		groupId = groupId.toStringUUID(),
		startDate = startDate.toDto(),
		endDate = endDate.toDto(),
		background = background.dto(),
		isActive = isActive,
		isPaused = isPaused,
		createdOn = createdOn?.toDto(),
	)
}

fun StudentControlsDto.toModel(): StudentControls {
	return StudentControls(
		id = id?.toUUID(),
		studentId = studentId.toUUID(),
		status = status.enum(),
		groupId = groupId.toUUID(),
		startDate = startDate.toDate(),
		endDate = endDate.toDate(),
		background = background.enum(),
		isActive = isActive,
		isPaused = isPaused,
		createdOn = createdOn?.toDateTime(),
	)
}
