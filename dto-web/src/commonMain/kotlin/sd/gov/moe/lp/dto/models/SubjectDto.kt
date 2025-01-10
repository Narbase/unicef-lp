package sd.gov.moe.lp.dto.models

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport

@Deprecated("replace with narrator")
@JsExport
data class SubjectDto(
    val id: StringUUID?,
    val gradeId: StringUUID,
    val name: String,
    val description: String?,
    val thumbnailUrl: String?,
    val hasCertificate: Boolean,
)
