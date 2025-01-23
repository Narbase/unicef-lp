package sd.gov.moe.lp.data.conversions.studentstatuslog

import sd.gov.moe.lp.data.access.studentstatuslog.StudentStatusLog
import sd.gov.moe.lp.dto.domain.studentstatuslog.StudentStatusLogDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.dto.common.dto
import sd.gov.moe.lp.dto.common.enum
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun StudentStatusLog.toDto(): StudentStatusLogDto {
	return StudentStatusLogDto(
		id = id?.toStringUUID(),
		reason = reason,
		studentId = studentId.toStringUUID(),
		status = status.dto(),
		createdOn = createdOn?.toDto(),
	)
}

fun StudentStatusLogDto.toModel(): StudentStatusLog {
	return StudentStatusLog(
		id = id?.toUUID(),
		reason = reason,
		studentId = studentId.toUUID(),
		status = status.enum(),
		createdOn = createdOn?.toDateTime(),
	)
}
