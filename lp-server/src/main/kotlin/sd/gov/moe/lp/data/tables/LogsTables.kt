package sd.gov.moe.lp.data.tables


import com.google.gson.JsonElement
import org.jetbrains.exposed.dao.id.UUIDTable
import sd.gov.moe.lp.data.columntypes.dateTimeWithoutTimezone
import sd.gov.moe.lp.data.columntypes.enum
import sd.gov.moe.lp.data.columntypes.jsonColumn
import sd.gov.moe.lp.data.models.ReleaseDetails
import sd.gov.moe.lp.data.models.StudentActivityData
import sd.gov.moe.lp.data.models.monitoring.GroupMonitoringStats
import sd.gov.moe.lp.data.models.monitoring.StudentMonitoringStats
import sd.gov.moe.lp.domain.logUpload.ParseResultData
import sd.gov.moe.lp.dto.common.enums.ActivityType
import sd.gov.moe.lp.dto.common.enums.StaffActions


object UploadResultsTable : UUIDTable("upload_results"), LoggedTable {
    val results = jsonColumn<ParseResultData>("results")
    override val createdOn = createdOnColumn()
}

object StudentActivityLogTable : UUIDTable("student_activity_log"), LoggedTable {
    val studentId = reference("student_id", StudentsTable)
    val gradeId = reference("grade_id", GradesTable)
    val activityType = enum("activity_type", ActivityType::class)
    val data = jsonColumn<StudentActivityData>("data").nullable()
    val startTime = dateTimeWithoutTimezone("start_time")
    val endTime = dateTimeWithoutTimezone("end_time")
    override val createdOn = createdOnColumn()
}

/*
object GameUploadLogTable : UUIDTable("game_upload_log"), LoggedTable {
    val uploadLogId = reference("upload_log_id", UploadLogTable)

    val startTime = dateTimeWithoutTimezone("start_time")
    val endTime = dateTimeWithoutTimezone("end_time")
    val playedTimeInMilliSeconds = decimal("played_time_in_milli_seconds", 20, 4) //todo confirm this
    val playedDate = datetime("played_date")

    val additionalInfo = jsonColumn<UploadLogFileController.Companion.AdditionalInfo>("additional_info").nullable()
    override val createdOn = createdOnColumn()
    val hash = text("hash").nullable()
}
*/

object UploadLogTable : UUIDTable("upload_log"), LoggedTable {
    val studentId = reference("student_id", StudentsTable)
    val gradeId = reference("grade_id", GradesTable)
    val subjectId = reference("subject_id", SubjectsTable)
    val releaseId = reference("released_id", Releases)
    val appVersion = text("app_version")
    val deviceModel = text("device_model")
    val deviceOsVersion = text("device_os_version")
    val deviceSerialId = text("device_serial_id")
    val fileId = reference("file_id", FilesTable)
    val startTime = dateTimeWithoutTimezone("start_time")
    val endTime = dateTimeWithoutTimezone("end_time")
    override val createdOn = createdOnColumn()
}

object StaffActionsLogTable : UUIDTable("staff_actions_log"), LoggedTable {
    val action = enum("action", StaffActions::class)
    val staffId = reference("staff_id", StaffTable)
    val itemId = uuid("item_id")
    val event = text("event")
    val data = jsonColumn<JsonElement>("data").nullable()
    override val createdOn = createdOnColumn()
}

object GroupsMonitoringTable : UUIDTable("groups_monitoring"), LoggedTable {
    val groupId = reference("group_id", GroupsTable)
    val stats = jsonColumn<GroupMonitoringStats>("stats")
    override val createdOn = createdOnColumn()
    val updatedOn = updatedOnColumn()
}

object StudentsMonitoringTable : UUIDTable("students_monitoring"), LoggedTable {
    val studentId = reference("student_id", StudentsTable)
    val stats = jsonColumn<StudentMonitoringStats>("stats")
    override val createdOn = createdOnColumn()

    // todo: confirm change from clientDefault { DateTime() } to dateTimeWithoutTimezone("updated_on").defaultExpression(CurrentDateTimeAtUtc())
    val updatedOn = updatedOnColumn()
}


// A dump that new users can use to start without a full history of the releases
object Releases : UUIDTable("releases"), LoggedTable {
    val releaseDetails = jsonColumn<ReleaseDetails>("release_details").nullable()
    val releasedOn = dateTimeWithoutTimezone("released_on").nullable()
    override val createdOn = createdOnColumn()
}
/*
object ChangeLogTable : UUIDTable("change_log"), LoggedTable {
    val modifiedTableName = text("modified_table_name")
    val recordId = uuid("record_id")
    val operation = text("operation") // enum : insert, update, delete
    val releaseId = reference("released_id", Releases)
    override val createdOn = createdOnColumn()
}

 */