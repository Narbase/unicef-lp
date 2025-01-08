package sd.gov.moe.lp.student.translations.display

import sd.gov.moe.lp.dto.models.roles.Privilege
import sd.gov.moe.lp.student.utils.string.splitCamelCase

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */

val Privilege.displayName: String
    get() {
        return when (this) {
            Privilege.BasicUser -> "Basic User"
            Privilege.UsersManagement -> "Users Management"
            else -> name.splitCamelCase()
        }
    }