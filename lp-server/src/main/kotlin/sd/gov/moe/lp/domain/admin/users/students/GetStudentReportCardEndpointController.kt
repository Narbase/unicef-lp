@file:Suppress("unused")

package sd.gov.moe.lp.domain.admin.users.students

import sd.gov.moe.lp.common.DataResponse
import sd.gov.moe.lp.common.EndpointHandler
import sd.gov.moe.lp.common.auth.loggedin.AuthorizedClientData
import sd.gov.moe.lp.domain.admin.users.groups.groupsList
import sd.gov.moe.lp.domain.admin.users.paths.learningPathList
import sd.gov.moe.lp.dto.domain.admin.GetStudentReportCardEndpoint
import sd.gov.moe.lp.dto.models.*


class GetStudentReportCardEndpointController :
    EndpointHandler<GetStudentReportCardEndpoint.Request, GetStudentReportCardEndpoint.Response>(
        GetStudentReportCardEndpoint.Request::class,
        GetStudentReportCardEndpoint
    ) {
    override fun process(
        requestDto: GetStudentReportCardEndpoint.Request,
        clientData: AuthorizedClientData?
    ): DataResponse<GetStudentReportCardEndpoint.Response> {

        val student = studentsList.first { it.student.id == requestDto.studentId }
        return DataResponse(
            GetStudentReportCardEndpoint.Response(
                extendedStudentReportCard = ExtendedStudentReportCardDto(
                    studentProfile = student,
                    studentSubjects = studentSubjects,
                    studentGroupsCount = groupsList.size,
                    studentLearningPathsCount = learningPathList.size,
                )
            )
        )
    }
}