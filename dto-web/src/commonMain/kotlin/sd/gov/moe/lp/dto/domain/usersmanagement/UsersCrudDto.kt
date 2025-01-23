package sd.gov.moe.lp.dto.domain.usersmanagement

import sd.gov.moe.lp.dto.common.IdDto
import sd.gov.moe.lp.dto.common.enums.Country
import sd.gov.moe.lp.dto.domain.roles.RoleDto
import kotlin.js.JsExport

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
@JsExport
object UsersCrudDto {

    class Filters(
        val getInactive: Boolean?,
        val clientId: IdDto?,
    )


    class User(
        var clientId: IdDto?,
        var userId: IdDto?,
        val username: String,
        val password: String,
        val fullName: String,
        val country: Country,
        val dynamicRoles: Array<RoleDto>
    )
}