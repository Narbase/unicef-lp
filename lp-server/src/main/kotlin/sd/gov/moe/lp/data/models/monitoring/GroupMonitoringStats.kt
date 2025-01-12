package sd.gov.moe.lp.data.models.monitoring

import java.util.UUID


class GroupMonitoringStats(
    val grades: List<UUID>,
    val daysPlayed: Int,
    val lastPlayed: Long?,
    val lastUpload: Long?,
    val activeBoys: Long,
    val totalBoys: Long,
    val activeGirls: Long,
    val totalGirls: Long,
) {
    companion object {
        fun emptyStats() = GroupMonitoringStats(
            listOf(),
            0,
            null,
            null,
            0,
            0,
            0,
            0,
        )
    }
}