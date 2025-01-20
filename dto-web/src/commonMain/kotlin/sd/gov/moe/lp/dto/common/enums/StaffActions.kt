package sd.gov.moe.lp.dto.common.enums

import sd.gov.moe.lp.dto.common.EnumDtoName

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */

enum class StaffActions(persistenceName: String? = null, dtoName: String? = null) : EnumPersistenceName, EnumDtoName {
    Locality,
    AdministrativeUnit,
    Center,
    Group,
    Grade,
    Subject,
    Lesson,
    Student,
    Staff,
    Partner,
    Teacher,
    ;

    override val dtoName: String = dtoName ?: name
    override val persistenceName: String = persistenceName ?: name

}