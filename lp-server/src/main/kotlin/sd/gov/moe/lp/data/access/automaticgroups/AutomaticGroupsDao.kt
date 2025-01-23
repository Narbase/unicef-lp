package sd.gov.moe.lp.data.access.automaticgroups

import sd.gov.moe.lp.data.access.utils.BasicDao
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.AutomaticGroupsTable
import org.jetbrains.exposed.dao.id.EntityID
import org.joda.time.DateTime

data class AutomaticGroup(
	override val id: UUID?,
	val groupId: UUID,
	val gradeId: UUID,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object AutomaticGroupsDao:
	BasicDao<AutomaticGroupsTable, UUID, AutomaticGroup>(AutomaticGroupsTable) {
	override fun toStatement(model: AutomaticGroup, row: UpdateBuilder<Int>) {
		row[table.groupId] = model.groupId
		row[table.gradeId] = model.gradeId
	}

	override fun toModel(row: ResultRow): AutomaticGroup {
		return AutomaticGroup(
			id = row[table.id].value,
			groupId = row[table.groupId].value,
			gradeId = row[table.gradeId].value,
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

