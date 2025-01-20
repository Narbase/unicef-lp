package sd.gov.moe.lp.dto.common.enums

import sd.gov.moe.lp.dto.common.EnumDtoName

enum class Education(persistenceName: String? = null, dtoName: String? = null) : EnumDtoName, EnumPersistenceName {
    Unknown,
    Formal,
    Informal,
    ;

    override val dtoName: String = dtoName ?: name
    override val persistenceName: String = persistenceName ?: name

}