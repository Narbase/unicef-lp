package sd.gov.moe.lp.dto.domain.studentsimportfiles

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class StudentsImportFileDto(
	val id: StringUUID?,
	val fileId: StringUUID,
	val groupId: StringUUID,
	val uploadedBy: StringUUID,
	val uploadedOn: DateTimeDto,
	val importedBy: StringUUID?,
	val importedOn: DateTimeDto?,
	val createdOn: DateTimeDto?,
)

