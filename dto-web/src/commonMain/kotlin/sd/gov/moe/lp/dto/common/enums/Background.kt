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

enum class Background(persistenceName: String? = null, dtoName: String? = null): EnumDtoName, EnumPersistenceName {
    Refugee,
    Host,
    IDPs,
    Other,
    ;

    override val dtoName: String = dtoName ?: name
    override val persistenceName: String = persistenceName ?: name
}
