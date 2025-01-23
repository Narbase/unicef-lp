package sd.gov.moe.lp.data.access.students

import com.narbase.oss.data.access.utils.BasicDaoWithoutDelete
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.StudentsTable
import kotlin.String
import sd.gov.moe.lp.dto.common.enums.Gender
import org.joda.time.LocalDate
import sd.gov.moe.lp.dto.common.enums.Country
import org.jetbrains.exposed.dao.id.EntityID
import kotlin.Boolean
import org.joda.time.DateTime

data class Student(
	override val id: UUID?,
	val username: String,
	val passwordHash: String,
	val fullName: String,
	val idNumber: String?,
	val gender: Gender,
	val dateOfBirth: LocalDate,
	val country: Country,
	val schoolName: String?,
	val callingCode: String?,
	val localPhone: String?,
	val profilePictureId: UUID?,
	val gradeId: UUID,
	val isDeleteRequested: Boolean,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object StudentsDao:
	BasicDaoWithoutDelete<StudentsTable, UUID, Student>(StudentsTable) {
	override fun toStatement(model: Student, row: UpdateBuilder<Int>) {
		row[table.username] = model.username
		row[table.passwordHash] = model.passwordHash
		row[table.fullName] = model.fullName
		row[table.idNumber] = model.idNumber
		row[table.gender] = model.gender
		row[table.dateOfBirth] = model.dateOfBirth
		row[table.country] = model.country
		row[table.schoolName] = model.schoolName
		row[table.callingCode] = model.callingCode
		row[table.localPhone] = model.localPhone
		row[table.profilePictureId] = model.profilePictureId
		row[table.gradeId] = model.gradeId
		row[table.isDeleteRequested] = model.isDeleteRequested
	}

	override fun toModel(row: ResultRow): Student {
		return Student(
			id = row[table.id].value,
			username = row[table.username],
			passwordHash = row[table.passwordHash],
			fullName = row[table.fullName],
			idNumber = row[table.idNumber],
			gender = row[table.gender],
			dateOfBirth = row[table.dateOfBirth],
			country = row[table.country],
			schoolName = row[table.schoolName],
			callingCode = row[table.callingCode],
			localPhone = row[table.localPhone],
			profilePictureId = row[table.profilePictureId]?.value,
			gradeId = row[table.gradeId].value,
			isDeleteRequested = row[table.isDeleteRequested],
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

