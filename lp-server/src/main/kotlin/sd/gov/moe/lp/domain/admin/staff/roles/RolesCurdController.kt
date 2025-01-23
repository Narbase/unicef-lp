package sd.gov.moe.lp.domain.admin.staff.roles

import org.jetbrains.exposed.sql.transactions.transaction
import sd.gov.moe.lp.common.auth.loggedin.AuthorizedClientData
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.data.access.roles.RolesDao
import sd.gov.moe.lp.data.conversions.roles.toDto
import sd.gov.moe.lp.data.models.utils.ListAndTotal
import sd.gov.moe.lp.domain.user.crud.CrudController
import sd.gov.moe.lp.dto.common.enum
import sd.gov.moe.lp.dto.domain.roles.RoleDto
import java.util.*

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */


class RolesCurdController : CrudController<RoleDto, Unit>(RoleDto::class, Unit::class) {

    private val dao = RolesDao

    override fun getItemsList(
        pageNo: Long,
        pageSize: Int,
        searchTerm: String,
        filters: Map<String, String>,
        data: Unit?,
        clientData: AuthorizedClientData?
    ): ListAndTotal<sd.gov.moe.lp.dto.domain.roles.RoleDto> {
        val listAndCount = transaction { dao.getList(pageNo, pageSize, searchTerm) }
        return ListAndTotal(listAndCount.list.map { it.toDto() }, listAndCount.total)
    }

    override fun createItem(item: RoleDto, clientData: AuthorizedClientData?): RoleDto {
        return transaction {
            val id = dao.create(item.name, item.role.privileges.map { it.enum() })
            val model = dao.get(id)
            model.toDto()
        }
    }

    override fun updateItem(item: RoleDto, clientData: AuthorizedClientData?): RoleDto {
        return transaction {
            val id = item.id!!
            dao.update(id.toUUID(), item.name, item.role.privileges.map { it.enum() })
            val model = dao.get(id.toUUID())
            model.toDto()
        }
    }

    override fun deleteItem(id: UUID?, clientData: AuthorizedClientData?) {
        transaction { dao.delete(id!!) }
    }
}