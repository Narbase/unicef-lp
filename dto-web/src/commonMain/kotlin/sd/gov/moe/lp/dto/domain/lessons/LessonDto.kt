package sd.gov.moe.lp.dto.domain.lessons

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import kotlin.String
import sd.gov.moe.lp.dto.common.DtoName
import sd.gov.moe.lp.dto.common.enums.LessonType
import kotlin.Int
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class LessonDto(
	val id: StringUUID?,
	val subjectId: StringUUID,
	val title: String,
	val type: DtoName<LessonType>,
	val order: Int,
	val createdOn: DateTimeDto?,
)

