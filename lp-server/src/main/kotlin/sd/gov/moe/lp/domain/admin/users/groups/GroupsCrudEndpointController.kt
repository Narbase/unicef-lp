@file:Suppress("unused")

package sd.gov.moe.lp.domain.admin.users.groups

import sd.gov.moe.lp.common.auth.loggedin.AuthorizedClientData
import sd.gov.moe.lp.data.models.utils.ListAndTotal
import sd.gov.moe.lp.domain.user.crud.EndpointCrudController
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.dto.domain.admin.GroupsCrudEndpoint
import sd.gov.moe.lp.dto.models.GroupDto
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
        return ListAndTotal(list = groupsList, total = groupsList.size.toLong())
    }

    override fun createItem(item: GroupDto, clientData: AuthorizedClientData?): GroupDto {
        val newItem = GroupDto(id = UUID.randomUUID().toStringUUID(), name = item.name)
        groupsList = groupsList.plus(newItem)
        return groupsList.find { it.id == newItem.id } ?: throw IllegalArgumentException("not found")
    }

    override fun updateItem(item: GroupDto, clientData: AuthorizedClientData?): GroupDto {
        groupsList = groupsList.map { if (it.id == item.id) item else it }
        return groupsList.find { it.id == item.id } ?: throw IllegalArgumentException("not found")
    }

    override fun deleteItem(id: UUID?, clientData: AuthorizedClientData?) {
        TODO("Not yet implemented")
    }

}

var groupsList =
    listOf(
        GroupDto(id = UUID.randomUUID().toStringUUID(), name = "group 1"),
        GroupDto(id = UUID.randomUUID().toStringUUID(), name = "group 2"),
        GroupDto(id = UUID.randomUUID().toStringUUID(), name = "group 3"),
        GroupDto(id = UUID.randomUUID().toStringUUID(), name = "group 4"),
        GroupDto(id = UUID.randomUUID().toStringUUID(), name = "group 5"),
    )

