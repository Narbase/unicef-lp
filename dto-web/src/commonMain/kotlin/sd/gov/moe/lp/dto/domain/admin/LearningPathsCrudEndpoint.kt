package sd.gov.moe.lp.dto.domain.admin

import sd.gov.moe.lp.dto.common.StringUUID
import sd.gov.moe.lp.dto.models.GroupDto
import sd.gov.moe.lp.dto.models.LearningPathDto
import sd.gov.moe.lp.router.CrudEndPoint
import kotlin.js.JsExport

@Deprecated("use with actual data")
@JsExport
object LearningPathsCrudEndpoint : CrudEndPoint<LearningPathDto, LearningPathsCrudEndpoint.Filters>() {
    class Filters(
        val studentId: StringUUID? = null
    )
}

