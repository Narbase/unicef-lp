package sd.gov.moe.lp.dto.common.enums

import sd.gov.moe.lp.dto.common.EnumDtoName

enum class LessonType(persistenceName: String? = null, dtoName: String? = null, val displayName: String) :
    EnumPersistenceName,
    EnumDtoName {
    PDF(displayName = "Pdf document"),
    Video(displayName = "Video Clip"),
    HTML(displayName = "Html file"),
    GradedAssessment(displayName = "Graded Assessment"),
    NonGradedAssessment(displayName = "Non-Graded Assessment"),
    ;

    override val dtoName: String = dtoName ?: name
    override val persistenceName: String = persistenceName ?: name

    companion object {
        val standardLessons = listOf(PDF, Video, HTML)
    }
}