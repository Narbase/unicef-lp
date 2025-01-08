package sd.gov.moe.lp.student.network.calls.settings

import sd.gov.moe.lp.dto.models.roles.DynamicRoleDto
import sd.gov.moe.lp.student.network.crud.CrudServerCaller

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
object AdminRolesServerCaller :
    CrudServerCaller<DynamicRoleDto, Unit>("/api/admin/v1/settings/roles") {

}
