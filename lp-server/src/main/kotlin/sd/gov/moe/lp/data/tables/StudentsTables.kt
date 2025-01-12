package sd.gov.moe.lp.data.tables

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.jodatime.date
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime
import sd.gov.moe.lp.data.columntypes.dateTimeWithoutTimezone
import sd.gov.moe.lp.data.columntypes.enum
import sd.gov.moe.lp.data.columntypes.jsonColumn
import sd.gov.moe.lp.data.models.monitoring.StudentMonitoringStats
import sd.gov.moe.lp.dto.common.enums.ActivityType
import sd.gov.moe.lp.dto.common.enums.Background
import sd.gov.moe.lp.dto.common.enums.Gender
import sd.gov.moe.lp.dto.common.enums.StudentStatus

object StudentsTable : UUIDTable("students"), LoggedTable, DeletableTable {
    val clientId = reference("client_id", ClientsTable).uniqueIndex()
    val fullName = text("full_name")
    val idNumber = text("id_number")

    //    val studentNumber = integer("number")
    val gender = enum("gender", Gender::class)
    val status = enum("status", StudentStatus::class)
    val dateOfBirth = date("date_of_birth")
    val groupId = reference("group_id", GroupsTable)
    val schoolName = text("school_name")
    val callingCode = text("calling_code") // with leading +
    val localPhone = text("local_phone") // without leading zero

    //    val startDate = date("start_date")
//    val endDate = date("end_date")
    val background = enum("background", Background::class)
    val isActive = bool("is_active").default(true)
    val isPaused = bool("is_paused").default(false)
    val profilePictureUrl = text("profile_picture_url")
    val gradeId = reference("grade_id", GradesTable)
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object StudentsMonitoringTable : UUIDTable("students_monitoring"), LoggedTable {
    val studentId = reference("student_id", StudentsTable)
    val stats = jsonColumn<StudentMonitoringStats>("stats")
    override val createdOn = createdOnColumn()
    val updatedOn = datetime("updated_on").clientDefault { DateTime() }
}

object StudentsActivationLogTable : UUIDTable("students_activation_log"), LoggedTable {
    val isActive = bool("is_active")
    val reason = text("reason")
    val studentId = reference("student_id", StudentsTable)
    val date = date("date")
    override val createdOn = createdOnColumn()
}


object StudentsPauseLogTable : UUIDTable("students_pause_log"), LoggedTable {
    val isPaused = bool("is_paused")
    val reason = text("reason")
    val studentId = reference("student_id", StudentsTable)
    val date = date("date")
    override val createdOn = createdOnColumn()
}

object StudentsImportFilesTable : UUIDTable("students_import_files"), LoggedTable {
    val filePath = text("file_path")
    val groupId = reference("group_id", GroupsTable)
    val uploadedBy = reference("uploaded_by", StaffTable)
    val uploadedOn = datetime("uploaded_on")
    val importedBy = reference("imported_by", StaffTable).nullable()
    val importedOn = datetime("imported_on").nullable()
    override val createdOn = createdOnColumn()
}

object StudentLessonsTable : UUIDTable("student_lessons"), LoggedTable, DeletableTable {
    val studentId = reference("student_id", StudentsTable)
    val lessonId = reference("lesson_id", LessonsTable)
    val progress = double("progress")
    val answers = jsonColumn<String>("answers").nullable() // Answers

    // constraint: student and lesson are unique
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

/*
object StudentAssessmentsTable : UUIDTable("student_assessments"), LoggedTable, DeletableTable {
    val studentId = reference("student_id", StudentsTable)
    val lessonId = reference("lesson_id", LessonsTable)
    val progress = double("progress")
    val answers = jsonColumn<String>("answers")

    // constraint: student and lesson are unique
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}
 */

object StudentSubjectsTable : UUIDTable("student_subjects"), LoggedTable, DeletableTable {
    val studentId = reference("student_id", StudentsTable)
    val subjectId = reference("subject_id", SubjectsTable)
    val progress = double("progress")
    val completedOn = dateTimeWithoutTimezone("completed_on").nullable()
    val certificate = text("certificate").nullable()

    // constraint: student and subject are unique
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object StudentGradesTable : UUIDTable("student_grades"), LoggedTable, DeletableTable {
    val studentId = reference("student_id", StudentsTable)
    val gradeId = reference("grade_id", GradesTable)
//    val progress = double("progress")

    // constraint: student and grade are unique
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object StudentGroupsTable : UUIDTable("student_groups"), LoggedTable, DeletableTable {
    val studentId = reference("student_id", StudentsTable)
    val groupId = reference("group_id", GroupsTable)

    // constraint: student and lesson are unique
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object StudentLearningPathsTable : UUIDTable("student_learning_paths"), LoggedTable, DeletableTable {
    val studentId = reference("student_id", StudentsTable)
    val pathId = reference("path_id", LearningPathsTable)
    val progress = double("progress")

    // constraint: student and lesson are unique
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object StudentActivityLogTable : UUIDTable("student_activity_log"), LoggedTable {
    val studentId = reference("student_id", StudentsTable)
    val activityType = enum("activity_type", ActivityType::class)
    val lessonId = reference("lesson_id", LessonsTable).nullable()
    val score = double("score").nullable()
    val startTime = dateTimeWithoutTimezone("start_time")
    val endTime = dateTimeWithoutTimezone("end_time")
    override val createdOn = createdOnColumn()
}
