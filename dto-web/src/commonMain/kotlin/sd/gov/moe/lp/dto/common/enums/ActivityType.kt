package sd.gov.moe.lp.dto.common.enums

import sd.gov.moe.lp.dto.common.EnumDtoName

enum class ActivityType(override val persistenceName: String, override val dtoName: String) : EnumPersistenceName,
    EnumDtoName {
    Login("Login", "Login"),
    Lesson("Lesson", "Lesson"),
    Logout("Logout", "Logout"),
    ;
}