@file:Suppress("unused")

package sd.gov.moe.lp.domain.admin.users.students

import org.joda.time.DateTime
import sd.gov.moe.lp.common.DataResponse
import sd.gov.moe.lp.common.EndpointHandler
import sd.gov.moe.lp.common.auth.loggedin.AuthorizedClientData
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto
import sd.gov.moe.lp.dto.common.kmmLongOf
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.dto.common.utils.ListAndTotalDto
import sd.gov.moe.lp.dto.domain.admin.GetStudentReportCardEndpoint
import sd.gov.moe.lp.dto.models.*
import java.util.UUID


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
                    studentSubjects = arrayOf(
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
                                ) ,
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
                                    progress = 100.0,
                                ) ,
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
                    ),
                    studentGroups = emptyArray(),
                    studentLearningPaths = emptyArray(),
                )
            )
        )
    }
}