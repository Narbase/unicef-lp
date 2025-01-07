package sd.gov.moe.lp.dto.domain.admin

import sd.gov.moe.lp.dto.common.utils.ListAndTotalDto
import sd.gov.moe.lp.dto.models.GradeDto
import sd.gov.moe.lp.router.EndPoint
import kotlin.js.JsExport

@Deprecated("use with actual data")
@JsExport
object GetGradesEndpoint : EndPoint<GetGradesEndpoint.Request, GetGradesEndpoint.Response>() {
    class Request(
        val pageNo: Int = 0,
        val pageSize: Int? = null,
        val searchTerm: String? = "",
    )

    class Response(
        val listAndTotal: ListAndTotalDto<GradeDto>,
    )
}

