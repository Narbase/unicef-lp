package sd.gov.moe.lp.dto.domain.studentcontrols

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import sd.gov.moe.lp.dto.common.DtoName
import sd.gov.moe.lp.dto.common.enums.StudentStatus
import sd.gov.moe.lp.dto.common.datetime.DateDto
import sd.gov.moe.lp.dto.common.enums.Background
import kotlin.Boolean
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class StudentControlsDto(
	val id: StringUUID?,
	val studentId: StringUUID,
	val status: DtoName<StudentStatus>,
	val groupId: StringUUID,
	val startDate: DateDto,
	val endDate: DateDto,
	val background: DtoName<Background>,
	val isActive: Boolean,
	val isPaused: Boolean,
	val createdOn: DateTimeDto?,
)

