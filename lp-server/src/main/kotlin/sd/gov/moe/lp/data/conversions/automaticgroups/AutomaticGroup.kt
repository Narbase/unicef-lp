package sd.gov.moe.lp.data.conversions.automaticgroups

import sd.gov.moe.lp.data.access.automaticgroups.AutomaticGroup
import sd.gov.moe.lp.dto.domain.automaticgroups.AutomaticGroupDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun AutomaticGroup.toDto(): AutomaticGroupDto {
	return AutomaticGroupDto(
		id = id?.toStringUUID(),
		groupId = groupId.toStringUUID(),
		gradeId = gradeId.toStringUUID(),
		createdOn = createdOn?.toDto(),
	)
}

fun AutomaticGroupDto.toModel(): AutomaticGroup {
	return AutomaticGroup(
		id = id?.toUUID(),
		groupId = groupId.toUUID(),
		gradeId = gradeId.toUUID(),
		createdOn = createdOn?.toDateTime(),
	)
}
