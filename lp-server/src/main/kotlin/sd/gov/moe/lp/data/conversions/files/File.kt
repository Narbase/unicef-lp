package sd.gov.moe.lp.data.conversions.files

import sd.gov.moe.lp.data.access.files.File
import sd.gov.moe.lp.dto.domain.files.FileDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun File.toDto(): FileDto {
	return FileDto(
		id = id?.toStringUUID(),
		fileUrl = fileUrl,
		fileName = fileName,
		createdOn = createdOn?.toDto(),
	)
}

fun FileDto.toModel(): File {
	return File(
		id = id?.toUUID(),
		fileUrl = fileUrl,
		fileName = fileName,
		createdOn = createdOn?.toDateTime(),
	)
}
