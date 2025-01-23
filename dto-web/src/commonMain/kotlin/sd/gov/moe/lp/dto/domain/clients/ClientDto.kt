package sd.gov.moe.lp.dto.domain.clients

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import kotlin.String
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class ClientDto(
	val id: StringUUID?,
	val username: String,
	val passwordHash: String,
	val lastLogin: DateTimeDto?,
	val createdOn: DateTimeDto?,
)

