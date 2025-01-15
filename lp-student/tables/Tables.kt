package sd.gov.moe.lp.data.tables


import sd.gov.moe.lp.data.columntypes.dateTimeWithoutTimezone
import sd.gov.moe.lp.data.columntypes.enum
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Function
import org.jetbrains.exposed.sql.QueryBuilder
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.DateColumnType
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime
import sd.gov.moe.lp.data.columntypes.jsonColumn
import sd.gov.moe.lp.data.tables.StudentsTable.default
import sd.gov.moe.lp.domain.logUpload.ParseResultData
import sd.gov.moe.lp.dto.common.enums.Gender
import java.awt.image.LookupTable

interface DeletableTable {
    val isDeleted: Column<Boolean>
}

interface DeletableTableWithRequest {
    val isDeleted: Column<Boolean>
    val isDeleteRequested: Column<Boolean>
}

interface LoggedTable {
    val createdOn: Column<DateTime>
}

fun Table.deletedColumn() = bool("is_deleted").default(false)
fun Table.deleteRequestedColumn() = bool("is_delete_requested").default(false)
fun Table.createdOnColumn() = dateTimeWithoutTimezone("created_on").defaultExpression(CurrentDateTimeAtUtc())
fun Table.updatedOnColumn() = dateTimeWithoutTimezone("updated_on").defaultExpression(CurrentDateTimeAtUtc())

class CurrentDateTimeAtUtc : Function<DateTime>(DateColumnType(false)) {
    override fun toQueryBuilder(queryBuilder: QueryBuilder) = queryBuilder {
        +"CURRENT_TIMESTAMP at time zone 'UTC'"
    }
}

object ClientsTable : LoggedTable, UUIDTable("clients") {
    val username: Column<String> = text("username").index()
    val passwordHash: Column<String> = text("password_hash")
    val lastLogin = dateTimeWithoutTimezone("last_login").nullable()
    override val createdOn = createdOnColumn()
}

object CountriesTable : UUIDTable("countries"), LoggedTable {
    val name = text("name")
    override val createdOn = createdOnColumn()
}

object DeviceTokensTable : UUIDTable("device_tokens") {
    val token: Column<String> = text("token").uniqueIndex()
    val clientId = reference("client_id", ClientsTable)
    val createdOn = createdOnColumn()
}

object UploadedFilesTable : UUIDTable("uploaded_files_table"), LoggedTable, DeletableTable {
    val fileUrl = text("file_url")
    val fileName = text("file_name")
    override val createdOn = createdOnColumn()
    override val isDeleted = deletedColumn()
}
