package sd.gov.moe.lp.dto.common.enums

import sd.gov.moe.lp.dto.common.EnumDtoName

enum class ActivityType(persistenceName: String? = null, dtoName: String? = null) : EnumPersistenceName,
    EnumDtoName {
    Login,
    Lesson,
    Enrollment,
    Logout,
    ;

    override val dtoName: String = dtoName ?: name
    override val persistenceName: String = persistenceName ?: name
}