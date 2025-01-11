package sd.gov.moe.lp.dto.domain.admin

import sd.gov.moe.lp.dto.common.StringUUID
import sd.gov.moe.lp.dto.common.utils.ListAndTotalDto
import sd.gov.moe.lp.dto.models.ExtendedStudentReportCardDto
import sd.gov.moe.lp.dto.models.ExtendedStudentSubjectDto
import sd.gov.moe.lp.dto.models.GradeDto
import sd.gov.moe.lp.router.EndPoint
import kotlin.js.JsExport

@Deprecated("use with actual data")
@JsExport
object GetStudentSubjectsEndpoint :
    EndPoint<GetStudentSubjectsEndpoint.Request, GetStudentSubjectsEndpoint.Response>() {
    class Request(
        val studentId: StringUUID,
        val pageNo: Int = 0,
        val pageSize: Int? = null,
        val searchTerm: String? = "",
    )

    class Response(
        val listAndTotalDto: ListAndTotalDto<ExtendedStudentSubjectDto>,
    )
}

