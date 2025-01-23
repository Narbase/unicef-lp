package sd.gov.moe.lp.dto.domain.user.profile

import sd.gov.moe.lp.dto.common.enums.Country
import kotlin.js.JsExport

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
@JsExport
object GetProfileDto {

    class Request

    class Response(
        val profile: UserProfile
    )

    class UserProfile(
        val clientId: String,
        val userId: String,
        val fullName: String,
        val username: String,
        val country: Country,
        val privileges: Array<String>
    )
}