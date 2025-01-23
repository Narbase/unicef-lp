package sd.gov.moe.lp.dto.domain.administrativeunits

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import kotlin.String
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class AdministrativeUnitDto(
	val id: StringUUID?,
	val name: String,
	val localityId: StringUUID,
	val createdOn: DateTimeDto?,
)

