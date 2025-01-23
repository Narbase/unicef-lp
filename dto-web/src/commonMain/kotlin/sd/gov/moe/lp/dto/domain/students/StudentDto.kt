package sd.gov.moe.lp.dto.domain.students

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import kotlin.String
import sd.gov.moe.lp.dto.common.DtoName
import sd.gov.moe.lp.dto.common.enums.Gender
import sd.gov.moe.lp.dto.common.datetime.DateDto
import sd.gov.moe.lp.dto.common.enums.Country
import kotlin.Boolean
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class StudentDto(
	val id: StringUUID?,
	val username: String,
	val passwordHash: String,
	val fullName: String,
	val idNumber: String?,
	val gender: DtoName<Gender>,
	val dateOfBirth: DateDto,
	val country: DtoName<Country>,
	val schoolName: String?,
	val callingCode: String?,
	val localPhone: String?,
	val profilePictureId: StringUUID?,
	val gradeId: StringUUID,
	val isDeleteRequested: Boolean,
	val createdOn: DateTimeDto?,
)

