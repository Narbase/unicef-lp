package sd.gov.moe.lp.dto.domain.partners

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import kotlin.String
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class PartnerDto(
	val id: StringUUID?,
	val name: String,
	val stateId: StringUUID,
	val createdOn: DateTimeDto?,
)

