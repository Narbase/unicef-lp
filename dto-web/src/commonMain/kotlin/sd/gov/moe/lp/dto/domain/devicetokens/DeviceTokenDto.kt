package sd.gov.moe.lp.dto.domain.devicetokens

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import kotlin.String
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class DeviceTokenDto(
	val id: StringUUID?,
	val token: String,
	val clientId: StringUUID,
	val createdOn: DateTimeDto?,
)

