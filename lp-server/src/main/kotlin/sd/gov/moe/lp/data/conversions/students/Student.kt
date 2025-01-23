package sd.gov.moe.lp.data.conversions.students

import sd.gov.moe.lp.data.access.students.Student
import sd.gov.moe.lp.dto.domain.students.StudentDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.dto.common.dto
import sd.gov.moe.lp.dto.common.enum
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDate
import sd.gov.moe.lp.domain.utils.toDateTime

fun Student.toDto(): StudentDto {
	return StudentDto(
		id = id?.toStringUUID(),
		username = username,
		passwordHash = passwordHash,
		fullName = fullName,
		idNumber = idNumber,
		gender = gender.dto(),
		dateOfBirth = dateOfBirth.toDto(),
		country = country.dto(),
		schoolName = schoolName,
		callingCode = callingCode,
		localPhone = localPhone,
		profilePictureId = profilePictureId?.toStringUUID(),
		gradeId = gradeId.toStringUUID(),
		isDeleteRequested = isDeleteRequested,
		createdOn = createdOn?.toDto(),
	)
}

fun StudentDto.toModel(): Student {
	return Student(
		id = id?.toUUID(),
		username = username,
		passwordHash = passwordHash,
		fullName = fullName,
		idNumber = idNumber,
		gender = gender.enum(),
		dateOfBirth = dateOfBirth.toDate(),
		country = country.enum(),
		schoolName = schoolName,
		callingCode = callingCode,
		localPhone = localPhone,
		profilePictureId = profilePictureId?.toUUID(),
		gradeId = gradeId.toUUID(),
		isDeleteRequested = isDeleteRequested,
		createdOn = createdOn?.toDateTime(),
	)
}
