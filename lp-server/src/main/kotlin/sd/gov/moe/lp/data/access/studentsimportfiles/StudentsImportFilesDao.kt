package sd.gov.moe.lp.data.access.studentsimportfiles

import com.narbase.oss.data.access.utils.BasicDaoWithoutDelete
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.StudentsImportFilesTable
import org.jetbrains.exposed.dao.id.EntityID
import org.joda.time.DateTime

data class StudentsImportFile(
	override val id: UUID?,
	val fileId: UUID,
	val groupId: UUID,
	val uploadedBy: UUID,
	val uploadedOn: DateTime,
	val importedBy: UUID?,
	val importedOn: DateTime?,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object StudentsImportFilesDao:
	BasicDaoWithoutDelete<StudentsImportFilesTable, UUID, StudentsImportFile>(StudentsImportFilesTable) {
	override fun toStatement(model: StudentsImportFile, row: UpdateBuilder<Int>) {
		row[table.fileId] = model.fileId
		row[table.groupId] = model.groupId
		row[table.uploadedBy] = model.uploadedBy
		row[table.uploadedOn] = model.uploadedOn
		row[table.importedBy] = model.importedBy
		row[table.importedOn] = model.importedOn
	}

	override fun toModel(row: ResultRow): StudentsImportFile {
		return StudentsImportFile(
			id = row[table.id].value,
			fileId = row[table.fileId].value,
			groupId = row[table.groupId].value,
			uploadedBy = row[table.uploadedBy].value,
			uploadedOn = row[table.uploadedOn],
			importedBy = row[table.importedBy]?.value,
			importedOn = row[table.importedOn],
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

