package sd.gov.moe.lp.dto.domain.admin

import sd.gov.moe.lp.dto.common.StringUUID
import sd.gov.moe.lp.dto.common.utils.ListAndTotalDto
import sd.gov.moe.lp.dto.models.ExtendedStudentReportCardDto
import sd.gov.moe.lp.dto.models.ExtendedStudentSubjectDto
import sd.gov.moe.lp.router.EndPoint
import kotlin.js.JsExport

@Deprecated("use with actual data")
@JsExport
object GetStudentReportCardEndpoint :
    EndPoint<GetStudentReportCardEndpoint.Request, GetStudentReportCardEndpoint.Response>() {
    class Request(
        val studentId: StringUUID,
    )

    class Response(
        val extendedStudentReportCard: ExtendedStudentReportCardDto,
    )
}

