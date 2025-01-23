package sd.gov.moe.lp.data.access.groups

import com.narbase.oss.data.access.utils.BasicDaoWithoutDelete
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.GroupsTable
import kotlin.String
import org.jetbrains.exposed.dao.id.EntityID
import org.joda.time.LocalDate
import sd.gov.moe.lp.dto.common.enums.Education
import kotlin.Boolean
import org.joda.time.DateTime

data class Group(
	override val id: UUID?,
	val name: String,
	val centerId: UUID,
	val teacherId: UUID,
	val startDate: LocalDate,
	val endDate: LocalDate,
	val education: Education,
	val partnerId: UUID,
	val isDeleteRequested: Boolean,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object GroupsDao:
	BasicDaoWithoutDelete<GroupsTable, UUID, Group>(GroupsTable) {
	override fun toStatement(model: Group, row: UpdateBuilder<Int>) {
		row[table.name] = model.name
		row[table.centerId] = model.centerId
		row[table.teacherId] = model.teacherId
		row[table.startDate] = model.startDate
		row[table.endDate] = model.endDate
		row[table.education] = model.education
		row[table.partnerId] = model.partnerId
		row[table.isDeleteRequested] = model.isDeleteRequested
	}

	override fun toModel(row: ResultRow): Group {
		return Group(
			id = row[table.id].value,
			name = row[table.name],
			centerId = row[table.centerId].value,
			teacherId = row[table.teacherId].value,
			startDate = row[table.startDate],
			endDate = row[table.endDate],
			education = row[table.education],
			partnerId = row[table.partnerId].value,
			isDeleteRequested = row[table.isDeleteRequested],
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

