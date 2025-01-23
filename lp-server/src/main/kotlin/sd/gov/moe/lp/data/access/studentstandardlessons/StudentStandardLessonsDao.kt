package sd.gov.moe.lp.data.access.studentstandardlessons

import sd.gov.moe.lp.data.access.utils.BasicDao
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.StudentStandardLessonsTable
import org.jetbrains.exposed.dao.id.EntityID
import kotlin.Boolean
import org.joda.time.DateTime

data class StudentStandardLesson(
	override val id: UUID?,
	val studentId: UUID,
	val lessonId: UUID,
	val isCompleted: Boolean,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object StudentStandardLessonsDao:
	BasicDao<StudentStandardLessonsTable, UUID, StudentStandardLesson>(StudentStandardLessonsTable) {
	override fun toStatement(model: StudentStandardLesson, row: UpdateBuilder<Int>) {
		row[table.studentId] = model.studentId
		row[table.lessonId] = model.lessonId
		row[table.isCompleted] = model.isCompleted
	}

	override fun toModel(row: ResultRow): StudentStandardLesson {
		return StudentStandardLesson(
			id = row[table.id].value,
			studentId = row[table.studentId].value,
			lessonId = row[table.lessonId].value,
			isCompleted = row[table.isCompleted],
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

