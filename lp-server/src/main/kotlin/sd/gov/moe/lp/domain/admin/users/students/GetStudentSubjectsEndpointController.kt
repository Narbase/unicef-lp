@file:Suppress("unused")

package sd.gov.moe.lp.domain.admin.users.students

import org.joda.time.DateTime
import sd.gov.moe.lp.common.DataResponse
import sd.gov.moe.lp.common.EndpointHandler
import sd.gov.moe.lp.common.auth.loggedin.AuthorizedClientData
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.dto.common.kmmLongOf
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.dto.common.utils.ListAndTotalDto
import sd.gov.moe.lp.dto.domain.admin.GetStudentSubjectsEndpoint
import sd.gov.moe.lp.dto.domain.studentgradedassessments.StudentGradedAssessmentDto
import sd.gov.moe.lp.dto.domain.studentsubjects.StudentSubjectDto
import sd.gov.moe.lp.dto.domain.subjects.SubjectDto
import sd.gov.moe.lp.dto.models.ExtendedStudentSubjectDto
import java.util.*


class GetStudentSubjectsEndpointController :
    EndpointHandler<GetStudentSubjectsEndpoint.Request, GetStudentSubjectsEndpoint.Response>(
        GetStudentSubjectsEndpoint.Request::class,
        GetStudentSubjectsEndpoint
    ) {
    override fun process(
        requestDto: GetStudentSubjectsEndpoint.Request,
        clientData: AuthorizedClientData?
    ): DataResponse<GetStudentSubjectsEndpoint.Response> {

        return DataResponse(
            GetStudentSubjectsEndpoint.Response(
                listAndTotalDto = ListAndTotalDto(
                    list = studentSubjects,
                    total = kmmLongOf(studentSubjects.size.toLong()),
                )
            )
        )
    }
}

val studentSubjects = arrayOf(
    ExtendedStudentSubjectDto(
        subject = SubjectDto(
            id = UUID.randomUUID().toStringUUID(),
            name = "s1",
            description = "no",
            thumbnailId = null,
            hasCertificate = true,
            isLessonsOrderRestrictive = false,
            feedbackFormId = null,
            createdOn = null
        ),
        studentSubject = StudentSubjectDto(
            id = UUID.randomUUID().toStringUUID(),
            studentId = studentsList.random().student.id!!,
            subjectId = UUID.randomUUID().toStringUUID(),
            progress = 39.0,
            completedOn = null,
            createdOn = DateTime.now().minusDays(852).toDto(),
            hasCertificate = false,
            feedback = null,
        ),
        studentSubjectAssessments = arrayOf(

        )
    ),
)