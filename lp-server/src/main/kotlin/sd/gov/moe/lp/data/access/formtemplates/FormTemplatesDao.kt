package sd.gov.moe.lp.data.access.formtemplates

import sd.gov.moe.lp.data.access.utils.BasicDao
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.FormTemplatesTable
import com.narbase.oss.dto.common.forms.EntryListDto
import kotlin.String
import org.joda.time.DateTime

data class FormTemplate(
	override val id: UUID?,
	val template: EntryListDto,
	val name: String,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object FormTemplatesDao:
	BasicDao<FormTemplatesTable, UUID, FormTemplate>(FormTemplatesTable) {
	override fun toStatement(model: FormTemplate, row: UpdateBuilder<Int>) {
		row[table.template] = model.template
		row[table.name] = model.name
	}

	override fun toModel(row: ResultRow): FormTemplate {
		return FormTemplate(
			id = row[table.id].value,
			template = row[table.template],
			name = row[table.name],
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

