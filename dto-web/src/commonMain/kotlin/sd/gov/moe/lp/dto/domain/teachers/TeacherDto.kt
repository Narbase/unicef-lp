package sd.gov.moe.lp.dto.domain.teachers

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import sd.gov.moe.lp.dto.common.DtoName
import sd.gov.moe.lp.dto.common.enums.Gender
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class TeacherDto(
	val id: StringUUID?,
	val gender: DtoName<Gender>,
	val centerId: StringUUID,
	val staffId: StringUUID,
	val createdOn: DateTimeDto?,
)

