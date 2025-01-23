package sd.gov.moe.lp.dto.domain.subjects

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import kotlin.String
import kotlin.Boolean
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class SubjectDto(
	val id: StringUUID?,
	val name: String,
	val description: String?,
	val thumbnailId: StringUUID?,
	val hasCertificate: Boolean,
	val isLessonsOrderRestrictive: Boolean,
	val feedbackFormId: StringUUID?,
	val createdOn: DateTimeDto?,
)

