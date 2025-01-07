package sd.gov.moe.lp.dto.models

import kotlin.js.JsExport

@Deprecated("replace with narrator")
@JsExport
data class ExtendedStudentProfileInfoDto(
    val student: ExtendedStudentDto,
    val client: ClientDto,
)
