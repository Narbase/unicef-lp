package sd.gov.moe.lp.dto.domain.admin

import sd.gov.moe.lp.dto.common.StringUUID
import sd.gov.moe.lp.dto.models.GroupDto
import sd.gov.moe.lp.router.CrudEndPoint
import kotlin.js.JsExport

@Deprecated("use with actual data")
@JsExport
object GroupsCrudEndpoint : CrudEndPoint<GroupDto, GroupsCrudEndpoint.Filters>() {
    class Filters(
        var studentId: StringUUID? = null
    )
}

