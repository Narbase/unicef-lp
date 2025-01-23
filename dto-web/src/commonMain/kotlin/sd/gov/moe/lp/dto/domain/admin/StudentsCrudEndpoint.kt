package sd.gov.moe.lp.dto.domain.admin

import sd.gov.moe.lp.dto.models.ExtendedStudentProfileInfoDto
import sd.gov.moe.lp.router.CrudEndPoint
import kotlin.js.JsExport

@Deprecated("use with actual data")
@JsExport
object StudentsCrudEndpoint : CrudEndPoint<ExtendedStudentProfileInfoDto, Unit>()

