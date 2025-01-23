package sd.gov.moe.lp.dto.domain.groups

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import kotlin.String
import sd.gov.moe.lp.dto.common.datetime.DateDto
import sd.gov.moe.lp.dto.common.DtoName
import sd.gov.moe.lp.dto.common.enums.Education
import kotlin.Boolean
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class GroupDto(
	val id: StringUUID?,
	val name: String,
	val centerId: StringUUID,
	val teacherId: StringUUID,
	val startDate: DateDto,
	val endDate: DateDto,
	val education: DtoName<Education>,
	val partnerId: StringUUID,
	val isDeleteRequested: Boolean,
	val createdOn: DateTimeDto?,
)

