package sd.gov.moe.lp.dto.domain.roles.clientsroles

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class ClientsRoleDto(
	val id: StringUUID?,
	val clientId: StringUUID,
	val dynamicRoleId: StringUUID,
	val createdOn: DateTimeDto?,
)

