package sd.gov.moe.lp.dto.domain.subjectadmins

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class SubjectAdminDto(
	val id: StringUUID?,
	val subjectId: StringUUID,
	val clientId: StringUUID,
	val createdOn: DateTimeDto?,
)

