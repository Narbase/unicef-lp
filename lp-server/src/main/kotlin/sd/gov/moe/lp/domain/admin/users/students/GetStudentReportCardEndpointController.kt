@file:Suppress("unused")

package sd.gov.moe.lp.domain.admin.users.students

import sd.gov.moe.lp.common.DataResponse
import sd.gov.moe.lp.common.EndpointHandler
import sd.gov.moe.lp.common.auth.loggedin.AuthorizedClientData
import sd.gov.moe.lp.dto.domain.admin.GetStudentReportCardEndpoint


class GetStudentReportCardEndpointController :
    EndpointHandler<GetStudentReportCardEndpoint.Request, GetStudentReportCardEndpoint.Response>(
        GetStudentReportCardEndpoint.Request::class,
        GetStudentReportCardEndpoint
    ) {
    override fun process(
        requestDto: GetStudentReportCardEndpoint.Request,
        clientData: AuthorizedClientData?
    ): DataResponse<GetStudentReportCardEndpoint.Response> {

        TODO("Not yet implemented")
    }
}