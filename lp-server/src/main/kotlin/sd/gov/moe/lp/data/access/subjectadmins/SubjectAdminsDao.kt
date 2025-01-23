package sd.gov.moe.lp.data.access.subjectadmins

import sd.gov.moe.lp.data.access.utils.BasicDao
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.SubjectAdminsTable
import org.jetbrains.exposed.dao.id.EntityID
import org.joda.time.DateTime

data class SubjectAdmin(
	override val id: UUID?,
	val subjectId: UUID,
	val clientId: UUID,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object SubjectAdminsDao:
	BasicDao<SubjectAdminsTable, UUID, SubjectAdmin>(SubjectAdminsTable) {
	override fun toStatement(model: SubjectAdmin, row: UpdateBuilder<Int>) {
		row[table.subjectId] = model.subjectId
		row[table.clientId] = model.clientId
	}

	override fun toModel(row: ResultRow): SubjectAdmin {
		return SubjectAdmin(
			id = row[table.id].value,
			subjectId = row[table.subjectId].value,
			clientId = row[table.clientId].value,
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

