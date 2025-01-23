package sd.gov.moe.lp.data.models.monitoring

import sd.gov.moe.lp.dto.common.StringUUID
import java.util.UUID


class GroupMonitoringStats(
    val grades: List<StringUUID>,
    val subjects: List<StringUUID>,
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