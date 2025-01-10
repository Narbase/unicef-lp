@file:Suppress("unused")

package sd.gov.moe.lp.domain.admin.users.students

import sd.gov.moe.lp.common.DataResponse
import sd.gov.moe.lp.common.EndpointHandler
import sd.gov.moe.lp.common.auth.loggedin.AuthorizedClientData
import sd.gov.moe.lp.dto.common.kmmLongOf
import sd.gov.moe.lp.dto.common.utils.ListAndTotalDto
import sd.gov.moe.lp.dto.domain.admin.GetGradesEndpoint


class GetGradesEndpointController :
    EndpointHandler<GetGradesEndpoint.Request, GetGradesEndpoint.Response>(
        GetGradesEndpoint.Request::class,
        GetGradesEndpoint
    ) {
    override fun process(
        requestDto: GetGradesEndpoint.Request,
        clientData: AuthorizedClientData?
    ): DataResponse<GetGradesEndpoint.Response> {
        val items = gradesList

        return DataResponse(
            GetGradesEndpoint.Response(
                listAndTotal = ListAndTotalDto(
                    list = items.toTypedArray(),
                    total = kmmLongOf(items.size.toLong())
                )
            )
        )
    }
}