package sd.gov.moe.lp.data.access.grades

import sd.gov.moe.lp.data.access.utils.BasicDao
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.GradesTable
import kotlin.String
import org.jetbrains.exposed.dao.id.EntityID
import org.joda.time.DateTime

data class Grade(
	override val id: UUID?,
	val name: String,
	val thumbnailId: UUID?,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object GradesDao:
	BasicDao<GradesTable, UUID, Grade>(GradesTable) {
	override fun toStatement(model: Grade, row: UpdateBuilder<Int>) {
		row[table.name] = model.name
		row[table.thumbnailId] = model.thumbnailId
	}

	override fun toModel(row: ResultRow): Grade {
		return Grade(
			id = row[table.id].value,
			name = row[table.name],
			thumbnailId = row[table.thumbnailId]?.value,
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

