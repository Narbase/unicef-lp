package sd.gov.moe.lp.data.tables


import com.google.gson.JsonElement
import com.narbase.oss.dto.common.forms.EntryListDto
import sd.gov.moe.lp.data.columntypes.dateTimeWithoutTimezone
import sd.gov.moe.lp.data.columntypes.enum
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Function
import org.jetbrains.exposed.sql.QueryBuilder
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.jodatime.DateColumnType
import org.joda.time.DateTime
import sd.gov.moe.lp.data.columntypes.jsonColumn
import sd.gov.moe.lp.data.models.ReleaseDetails
import sd.gov.moe.lp.domain.logUpload.ParseResultData
import sd.gov.moe.lp.dto.common.enums.Country
import sd.gov.moe.lp.dto.common.enums.Gender
import sd.gov.moe.lp.dto.common.enums.StaffActions

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

object StaffTable : UUIDTable("staff"), LoggedTable, DeletableTable {
    val clientId = reference("client_id", ClientsTable).uniqueIndex()
    val fullName = text("full_name")
//    val callingCode = text("calling_code") // with leading +
//    val localPhone = text("local_phone") // without leading zero

    val country = enum("country", Country::class)
    val isInactive = bool("is_inactive").default(false)

    //    val isDummy = bool("is_dummy").default(false)
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object DeviceTokensTable : UUIDTable("device_tokens") {
    val token: Column<String> = text("token").uniqueIndex()
    val clientId = reference("client_id", ClientsTable)
    val createdOn = createdOnColumn()
}

object FilesTable : UUIDTable("uploaded_files_table"), LoggedTable, DeletableTable {
    val fileUrl = text("file_url")
    val fileName = text("file_name")
    override val createdOn = createdOnColumn()
    override val isDeleted = deletedColumn()
}

object FormTemplatesTable : UUIDTable("form_templates"), LoggedTable, DeletableTable {
    val template = jsonColumn<EntryListDto>("template")
    val name = text("name")
    override val createdOn = createdOnColumn()
    override val isDeleted = deletedColumn()
}

object TeachersTable : UUIDTable("teachers"), LoggedTable, DeletableTable {
    val gender = enum("gender", Gender::class)
    val centerId = reference("center_id", CentersTable)
    val staffId = reference("staff_id", StaffTable)
    override val createdOn = createdOnColumn()
    override val isDeleted = deletedColumn()
}

object PartnersTable : UUIDTable("partners"), LoggedTable, DeletableTable {
    val name = text("name")
    val stateId = reference("state_id", StatesTable)
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object StaffPartnersTable : UUIDTable("staff_partners"), LoggedTable {
    val staffId = reference("staff_id", StaffTable)
    val partnerId = reference("partner_id", PartnersTable)
    override val createdOn = createdOnColumn()
}
