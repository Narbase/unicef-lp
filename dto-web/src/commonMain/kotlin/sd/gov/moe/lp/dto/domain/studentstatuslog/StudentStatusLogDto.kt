package sd.gov.moe.lp.dto.domain.studentstatuslog

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import kotlin.String
import sd.gov.moe.lp.dto.common.DtoName
import sd.gov.moe.lp.dto.common.enums.StudentStatus
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class StudentStatusLogDto(
	val id: StringUUID?,
	val reason: String,
	val studentId: StringUUID,
	val status: DtoName<StudentStatus>,
	val createdOn: DateTimeDto?,
)

