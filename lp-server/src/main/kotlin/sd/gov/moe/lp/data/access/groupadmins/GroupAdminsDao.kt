package sd.gov.moe.lp.data.access.groupadmins

import sd.gov.moe.lp.data.access.utils.BasicDao
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.GroupAdminsTable
import org.jetbrains.exposed.dao.id.EntityID
import org.joda.time.DateTime

data class GroupAdmin(
	override val id: UUID?,
	val groupId: UUID,
	val staffId: UUID,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object GroupAdminsDao:
	BasicDao<GroupAdminsTable, UUID, GroupAdmin>(GroupAdminsTable) {
	override fun toStatement(model: GroupAdmin, row: UpdateBuilder<Int>) {
		row[table.groupId] = model.groupId
		row[table.staffId] = model.staffId
	}

	override fun toModel(row: ResultRow): GroupAdmin {
		return GroupAdmin(
			id = row[table.id].value,
			groupId = row[table.groupId].value,
			staffId = row[table.staffId].value,
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

