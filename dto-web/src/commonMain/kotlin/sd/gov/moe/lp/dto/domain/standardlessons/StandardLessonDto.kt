package sd.gov.moe.lp.dto.domain.standardlessons

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class StandardLessonDto(
	val id: StringUUID?,
	val lessonId: StringUUID,
	val fileId: StringUUID,
	val createdOn: DateTimeDto?,
)

