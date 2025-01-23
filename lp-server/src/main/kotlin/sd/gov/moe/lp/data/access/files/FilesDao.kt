package sd.gov.moe.lp.data.access.files

import sd.gov.moe.lp.data.access.utils.BasicDao
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.FilesTable
import kotlin.String
import org.joda.time.DateTime

data class File(
	override val id: UUID?,
	val fileUrl: String,
	val fileName: String,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object FilesDao:
	BasicDao<FilesTable, UUID, File>(FilesTable) {
	override fun toStatement(model: File, row: UpdateBuilder<Int>) {
		row[table.fileUrl] = model.fileUrl
		row[table.fileName] = model.fileName
	}

	override fun toModel(row: ResultRow): File {
		return File(
			id = row[table.id].value,
			fileUrl = row[table.fileUrl],
			fileName = row[table.fileName],
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

