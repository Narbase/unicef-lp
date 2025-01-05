@file:Suppress("unused")

package sd.gov.moe.lp.domain.admin.users.groups

import sd.gov.moe.lp.common.auth.loggedin.AuthorizedClientData
import sd.gov.moe.lp.data.models.utils.ListAndTotal
import sd.gov.moe.lp.domain.user.crud.EndpointCrudController
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.dto.domain.admin.GroupsCrudEndpoint
import sd.gov.moe.lp.dto.models.GroupDto
import java.util.*

class GroupsCrudEndpointController : EndpointCrudController<GroupDto, Unit>(
    listOf(GroupsCrudEndpoint),
    GroupDto::class,
    Unit::class,
) {
    override fun getItemsList(
        pageNo: Long,
        pageSize: Int,
        searchTerm: String,
        filters: Map<String, String>,
        data: Unit?,
        clientData: AuthorizedClientData?
    ): ListAndTotal<GroupDto> {
        return ListAndTotal(list = list, total = list.size.toLong())
    }

    override fun createItem(item: GroupDto, clientData: AuthorizedClientData?): GroupDto {
        val newItem = GroupDto(id = UUID.randomUUID().toStringUUID(), name = item.name)
        list = list.plus(newItem)
        return list.find { it.id == newItem.id } ?: throw IllegalArgumentException("not found")
    }

    override fun updateItem(item: GroupDto, clientData: AuthorizedClientData?): GroupDto {
        list = list.map { if (it.id == item.id) item else it }
        return list.find { it.id == item.id } ?: throw IllegalArgumentException("not found")
    }

    override fun deleteItem(id: UUID?, clientData: AuthorizedClientData?) {
        TODO("Not yet implemented")
    }

}

var list =
    listOf(
        GroupDto(id = UUID.randomUUID().toStringUUID(), name = "group 1"),
        GroupDto(id = UUID.randomUUID().toStringUUID(), name = "group 2"),
        GroupDto(id = UUID.randomUUID().toStringUUID(), name = "group 3"),
        GroupDto(id = UUID.randomUUID().toStringUUID(), name = "group 4"),
        GroupDto(id = UUID.randomUUID().toStringUUID(), name = "group 5"),
    )

