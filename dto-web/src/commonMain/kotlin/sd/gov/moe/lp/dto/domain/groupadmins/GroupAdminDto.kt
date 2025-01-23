package sd.gov.moe.lp.dto.domain.groupadmins

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class GroupAdminDto(
	val id: StringUUID?,
	val groupId: StringUUID,
	val staffId: StringUUID,
	val createdOn: DateTimeDto?,
)

