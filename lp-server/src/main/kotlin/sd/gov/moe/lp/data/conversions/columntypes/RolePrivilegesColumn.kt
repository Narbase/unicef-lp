package sd.gov.moe.lp.data.conversions.columntypes

import sd.gov.moe.lp.data.columntypes.RolePrivilegesColumn
import sd.gov.moe.lp.dto.domain.columntypes.RolePrivilegesColumnDto
import sd.gov.moe.lp.dto.common.dto
import sd.gov.moe.lp.dto.common.enum
import kotlin.collections.toTypedArray

fun RolePrivilegesColumn.toDto(): RolePrivilegesColumnDto {
	return RolePrivilegesColumnDto(
		privileges = privileges.map{ it.dto() }.toTypedArray(),
	)
}

fun RolePrivilegesColumnDto.toModel(): RolePrivilegesColumn {
	return RolePrivilegesColumn(
		privileges = privileges.map{ it.enum() }.toList(),
	)
}
