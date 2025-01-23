package sd.gov.moe.lp.dto.domain.staffpartners

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class StaffPartnerDto(
	val id: StringUUID?,
	val staffId: StringUUID,
	val partnerId: StringUUID,
	val createdOn: DateTimeDto?,
)

