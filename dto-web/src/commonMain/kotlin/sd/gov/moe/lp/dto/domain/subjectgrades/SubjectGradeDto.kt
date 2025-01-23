package sd.gov.moe.lp.dto.domain.subjectgrades

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import sd.gov.moe.lp.dto.common.DtoName
import sd.gov.moe.lp.dto.common.enums.SubjectEnrollmentType
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class SubjectGradeDto(
	val id: StringUUID?,
	val gradeId: StringUUID,
	val subjectId: StringUUID,
	val enrollmentType: DtoName<SubjectEnrollmentType>,
	val createdOn: DateTimeDto?,
)

