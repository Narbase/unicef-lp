package sd.gov.moe.lp.data.conversions.states

import sd.gov.moe.lp.data.access.states.State
import sd.gov.moe.lp.dto.domain.states.StateDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.dto.common.dto
import sd.gov.moe.lp.dto.common.enum
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun State.toDto(): StateDto {
	return StateDto(
		id = id?.toStringUUID(),
		name = name,
		country = country.dto(),
		createdOn = createdOn?.toDto(),
	)
}

fun StateDto.toModel(): State {
	return State(
		id = id?.toUUID(),
		name = name,
		country = country.enum(),
		createdOn = createdOn?.toDateTime(),
	)
}
