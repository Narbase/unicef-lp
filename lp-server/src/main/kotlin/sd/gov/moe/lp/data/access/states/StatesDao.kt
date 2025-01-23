package sd.gov.moe.lp.data.access.states

import com.narbase.oss.data.access.utils.BasicDaoWithoutDelete
import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.UUID
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Query
import sd.gov.moe.lp.data.tables.StatesTable
import kotlin.String
import sd.gov.moe.lp.dto.common.enums.Country
import org.joda.time.DateTime

data class State(
	override val id: UUID?,
	val name: String,
	val country: Country,
	val createdOn: DateTime?,
) : ModelWithId<UUID>

object StatesDao:
	BasicDaoWithoutDelete<StatesTable, UUID, State>(StatesTable) {
	override fun toStatement(model: State, row: UpdateBuilder<Int>) {
		row[table.name] = model.name
		row[table.country] = model.country
	}

	override fun toModel(row: ResultRow): State {
		return State(
			id = row[table.id].value,
			name = row[table.name],
			country = row[table.country],
			createdOn = row[table.createdOn],
		)
	}

	override fun filterWithSearchTerm(query: Query, searchTerm: String) {
		TODO("Not yet implemented")
	}
}

