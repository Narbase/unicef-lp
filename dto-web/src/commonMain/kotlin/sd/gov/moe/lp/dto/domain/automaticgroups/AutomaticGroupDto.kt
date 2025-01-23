package sd.gov.moe.lp.dto.domain.automaticgroups

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class AutomaticGroupDto(
	val id: StringUUID?,
	val groupId: StringUUID,
	val gradeId: StringUUID,
	val createdOn: DateTimeDto?,
)

