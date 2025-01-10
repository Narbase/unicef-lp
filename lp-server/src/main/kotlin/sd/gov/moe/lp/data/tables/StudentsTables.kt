package sd.gov.moe.lp.data.tables

import org.jetbrains.exposed.dao.id.UUIDTable
import sd.gov.moe.lp.data.columntypes.dateTimeWithoutTimezone
import sd.gov.moe.lp.data.columntypes.enum
import sd.gov.moe.lp.data.columntypes.jsonColumn
import sd.gov.moe.lp.dto.common.enums.ActivityType

object StudentsTable : UUIDTable("students"), LoggedTable, DeletableTable {
    val clientId = reference("client_id", ClientsTable).uniqueIndex()
    val fullName = text("full_name")
    val profilePictureUrl = text("profile_picture_url")
    val gradeId = reference("grade_id", GradesTable)
    val isLessonsOrderRestrictive = bool("is_lessons_order_restrictive")
    override val isDeleted = deletedColumn()
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

    // constraint: student and lesson are unique
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object StudentGradesTable : UUIDTable("student_grades"), LoggedTable, DeletableTable {
    val studentId = reference("student_id", StudentsTable)
    val gradeId = reference("grade_id", GradesTable)
//    val progress = double("progress")

    // constraint: student and lesson are unique
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
