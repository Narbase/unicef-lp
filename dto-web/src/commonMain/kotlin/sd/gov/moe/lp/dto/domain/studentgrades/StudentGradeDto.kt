package sd.gov.moe.lp.dto.domain.studentgrades

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import kotlin.Double
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class StudentGradeDto(
	val id: StringUUID?,
	val studentId: StringUUID,
	val gradeId: StringUUID,
	val progress: Double,
	val createdOn: DateTimeDto?,
)

