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
import sd.gov.moe.lp.dto.models.ExtendedStudentSubjectDto
import sd.gov.moe.lp.dto.models.StudentAssessmentDto
import sd.gov.moe.lp.dto.models.StudentSubjectDto
import sd.gov.moe.lp.dto.models.SubjectDto
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
            gradeId = gradesList.random().id!!,
            name = "s1",
            description = "no",
            thumbnailUrl = null,
            hasCertificate = true
        ),
        studentSubject = StudentSubjectDto(
            id = UUID.randomUUID().toStringUUID(),
            studentId = studentsList.random().student.id!!,
            subjectId = UUID.randomUUID().toStringUUID(),
            progress = 39.0,
            completedOn = null,
            enrolledOn = DateTime.now().minusDays(852).toDto(),
        ),
        studentSubjectAssessments = arrayOf(
            StudentAssessmentDto(
                id = UUID.randomUUID().toStringUUID(),
                studentId = studentsList.random().student.id!!,
                assessmentId = UUID.randomUUID().toStringUUID(),
                progress = 100.0,
            ),
            StudentAssessmentDto(
                id = UUID.randomUUID().toStringUUID(),
                studentId = studentsList.random().student.id!!,
                assessmentId = UUID.randomUUID().toStringUUID(),
                progress = 40.0,
            ),
            StudentAssessmentDto(
                id = UUID.randomUUID().toStringUUID(),
                studentId = studentsList.random().student.id!!,
                assessmentId = UUID.randomUUID().toStringUUID(),
                progress = 0.0,
            ),
        )
    ),
    ExtendedStudentSubjectDto(
        subject = SubjectDto(
            id = UUID.randomUUID().toStringUUID(),
            gradeId = gradesList.random().id!!,
            name = "s2",
            description = null,
            thumbnailUrl = null,
            hasCertificate = false
        ),
        studentSubject = StudentSubjectDto(
            id = UUID.randomUUID().toStringUUID(),
            studentId = studentsList.random().student.id!!,
            subjectId = UUID.randomUUID().toStringUUID(),
            progress = 100.0,
            completedOn = null,
            enrolledOn = DateTime.now().minusDays(852).toDto(),
        ),
        studentSubjectAssessments = arrayOf(
            StudentAssessmentDto(
                id = UUID.randomUUID().toStringUUID(),
                studentId = studentsList.random().student.id!!,
                assessmentId = UUID.randomUUID().toStringUUID(),
                progress = 50.0,
            ),
            StudentAssessmentDto(
                id = UUID.randomUUID().toStringUUID(),
                studentId = studentsList.random().student.id!!,
                assessmentId = UUID.randomUUID().toStringUUID(),
                progress = 40.0,
            ),
            StudentAssessmentDto(
                id = UUID.randomUUID().toStringUUID(),
                studentId = studentsList.random().student.id!!,
                assessmentId = UUID.randomUUID().toStringUUID(),
                progress = 0.0,
            ),
        )
    )
)