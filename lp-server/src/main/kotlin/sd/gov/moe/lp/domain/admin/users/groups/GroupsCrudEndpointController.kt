@file:Suppress("unused")

package sd.gov.moe.lp.domain.admin.users.groups

import sd.gov.moe.lp.common.auth.loggedin.AuthorizedClientData
import sd.gov.moe.lp.data.models.utils.ListAndTotal
import sd.gov.moe.lp.domain.user.crud.EndpointCrudController
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.dto.domain.admin.GroupsCrudEndpoint
import sd.gov.moe.lp.dto.domain.groups.GroupDto
import java.util.*

class GroupsCrudEndpointController : EndpointCrudController<GroupDto, GroupsCrudEndpoint.Filters>(
    listOf(GroupsCrudEndpoint),
    GroupDto::class,
    GroupsCrudEndpoint.Filters::class,
) {
    override fun getItemsList(
        pageNo: Long,
        pageSize: Int,
        searchTerm: String,
        filters: Map<String, String>,
        data: GroupsCrudEndpoint.Filters?,
        clientData: AuthorizedClientData?
    ): ListAndTotal<GroupDto> {
        TODO("Not yet implemented")
    }

    override fun createItem(item: GroupDto, clientData: AuthorizedClientData?): GroupDto {
        TODO("Not yet implemented")
    }

    override fun updateItem(item: GroupDto, clientData: AuthorizedClientData?): GroupDto {
        TODO("Not yet implemented")
    }

    override fun deleteItem(id: UUID?, clientData: AuthorizedClientData?) {
        TODO("Not yet implemented")
    }

}

