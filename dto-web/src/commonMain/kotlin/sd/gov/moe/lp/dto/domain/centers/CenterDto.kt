package sd.gov.moe.lp.dto.domain.centers

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import kotlin.String
import kotlin.Double
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class CenterDto(
	val id: StringUUID?,
	val name: String,
	val lat: Double,
	val lng: Double,
	val administrativeUnitId: StringUUID,
	val createdOn: DateTimeDto?,
)

