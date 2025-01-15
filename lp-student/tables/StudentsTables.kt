package sd.gov.moe.lp.data.tables

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.jodatime.date
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime
import sd.gov.moe.lp.data.columntypes.dateTimeWithoutTimezone
import sd.gov.moe.lp.data.columntypes.enum
import sd.gov.moe.lp.data.columntypes.jsonColumn
import sd.gov.moe.lp.data.models.monitoring.StudentMonitoringStats
import sd.gov.moe.lp.data.tables.StudentLessonsTable.nullable
import sd.gov.moe.lp.dto.common.enums.ActivityType
import sd.gov.moe.lp.dto.common.enums.Background
import sd.gov.moe.lp.dto.common.enums.Gender
import sd.gov.moe.lp.dto.common.enums.StudentStatus

object StudentsTable : UUIDTable("students"), LoggedTable, DeletableTableWithRequest {
    // todo: this is created from the tablet
    val clientId = reference("client_id", ClientsTable).uniqueIndex()
    val fullName = text("full_name")
    val idNumber = text("id_number").nullable()
    val gender = enum("gender", Gender::class)
    val dateOfBirth = date("date_of_birth")
    val countryId = reference("country_id", CountriesTable)
    val schoolName = text("school_name").nullable()
    val callingCode = text("calling_code").nullable() // with leading +
    val localPhone = text("local_phone").nullable() // without leading zero
    val profilePictureId = reference("profile_picture_id", UploadedFilesTable).nullable()
    val gradeId = reference("grade_id", GradesTable)

    override val isDeleted = deletedColumn()
    override val isDeleteRequested = deleteRequestedColumn()
    override val createdOn = createdOnColumn()
}

object StudentLessonsTable : UUIDTable("student_lessons"), LoggedTable, DeletableTable {
    val studentId = reference("student_id", StudentsTable)
    val lessonId = reference("lesson_id", LessonsTable)
    val isCompleted = bool("is_completed")

    // constraint: student and lesson are unique
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object StudentAssessmentsTable : UUIDTable("student_assessments"), LoggedTable, DeletableTable {
    val studentId = reference("student_id", StudentsTable)
    val lessonId = reference("lesson_id", LessonsTable)
    val progress = double("progress")
    val answers = jsonColumn<String>("answers")
    val attempts = integer("attempts").nullable()

    // constraint: student and lesson are unique
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object StudentSubjectsTable : UUIDTable("student_subjects"), LoggedTable, DeletableTable {
    val studentId = reference("student_id", StudentsTable)
    val subjectId = reference("subject_id", SubjectsTable)
//    val progress = double("progress")
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

object StudentActivityLogTable : UUIDTable("student_activity_log"), LoggedTable {
    val studentId = reference("student_id", StudentsTable)
    val gradeId = reference("grade_id", GradesTable)
    val activityType = enum("activity_type", ActivityType::class)
    val lessonId = reference("lesson_id", LessonsTable).nullable()
    val score = double("score").nullable()
    val answers = jsonColumn<String>("answers").nullable() // Answers
    val attempts = integer("attempts").nullable()
    val startTime = dateTimeWithoutTimezone("start_time")
    val endTime = dateTimeWithoutTimezone("end_time")
    override val createdOn = createdOnColumn()
}
