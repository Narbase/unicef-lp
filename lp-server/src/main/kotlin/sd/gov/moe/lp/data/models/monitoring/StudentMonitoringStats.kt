package sd.gov.moe.lp.data.models.monitoring

import java.util.UUID


class StudentMonitoringStats(
    val list: Array<GradeInfo>
) {
    class GradeInfo(
        val grade: UUID,
        val lessonTries: Int,
        val startLesson: Int,
        val currentLesson: Int,
        val daysPlayed: Int,
        val lastPlayed: Long?,
        val devices: Long,
        val assessmentsPassed: Long,
        val assessmentsFailed: Long,
        val minutesPlayed: Double,
    )

    companion object {
        fun emptyStats() = StudentMonitoringStats(
            arrayOf(),
        )
    }
}