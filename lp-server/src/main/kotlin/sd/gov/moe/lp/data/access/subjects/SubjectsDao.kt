package sd.gov.moe.lp.data.access.subjects

import sd.gov.moe.lp.data.access.utils.BasicDao
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.SubjectsTable
import kotlin.String
import org.jetbrains.exposed.dao.id.EntityID
import kotlin.Boolean
import org.joda.time.DateTime

data class Subject(
	override val id: UUID?,
	val name: String,
	val description: String?,
	val thumbnailId: UUID?,
	val hasCertificate: Boolean,
	val isLessonsOrderRestrictive: Boolean,
	val feedbackFormId: UUID?,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object SubjectsDao:
	BasicDao<SubjectsTable, UUID, Subject>(SubjectsTable) {
	override fun toStatement(model: Subject, row: UpdateBuilder<Int>) {
		row[table.name] = model.name
		row[table.description] = model.description
		row[table.thumbnailId] = model.thumbnailId
		row[table.hasCertificate] = model.hasCertificate
		row[table.isLessonsOrderRestrictive] = model.isLessonsOrderRestrictive
		row[table.feedbackFormId] = model.feedbackFormId
	}

	override fun toModel(row: ResultRow): Subject {
		return Subject(
			id = row[table.id].value,
			name = row[table.name],
			description = row[table.description],
			thumbnailId = row[table.thumbnailId]?.value,
			hasCertificate = row[table.hasCertificate],
			isLessonsOrderRestrictive = row[table.isLessonsOrderRestrictive],
			feedbackFormId = row[table.feedbackFormId]?.value,
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

