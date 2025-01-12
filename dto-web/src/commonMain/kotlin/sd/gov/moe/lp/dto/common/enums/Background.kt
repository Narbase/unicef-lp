package sd.gov.moe.lp.dto.common.enums

import sd.gov.moe.lp.dto.common.EnumDtoName


/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2017] - [2021] Narbase Technologies
 * All Rights Reserved.
 * Created by amel
 * On: 7/29/21.
 */

enum class Background(override val dtoName: String, override val persistenceName: String): EnumDtoName, EnumPersistenceName {
    Refugee("Refugee", "Refugee"),
    Host("Host", "Host"),
    IDPs("IDPs", "IDPs"),
    Other("Other", "Other"),
}
