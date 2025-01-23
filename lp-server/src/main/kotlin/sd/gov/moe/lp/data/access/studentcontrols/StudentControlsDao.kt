package sd.gov.moe.lp.data.access.studentcontrols

import sd.gov.moe.lp.data.access.utils.BasicDao
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.StudentControlsTable
import org.jetbrains.exposed.dao.id.EntityID
import sd.gov.moe.lp.dto.common.enums.StudentStatus
import org.joda.time.LocalDate
import sd.gov.moe.lp.dto.common.enums.Background
import kotlin.Boolean
import org.joda.time.DateTime

data class StudentControls(
	override val id: UUID?,
	val studentId: UUID,
	val status: StudentStatus,
	val groupId: UUID,
	val startDate: LocalDate,
	val endDate: LocalDate,
	val background: Background,
	val isActive: Boolean,
	val isPaused: Boolean,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object StudentControlsDao:
	BasicDao<StudentControlsTable, UUID, StudentControls>(StudentControlsTable) {
	override fun toStatement(model: StudentControls, row: UpdateBuilder<Int>) {
		row[table.studentId] = model.studentId
		row[table.status] = model.status
		row[table.groupId] = model.groupId
		row[table.startDate] = model.startDate
		row[table.endDate] = model.endDate
		row[table.background] = model.background
		row[table.isActive] = model.isActive
		row[table.isPaused] = model.isPaused
	}

	override fun toModel(row: ResultRow): StudentControls {
		return StudentControls(
			id = row[table.id].value,
			studentId = row[table.studentId].value,
			status = row[table.status],
			groupId = row[table.groupId].value,
			startDate = row[table.startDate],
			endDate = row[table.endDate],
			background = row[table.background],
			isActive = row[table.isActive],
			isPaused = row[table.isPaused],
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

