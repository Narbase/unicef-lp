@file:Suppress("unused")

package sd.gov.moe.lp.domain.admin.users.students

import sd.gov.moe.lp.common.auth.loggedin.AuthorizedClientData
import sd.gov.moe.lp.data.models.utils.ListAndTotal
import sd.gov.moe.lp.domain.user.crud.EndpointCrudController
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.dto.domain.admin.StudentsCrudEndpoint
import sd.gov.moe.lp.dto.domain.clients.ClientDto
import sd.gov.moe.lp.dto.domain.grades.GradeDto
import sd.gov.moe.lp.dto.models.ExtendedStudentProfileInfoDto
import sd.gov.moe.lp.dto.models.ExtendedStudentDto
import java.util.*

class StudentsCrudEndpointController : EndpointCrudController<ExtendedStudentProfileInfoDto, Unit>(
    listOf(StudentsCrudEndpoint),
    ExtendedStudentProfileInfoDto::class,
    Unit::class,
) {
    override fun getItemsList(
        pageNo: Long,
        pageSize: Int,
        searchTerm: String,
        filters: Map<String, String>,
        data: Unit?,
        clientData: AuthorizedClientData?
    ): ListAndTotal<ExtendedStudentProfileInfoDto> {
        return ListAndTotal(list = studentsList, total = studentsList.size.toLong())
    }

    override fun createItem(
        item: ExtendedStudentProfileInfoDto,
        clientData: AuthorizedClientData?
    ): ExtendedStudentProfileInfoDto {
        val newItem =
            ExtendedStudentProfileInfoDto(
                student = ExtendedStudentDto(
                    id = UUID.randomUUID().toStringUUID(),
                    fullName = item.student.fullName,
                    grade = item.student.grade,
                    thumbnailUrl = item.student.thumbnailUrl,
                ), client = ClientDto(
                    id = UUID.randomUUID().toStringUUID(),
                    username = item.client.username,
                    passwordHash = item.client.passwordHash,
                    lastLogin = null,
                    createdOn = null,
                )
            )
        studentsList = studentsList.plus(newItem)
        return studentsList.find { it.client.id == newItem.client.id } ?: throw IllegalArgumentException("not found")
    }

    override fun updateItem(
        item: ExtendedStudentProfileInfoDto,
        clientData: AuthorizedClientData?
    ): ExtendedStudentProfileInfoDto {
        studentsList = studentsList.map { if (it.client.id == item.client.id) item else it }
        return studentsList.find { it.client.id == item.client.id } ?: throw IllegalArgumentException("not found")
    }

    override fun deleteItem(id: UUID?, clientData: AuthorizedClientData?) {
        TODO("Not yet implemented")
    }

}

var gradesList = listOf(
    GradeDto(
        id = UUID.randomUUID().toStringUUID(),
        name = "Grade 1",
        thumbnailId = null,
        createdOn = null
    )
)

var studentsList =
    listOf(
        ExtendedStudentProfileInfoDto(
            student = ExtendedStudentDto(
                id = UUID.randomUUID().toStringUUID(),
                fullName = "first student full name",
                grade = gradesList.random(),
                thumbnailUrl = null,
            ),
            ClientDto(
                id = UUID.randomUUID().toStringUUID(),
                username = "first student user name",
                passwordHash = "first student password",
                lastLogin = null,
                createdOn = null
            )
        ),
    )
