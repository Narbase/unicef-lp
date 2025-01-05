package sd.gov.moe.lp.dto.models

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport

@JsExport
data class GroupDto(
    val id: StringUUID?,
    val name: String,
)
