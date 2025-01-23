package sd.gov.moe.lp.data.access.standardlessons

import sd.gov.moe.lp.data.access.utils.BasicDao
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.StandardLessonsTable
import org.jetbrains.exposed.dao.id.EntityID
import org.joda.time.DateTime

data class StandardLesson(
	override val id: UUID?,
	val lessonId: UUID,
	val fileId: UUID,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object StandardLessonsDao:
	BasicDao<StandardLessonsTable, UUID, StandardLesson>(StandardLessonsTable) {
	override fun toStatement(model: StandardLesson, row: UpdateBuilder<Int>) {
		row[table.lessonId] = model.lessonId
		row[table.fileId] = model.fileId
	}

	override fun toModel(row: ResultRow): StandardLesson {
		return StandardLesson(
			id = row[table.id].value,
			lessonId = row[table.lessonId].value,
			fileId = row[table.fileId].value,
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

