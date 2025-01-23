package sd.gov.moe.lp.dto.domain.grades

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import kotlin.String
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class GradeDto(
	val id: StringUUID?,
	val name: String,
	val thumbnailId: StringUUID?,
	val createdOn: DateTimeDto?,
)

