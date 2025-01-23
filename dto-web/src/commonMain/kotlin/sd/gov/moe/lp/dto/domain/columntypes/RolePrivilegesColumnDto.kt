package sd.gov.moe.lp.dto.domain.columntypes

import kotlin.js.JsExport
import sd.gov.moe.lp.dto.common.DtoName
import sd.gov.moe.lp.dto.models.roles.Privilege
import kotlin.Array

@JsExport
data class RolePrivilegesColumnDto(
	val privileges: Array<DtoName<Privilege>>,
)

