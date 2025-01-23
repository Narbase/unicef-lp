package sd.gov.moe.lp.dto.domain.files

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import kotlin.String
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class FileDto(
	val id: StringUUID?,
	val fileUrl: String,
	val fileName: String,
	val createdOn: DateTimeDto?,
)

