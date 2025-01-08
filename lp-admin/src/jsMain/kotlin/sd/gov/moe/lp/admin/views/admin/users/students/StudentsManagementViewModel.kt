package sd.gov.moe.lp.admin.views.admin.users.students

import com.narbase.kunafa.core.lifecycle.Observable
import sd.gov.moe.lp.dto.common.network.CommonCodes.BASIC_SUCCESS
import sd.gov.moe.lp.dto.common.network.crud.CrudDto
import sd.gov.moe.lp.dto.domain.admin.StudentsCrudEndpoint
import sd.gov.moe.lp.dto.models.ExtendedStudentProfileInfoDto
import sd.gov.moe.lp.admin.network.*
import sd.gov.moe.lp.admin.utils.BasicUiState

class StudentsManagementViewModel {
    private var searchTerm = ""
    val uiState = Observable<BasicUiState>()
    val upsertUiState = Observable<BasicUiState>()
    var data: List<ExtendedStudentProfileInfoDto> = listOf()

    var pageNo = 0
    var pageSize = 20
    var total = 0

    fun getStudents() {
        upsertUiState.value = null
        basicNetworkCall(uiState) {
            val dto = CrudDto.GetList.Request(
                pageNo, pageSize,
                searchTerm = searchTerm,
                data = Unit
            )
            val response = StudentsCrudEndpoint.remoteList(dto).data
            data = response.list.toList()
            total = response.total
        }
    }

    fun searchFor(term: String) {
        pageNo = 0
        searchTerm = term
        uiState.value = BasicUiState.Loaded
        getStudents()
    }

    fun getNextPage() {
        pageNo++
        getStudents()
    }

    fun getPreviousPage() {
        pageNo--
        getStudents()
    }

    fun addStudent(dto: ExtendedStudentProfileInfoDto) {
        basicNetworkCall(upsertUiState) {
            val response = StudentsCrudEndpoint.remoteAdd(dto)
            if (response.status != BASIC_SUCCESS) {
                throw UnknownErrorException()
            }
        }

    }

    fun editStudent(dto: ExtendedStudentProfileInfoDto) {
        basicNetworkCall(upsertUiState) {
            val response = StudentsCrudEndpoint.remoteUpdate(dto)
            if (response.status != BASIC_SUCCESS) {
                throw UnknownErrorException()
            }
        }

    }

}
