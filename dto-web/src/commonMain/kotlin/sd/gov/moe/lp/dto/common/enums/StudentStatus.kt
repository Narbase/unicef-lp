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
enum class StudentStatus(persistenceName: String? = null, dtoName: String? = null, val displayName: String) :
    EnumDtoName, EnumPersistenceName {
    Active("Active", "Active", "Active"),
    ActiveBeforeEnrollment(
        "ActiveBeforeEnrollment",
        "ActiveBeforeEnrollment",
        "Active before enrollment"
    ),  //todo rename this status
    Deactivated("Deactivated", "Deactivated", "Deactivated"),
    FinishedEnrollment("FinishedEnrollment", "FinishedEnrollment", "Finished enrollment"),
    NeverPlayed("NeverPlayed", "NeverPlayed", "No data uploaded"),
    Paused("Paused", "Paused", "Paused"),
    ;

    override val dtoName: String = dtoName ?: name
    override val persistenceName: String = persistenceName ?: name
}