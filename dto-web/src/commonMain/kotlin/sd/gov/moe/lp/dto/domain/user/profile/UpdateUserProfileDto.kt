package sd.gov.moe.lp.dto.domain.user.profile

import sd.gov.moe.lp.dto.common.DtoName
import sd.gov.moe.lp.dto.common.enums.Country
import kotlin.js.JsExport

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
@JsExport
object UpdateUserProfileDto {
    class RequestDto(
        val fullName: String,
        val country: DtoName<Country>,
    )

    class ResponseDto
}
