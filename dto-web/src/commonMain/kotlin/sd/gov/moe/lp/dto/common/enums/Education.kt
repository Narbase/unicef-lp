package sd.gov.moe.lp.dto.common.enums

import sd.gov.moe.lp.dto.common.EnumDtoName

enum class Education(override val dtoName: String, override val persistenceName: String) : EnumDtoName, EnumPersistenceName {
    Unknown("Unknown", "Unknown"),
    Formal("Formal", "Formal"),
    Informal("Informal", "Informal")
}