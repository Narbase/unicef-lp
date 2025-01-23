package sd.gov.moe.lp.data.conversions.centers

import sd.gov.moe.lp.data.access.centers.Center
import sd.gov.moe.lp.dto.domain.centers.CenterDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import kotlin.Double
import java.math.BigDecimal
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun Center.toDto(): CenterDto {
	return CenterDto(
		id = id?.toStringUUID(),
		name = name,
		lat = lat.let{ it.toDouble() },
		lng = lng.let{ it.toDouble() },
		administrativeUnitId = administrativeUnitId.toStringUUID(),
		createdOn = createdOn?.toDto(),
	)
}

fun CenterDto.toModel(): Center {
	return Center(
		id = id?.toUUID(),
		name = name,
		lat = lat.let{ BigDecimal.valueOf(it) },
		lng = lng.let{ BigDecimal.valueOf(it) },
		administrativeUnitId = administrativeUnitId.toUUID(),
		createdOn = createdOn?.toDateTime(),
	)
}
