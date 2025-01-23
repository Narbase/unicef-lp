package sd.gov.moe.lp.dto.common.enums

import sd.gov.moe.lp.dto.common.EnumDtoName

enum class Country(persistenceName: String? = null, dtoName: String? = null) : EnumPersistenceName, EnumDtoName {
    Sudan,
    Egypt,
    ;

    override val dtoName: String = dtoName ?: name
    override val persistenceName: String = persistenceName ?: name
}