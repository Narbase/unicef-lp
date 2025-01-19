package sd.gov.moe.lp.data.tables


import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.dao.id.IntIdTable
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

// todo: Uploaded files tables
object StaffTable : UUIDTable("staff"), LoggedTable, DeletableTable {
    val clientId = reference("client_id", ClientsTable).uniqueIndex()
    val fullName = text("full_name")
    val callingCode = text("calling_code") // with leading +
    val localPhone = text("local_phone") // without leading zero
    val isInactive = bool("is_inactive").default(false)
    override val isDeleted = deletedColumn()
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

//fixme: Is the client different from the user in ulp?
object StaffPartnersTable : UUIDTable("staff_partners"), LoggedTable {
    val staffId = reference("staff_id", StaffTable)
    val partnerId = reference("partner_id", PartnersTable)
    override val createdOn = createdOnColumn()
}

object UploadResultsTable : UUIDTable("upload_results"), LoggedTable {
    val results = jsonColumn<ParseResultData>("results")
    override val createdOn = createdOnColumn()
}


object GameUploadLogTable : UUIDTable("game_upload_log"), LoggedTable {
    val uploadLogId = reference("upload_log_id", UploadLogTable)

    //    val startLevel = integer("start_level")
//    val gameLevel = integer("game_level")
//    val minigame = integer("minigame").nullable()
//    val totalPoints = integer("total_points")
//    val studentPoints = double("student_points")
//    val cutoff = double("cutoff").nullable()
    val startTime = datetime("start_time")
    val endTime = datetime("end_time")
    val playedTimeInMilliSeconds = decimal("played_time_in_milli_seconds", 20, 4) //todo confirm this
    val playedDate = datetime("played_date")

    //    val additionalInfo = jsonColumn<UploadLogFileController.Companion.AdditionalInfo>("additional_info").nullable()
    override val createdOn = createdOnColumn()
    val hash = text("hash").nullable()
}

object UploadLogTable : UUIDTable("upload_log"), LoggedTable {
    val studentId = reference("student_id", StudentsTable)
    val gradeId = reference("grade_id", GradesTable)
    val gameVersion = text("game_version")
    val deviceModel = text("device_model")
    val deviceOsVersion = text("device_os_version")
    val deviceSerialId = text("device_serial_id")
    val fileId = reference("file_id", UploadedFilesTable)
    override val createdOn = createdOnColumn()
}

//fixme: Is the log in the students tables enough?
//object UserActionsLogTable : UUIDTable("user_actions_log"), LoggedTable {
//    val action = enum("action", UserAction::class)
//    val userId = reference("user_id", ClientsTable)
//    val affectedUserId = reference("affected_user_id", ClientsTable).nullable()
//    val oldLogItem = logItem("old_log_item")
//    val newLogItem = logItem("new_log_item")
//    override val createdOn = createdOnColumn()
//}

object DashboardMigrationsTable : IntIdTable("dashboard_migration"), LoggedTable {
    val migration = text("migration")
    val version = text("version").uniqueIndex()
    override val createdOn = createdOnColumn()
}