package sd.gov.moe.lp.data.tables

import com.narbase.oss.dto.common.forms.AnswersListDto
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import sd.gov.moe.lp.data.columntypes.dateTimeWithoutTimezone
import sd.gov.moe.lp.data.columntypes.dateWithoutTimezone
import sd.gov.moe.lp.data.columntypes.enum
import sd.gov.moe.lp.data.columntypes.jsonColumn
import sd.gov.moe.lp.data.models.StudentActivityData
import sd.gov.moe.lp.data.models.monitoring.StudentMonitoringStats
import sd.gov.moe.lp.dto.common.enums.ActivityType
import sd.gov.moe.lp.dto.common.enums.Background
import sd.gov.moe.lp.dto.common.enums.Gender
import sd.gov.moe.lp.dto.common.enums.StudentStatus

object StudentsTable : UUIDTable("students"), LoggedTable, DeletableTableWithRequest {
    val username: Column<String> = text("username")
    val passwordHash: Column<String> = text("password_hash")

    val fullName = text("full_name")
    val idNumber = text("id_number").nullable()
    val gender = enum("gender", Gender::class)
    val dateOfBirth = dateWithoutTimezone("date_of_birth")
    val countryId = reference("country_id", CountriesTable)
    val schoolName = text("school_name").nullable()
    val callingCode = text("calling_code").nullable() // with leading +
    val localPhone = text("local_phone").nullable() // without leading zero
    val profilePictureId = reference("profile_picture_id", FilesTable).nullable()
    val gradeId = reference("grade_id", GradesTable)

    override val isDeleted = deletedColumn()
    override val isDeleteRequested = deleteRequestedColumn()
    override val createdOn = createdOnColumn()
}

object StudentControlsTable : UUIDTable("student_controls"), LoggedTable, DeletableTable {
    val studentId = reference("student_id", StudentsTable)
    val status = enum("status", StudentStatus::class).default(StudentStatus.Active)

    val groupId = reference("group_id", GroupsTable)

    val startDate = dateWithoutTimezone("start_date")
    val endDate = dateWithoutTimezone("end_date")
    val background = enum("background", Background::class)
    val isActive = bool("is_active").default(true)
    val isPaused = bool("is_paused").default(false)

    override val createdOn = createdOnColumn()
    override val isDeleted = deletedColumn()
}

object StudentsMonitoringTable : UUIDTable("students_monitoring"), LoggedTable {
    val studentId = reference("student_id", StudentsTable)
    val stats = jsonColumn<StudentMonitoringStats>("stats")
    override val createdOn = createdOnColumn()

    // todo: confirm change from clientDefault { DateTime() } to dateTimeWithoutTimezone("updated_on").defaultExpression(CurrentDateTimeAtUtc())
    val updatedOn = updatedOnColumn()
}

object StudentStatusLogTable : UUIDTable("student_status_log"), LoggedTable {
    val reason = text("reason")
    val studentId = reference("student_id", StudentsTable)
    val status = enum("status", StudentStatus::class)
    override val createdOn = createdOnColumn()
}

object StudentsImportFilesTable : UUIDTable("students_import_files"), LoggedTable {
    val fileId = reference("file_id", FilesTable)
    val groupId = reference("group_id", GroupsTable)
    val uploadedBy = reference("uploaded_by", StaffTable)
    val uploadedOn = dateTimeWithoutTimezone("uploaded_on")
    val importedBy = reference("imported_by", StaffTable).nullable()
    val importedOn = dateTimeWithoutTimezone("imported_on").nullable()
    override val createdOn = createdOnColumn()
}

object StudentGradesTable : UUIDTable("student_grades"), LoggedTable, DeletableTable {
    val studentId = reference("student_id", StudentsTable)
    val gradeId = reference("grade_id", GradesTable)
    // todo: updated when the student data is imported
    val progress = double("progress")

    // constraint: student and grade are unique
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object StudentSubjectsTable : UUIDTable("student_subjects"), LoggedTable, DeletableTable {
    val studentId = reference("student_id", StudentsTable)
    val subjectId = reference("subject_id", SubjectsTable)

    // todo: updated when the student data is imported
    val progress = double("progress")
    val completedOn = dateTimeWithoutTimezone("completed_on").nullable()

    val hasCertificate = bool("has_certificate").nullable()
    val feedback = jsonColumn<AnswersListDto>("feedback").nullable()

    // constraint: student and subject are unique
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}


object StudentStandardLessonsTable : UUIDTable("student_standard_lessons"), LoggedTable, DeletableTable {
    val studentId = reference("student_id", StudentsTable)
    val lessonId = reference("lesson_id", StandardLessonsTable)
    val isCompleted = bool("is_completed")

    // constraint: student and lesson are unique
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object StudentGradedAssessmentsTable : UUIDTable("student_graded_assessments"), LoggedTable, DeletableTable {
    val studentId = reference("student_id", StudentsTable)
    val lessonId = reference("lesson_id", GradedAssessmentsTable)
    val progress = double("progress")
    val answers = jsonColumn<AnswersListDto>("answers")
    val attempts = integer("attempts")

    // constraint: student and lesson are unique
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object StudentNonGradedAssessmentsTable : UUIDTable("student_non_graded_assessments"), LoggedTable, DeletableTable {
    val studentId = reference("student_id", StudentsTable)
    val lessonId = reference("lesson_id", NonGradedAssessmentsTable)
    val progress = double("progress")
    val answers = jsonColumn<AnswersListDto>("answers")

    // constraint: student and lesson are unique
    override val isDeleted = deletedColumn()
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
