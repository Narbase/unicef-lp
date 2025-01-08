package sd.gov.moe.lp.admin.views.admin.users.groups

import com.narbase.kunafa.core.lifecycle.Observable
import sd.gov.moe.lp.dto.common.StringUUID
import sd.gov.moe.lp.dto.common.network.CommonCodes.BASIC_SUCCESS
import sd.gov.moe.lp.dto.common.network.crud.CrudDto
import sd.gov.moe.lp.dto.domain.admin.GroupsCrudEndpoint
import sd.gov.moe.lp.dto.models.GroupDto
import sd.gov.moe.lp.admin.network.*
import sd.gov.moe.lp.admin.utils.BasicUiState

class GroupsManagementViewModel {
    private var searchTerm = ""
    val uiState = Observable<BasicUiState>()
    val upsertUiState = Observable<BasicUiState>()
    val getStudentsUiState = Observable<BasicUiState>()
    var data: List<GroupDto> = listOf()

    var pageNo = 0
    var pageSize = 20
    var total = 0

    fun getGroups() {
        upsertUiState.value = null
        basicNetworkCall(uiState) {
            val dto = CrudDto.GetList.Request(
                pageNo, pageSize,
                searchTerm = searchTerm,
                data = Unit
            )
            val response = GroupsCrudEndpoint.remoteList(dto).data
            data = response.list.toList()
            total = response.total.toInt()
        }
    }

    fun searchFor(term: String) {
        pageNo = 0
        searchTerm = term
        uiState.value = BasicUiState.Loaded
        getGroups()
    }

    fun getNextPage() {
        pageNo++
        getGroups()
    }

    fun getPreviousPage() {
        pageNo--
        getGroups()
    }

    fun addGroup(dto: GroupDto) {
        basicNetworkCall(upsertUiState) {
            val response = GroupsCrudEndpoint.remoteAdd(dto)
            if (response.status != BASIC_SUCCESS) {
                throw UnknownErrorException()
            }
        }

    }

    fun editGroup(dto: GroupDto) {
        basicNetworkCall(upsertUiState) {
            val response = GroupsCrudEndpoint.remoteUpdate(dto)
            if (response.status != BASIC_SUCCESS) {
                throw UnknownErrorException()
            }
        }

    }

    fun getGroupMembers(id: StringUUID?) {
        basicNetworkCall(getStudentsUiState) {

        }
    }

}
