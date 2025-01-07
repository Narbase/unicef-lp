package sd.gov.moe.lp.dto.models

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport

@Deprecated("replace with narrator")
@JsExport
data class ClientDto(
    val id: StringUUID?,
    val userName: String,
    val password: String,
)
