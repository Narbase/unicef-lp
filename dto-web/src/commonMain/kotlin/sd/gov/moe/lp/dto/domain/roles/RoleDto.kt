package sd.gov.moe.lp.dto.domain.roles

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto
import kotlin.String
import sd.gov.moe.lp.dto.domain.columntypes.RolePrivilegesColumnDto

@JsExport
data class RoleDto(
	val id: StringUUID?,
	val createdOn: DateTimeDto?,
	val name: String,
	val role: RolePrivilegesColumnDto,
)

