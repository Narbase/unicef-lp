package sd.gov.moe.lp.admin.views.admin.users.students.reportCard.tables

import com.narbase.kunafa.core.lifecycle.Observable
import sd.gov.moe.lp.admin.network.basicNetworkCall
import sd.gov.moe.lp.admin.network.remoteProcess
import sd.gov.moe.lp.admin.utils.BasicUiState
import sd.gov.moe.lp.dto.common.StringUUID
import sd.gov.moe.lp.dto.domain.admin.GetStudentSubjectsEndpoint
import sd.gov.moe.lp.dto.models.ExtendedStudentSubjectDto

class StudentSubjectsTableViewModel {
    private var searchTerm = ""
    val uiState = Observable<BasicUiState>()
    val upsertUiState = Observable<BasicUiState>()
    var data: List<ExtendedStudentSubjectDto> = listOf()

    var pageNo = 0
    var pageSize = 20
    var total = 0

    var studentId: StringUUID? = null
    fun setStudentId(id: StringUUID) {
        studentId = id
    }

    fun getItems() {
        upsertUiState.value = null
        val id = studentId ?: return
        basicNetworkCall(uiState) {
            val dto = GetStudentSubjectsEndpoint.Request(
                studentId = id,
                pageNo,
                pageSize,
                searchTerm = searchTerm,
            )
            val response = GetStudentSubjectsEndpoint.remoteProcess(dto).data.listAndTotalDto
            data = response.list.toList()
            total = response.total.toInt()
        }
    }

    fun searchFor(term: String) {
        pageNo = 0
        searchTerm = term
        uiState.value = BasicUiState.Loaded
        getItems()
    }

    fun getNextPage() {
        pageNo++
        getItems()
    }

    fun getPreviousPage() {
        pageNo--
        getItems()
    }

}
