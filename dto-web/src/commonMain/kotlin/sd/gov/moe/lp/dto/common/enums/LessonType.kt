package sd.gov.moe.lp.dto.common.enums

import sd.gov.moe.lp.dto.common.EnumDtoName

enum class LessonType(override val persistenceName: String, override val dtoName: String) : EnumPersistenceName,
    EnumDtoName {
    PDF("PDF", "PDF"),
    Video("Video", "Video"),
    HTML("HTML", "HTML"),
    Assessment("Assessment", "Assessment"),
    ;
}