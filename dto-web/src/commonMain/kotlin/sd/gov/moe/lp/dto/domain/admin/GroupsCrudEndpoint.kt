package sd.gov.moe.lp.dto.domain.admin

import sd.gov.moe.lp.dto.models.GroupDto
import sd.gov.moe.lp.router.CrudEndPoint
import kotlin.js.JsExport

@JsExport
object GroupsCrudEndpoint : CrudEndPoint<GroupDto, Unit>()

