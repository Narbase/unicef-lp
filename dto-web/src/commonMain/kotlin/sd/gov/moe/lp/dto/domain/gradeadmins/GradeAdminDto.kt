package sd.gov.moe.lp.dto.domain.gradeadmins

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class GradeAdminDto(
	val id: StringUUID?,
	val gradeId: StringUUID,
	val staffId: StringUUID,
	val createdOn: DateTimeDto?,
)

