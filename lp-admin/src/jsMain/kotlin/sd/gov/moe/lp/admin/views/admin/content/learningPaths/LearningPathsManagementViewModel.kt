package sd.gov.moe.lp.admin.views.admin.content.learningPaths

import com.narbase.kunafa.core.lifecycle.Observable
import sd.gov.moe.lp.dto.common.network.CommonCodes.BASIC_SUCCESS
import sd.gov.moe.lp.dto.common.network.crud.CrudDto
import sd.gov.moe.lp.admin.network.*
import sd.gov.moe.lp.admin.utils.BasicUiState
import sd.gov.moe.lp.dto.domain.admin.LearningPathsCrudEndpoint
import sd.gov.moe.lp.dto.models.LearningPathDto

class LearningPathsManagementViewModel {
    private var searchTerm = ""
    val uiState = Observable<BasicUiState>()
    val upsertUiState = Observable<BasicUiState>()
    val getStudentsUiState = Observable<BasicUiState>()
    val filters = LearningPathsCrudEndpoint.Filters()
    var data: List<LearningPathDto> = listOf()

    var pageNo = 0
    var pageSize = 20
    var total = 0

    fun getPaths() {
        upsertUiState.value = null
        basicNetworkCall(uiState) {
            val dto = CrudDto.GetList.Request(
                pageNo, pageSize,
                searchTerm = searchTerm,
                data = filters
            )
            val response = LearningPathsCrudEndpoint.remoteList(dto).data
            data = response.list.toList()
            total = response.total.toInt()
        }
    }

    fun searchFor(term: String) {
        pageNo = 0
        searchTerm = term
        uiState.value = BasicUiState.Loaded
        getPaths()
    }

    fun getNextPage() {
        pageNo++
        getPaths()
    }

    fun getPreviousPage() {
        pageNo--
        getPaths()
    }

    fun addPath(dto: LearningPathDto) {
        basicNetworkCall(upsertUiState) {
            val response = LearningPathsCrudEndpoint.remoteAdd(dto)
            if (response.status != BASIC_SUCCESS) {
                throw UnknownErrorException()
            }
        }

    }

    fun editPath(dto: LearningPathDto) {
        basicNetworkCall(upsertUiState) {
            val response = LearningPathsCrudEndpoint.remoteUpdate(dto)
            if (response.status != BASIC_SUCCESS) {
                throw UnknownErrorException()
            }
        }

    }

}
