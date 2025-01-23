package sd.gov.moe.lp.main.provisioning

import sd.gov.moe.lp.data.access.clients.ClientsDao
import sd.gov.moe.lp.data.access.roles.RolesDao
import sd.gov.moe.lp.data.access.users.UsersRepository
import sd.gov.moe.lp.deployment.FirstRunConfig
import sd.gov.moe.lp.dto.models.roles.Privilege
import org.jetbrains.exposed.sql.transactions.transaction
import sd.gov.moe.lp.dto.common.enums.Country

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */

fun registerFirstAdmin() {
    val isClientsTableEmpty = transaction { ClientsDao.getClients(0, 1).isEmpty() }
    if (isClientsTableEmpty.not()) return
    val roleId = transaction { RolesDao.create("Super admin", Privilege.entries) }
    UsersRepository.create(
        FirstRunConfig.adminUsername,
        FirstRunConfig.adminPassword,
        "First run admin",
        Country.Sudan,
        listOf(roleId)
    )
}