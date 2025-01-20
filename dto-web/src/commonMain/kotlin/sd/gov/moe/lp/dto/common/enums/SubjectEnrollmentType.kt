package sd.gov.moe.lp.dto.common.enums

import sd.gov.moe.lp.dto.common.EnumDtoName

enum class SubjectEnrollmentType(persistenceName: String? = null, dtoName: String? = null) :
    EnumPersistenceName,
    EnumDtoName {
    Self,
//    SelfForGrade,
    AutomaticForGrade,
//    AutomaticForAll,
    ;

    override val dtoName: String = dtoName ?: name
    override val persistenceName: String = persistenceName ?: name
}