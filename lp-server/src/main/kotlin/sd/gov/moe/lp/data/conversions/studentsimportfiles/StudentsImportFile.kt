package sd.gov.moe.lp.data.conversions.studentsimportfiles

import sd.gov.moe.lp.data.access.studentsimportfiles.StudentsImportFile
import sd.gov.moe.lp.dto.domain.studentsimportfiles.StudentsImportFileDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun StudentsImportFile.toDto(): StudentsImportFileDto {
	return StudentsImportFileDto(
		id = id?.toStringUUID(),
		fileId = fileId.toStringUUID(),
		groupId = groupId.toStringUUID(),
		uploadedBy = uploadedBy.toStringUUID(),
		uploadedOn = uploadedOn.toDto(),
		importedBy = importedBy?.toStringUUID(),
		importedOn = importedOn?.toDto(),
		createdOn = createdOn?.toDto(),
	)
}

fun StudentsImportFileDto.toModel(): StudentsImportFile {
	return StudentsImportFile(
		id = id?.toUUID(),
		fileId = fileId.toUUID(),
		groupId = groupId.toUUID(),
		uploadedBy = uploadedBy.toUUID(),
		uploadedOn = uploadedOn.toDateTime(),
		importedBy = importedBy?.toUUID(),
		importedOn = importedOn?.toDateTime(),
		createdOn = createdOn?.toDateTime(),
	)
}
