package sd.gov.moe.lp.data.access.studentstatuslog

import com.narbase.oss.data.access.utils.BasicDaoWithoutDelete
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.StudentStatusLogTable
import kotlin.String
import org.jetbrains.exposed.dao.id.EntityID
import sd.gov.moe.lp.dto.common.enums.StudentStatus
import org.joda.time.DateTime

data class StudentStatusLog(
	override val id: UUID?,
	val reason: String,
	val studentId: UUID,
	val status: StudentStatus,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object StudentStatusLogDao:
	BasicDaoWithoutDelete<StudentStatusLogTable, UUID, StudentStatusLog>(StudentStatusLogTable) {
	override fun toStatement(model: StudentStatusLog, row: UpdateBuilder<Int>) {
		row[table.reason] = model.reason
		row[table.studentId] = model.studentId
		row[table.status] = model.status
	}

	override fun toModel(row: ResultRow): StudentStatusLog {
		return StudentStatusLog(
			id = row[table.id].value,
			reason = row[table.reason],
			studentId = row[table.studentId].value,
			status = row[table.status],
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

