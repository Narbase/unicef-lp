package sd.gov.moe.lp.data.models.monitoring

import java.util.UUID


class StudentMonitoringStats(
    val list: Array<SubjectInfo>
) {
    class SubjectInfo(
        val grade: UUID,
        val subject: UUID,
        val startLesson: UUID,
        val currentLesson: UUID,
        val daysPlayed: Int,
        val lastPlayed: Long?,
        val devices: Long,
        val assessmentsTries: Int,
        val assessmentsPassed: Long,
        val assessmentsFailed: Long,
        val assessmentsScore: Double,
        val minutesPlayed: Double,
    )

    companion object {
        fun emptyStats() = StudentMonitoringStats(
            arrayOf(),
        )
    }
}