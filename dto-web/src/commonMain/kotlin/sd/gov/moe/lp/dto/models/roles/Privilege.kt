package sd.gov.moe.lp.dto.models.roles

import sd.gov.moe.lp.dto.common.EnumDtoName
import sd.gov.moe.lp.dto.common.enums.EnumPersistenceName

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */

enum class Privilege(override val dtoName: String, override val persistenceName: String) : EnumDtoName,
    EnumPersistenceName {
    //todo: the privileges should be the actions allowed in the dashboard

    ;

    companion object {

        val adminAreaPrivileges by lazy {
            Privilege.entries
        }
    }
}