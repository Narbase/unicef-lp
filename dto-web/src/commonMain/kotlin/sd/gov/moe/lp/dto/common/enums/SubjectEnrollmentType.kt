package sd.gov.moe.lp.dto.common.enums

import sd.gov.moe.lp.dto.common.EnumDtoName

enum class SubjectEnrollmentType(override val persistenceName: String, override val dtoName: String) : EnumPersistenceName,
    EnumDtoName {
    Admin("Admin", "Admin"),
    Self("Self", "Self"),
    Automatic("Automatic", "Automatic"),
    ;
}