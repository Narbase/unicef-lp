package sd.gov.moe.lp.dto.domain.staff

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import kotlin.String
import sd.gov.moe.lp.dto.common.DtoName
import sd.gov.moe.lp.dto.common.enums.Country
import kotlin.Boolean
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class StaffDto(
	val id: StringUUID?,
	val clientId: StringUUID,
	val fullName: String,
	val country: DtoName<Country>,
	val isInactive: Boolean,
	val createdOn: DateTimeDto?,
)

