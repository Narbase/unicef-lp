package sd.gov.moe.lp.data.access.lessons

import sd.gov.moe.lp.data.access.utils.BasicDao
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.LessonsTable
import org.jetbrains.exposed.dao.id.EntityID
import kotlin.String
import sd.gov.moe.lp.dto.common.enums.LessonType
import kotlin.Int
import org.joda.time.DateTime

data class Lesson(
	override val id: UUID?,
	val subjectId: UUID,
	val title: String,
	val type: LessonType,
	val order: Int,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object LessonsDao:
	BasicDao<LessonsTable, UUID, Lesson>(LessonsTable) {
	override fun toStatement(model: Lesson, row: UpdateBuilder<Int>) {
		row[table.subjectId] = model.subjectId
		row[table.title] = model.title
		row[table.type] = model.type
		row[table.order] = model.order
	}

	override fun toModel(row: ResultRow): Lesson {
		return Lesson(
			id = row[table.id].value,
			subjectId = row[table.subjectId].value,
			title = row[table.title],
			type = row[table.type],
			order = row[table.order],
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

