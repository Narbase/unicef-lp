package sd.gov.moe.lp.data.conversions.studentsubjects

import sd.gov.moe.lp.data.access.studentsubjects.StudentSubject
import sd.gov.moe.lp.dto.domain.studentsubjects.StudentSubjectDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun StudentSubject.toDto(): StudentSubjectDto {
	return StudentSubjectDto(
		id = id?.toStringUUID(),
		studentId = studentId.toStringUUID(),
		subjectId = subjectId.toStringUUID(),
		progress = progress,
		completedOn = completedOn?.toDto(),
		hasCertificate = hasCertificate,
		feedback = feedback,
		createdOn = createdOn?.toDto(),
	)
}

fun StudentSubjectDto.toModel(): StudentSubject {
	return StudentSubject(
		id = id?.toUUID(),
		studentId = studentId.toUUID(),
		subjectId = subjectId.toUUID(),
		progress = progress,
		completedOn = completedOn?.toDateTime(),
		hasCertificate = hasCertificate,
		feedback = feedback,
		createdOn = createdOn?.toDateTime(),
	)
}
