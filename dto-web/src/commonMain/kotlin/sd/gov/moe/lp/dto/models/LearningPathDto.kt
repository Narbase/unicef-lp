package sd.gov.moe.lp.dto.models

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport

@Deprecated("replace with narrator")
@JsExport
data class LearningPathDto(
    val id: StringUUID?,
    val name: String,
)
