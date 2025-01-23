package sd.gov.moe.lp.data.models.monitoring

import sd.gov.moe.lp.dto.common.StringUUID
import java.util.UUID


class StudentMonitoringStats(
    val list: Array<SubjectInfo>
) {
    class SubjectInfo(
        val grade: StringUUID,
        val subject: StringUUID,
        val startLesson: StringUUID,
        val currentLesson: StringUUID,
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