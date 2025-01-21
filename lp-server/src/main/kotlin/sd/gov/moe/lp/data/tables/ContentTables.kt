package sd.gov.moe.lp.data.tables

import com.narbase.oss.dto.common.forms.EntryListDto
import org.jetbrains.exposed.dao.id.UUIDTable
import sd.gov.moe.lp.data.columntypes.enum
import sd.gov.moe.lp.data.columntypes.jsonColumn
import sd.gov.moe.lp.data.tables.GradesTable.nullable
import sd.gov.moe.lp.dto.common.enums.LessonType
import sd.gov.moe.lp.dto.common.enums.SubjectEnrollmentType

object GradesTable : UUIDTable("grades"), LoggedTable, DeletableTable {
    val name = text("name")
    val thumbnailId = reference("thumbnail_id", UploadedFilesTable).nullable()
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

// todo: Check access levels and roles
object GradeAdminsTable : UUIDTable("grade_admins"), LoggedTable, DeletableTable {
    val gradeId = reference("grade_id", GradesTable)
    val staffId = reference("staff_id", StaffTable)
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object SubjectsTable : UUIDTable("subjects"), LoggedTable, DeletableTable {
    val name = text("name")
    val description = text("description").nullable()
    val thumbnailId = reference("thumbnail_id", UploadedFilesTable).nullable()
    val hasCertificate = bool("has_certificate")
    // todo: clarify certificates handling (templates, editable, etc...)
//    val certificate = reference("certificate", UploadedFilesTable).nullable() // svg, html
    val isLessonsOrderRestrictive = bool("is_lessons_order_restrictive")
//    val hasFeedbackForm = bool("has_feedback_form")
    val feedbackFormId = reference("feedback_form_id", FormTemplatesTable).nullable()
    // todo: Needs discussion
//    val enrollmentType = enum("enrollment_type", SubjectEnrollmentType::class)
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object SubjectGradesTable : UUIDTable("subject_grades"), LoggedTable, DeletableTable {
    val gradeId = reference("grade_id", GradesTable)
    val subjectId = reference("subject_id", SubjectsTable)
    val enrollmentType = enum("enrollment_type", SubjectEnrollmentType::class)
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

// todo: Check access levels and roles, is it needed?
object SubjectAdminsTable : UUIDTable("subject_admins"), LoggedTable, DeletableTable {
    val subjectId = reference("subject_id", SubjectsTable)
    val clientId = reference("client_id", StaffTable)
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object LessonsTable : UUIDTable("lessons"), LoggedTable, DeletableTable {
    val subjectId = reference("subject_id", SubjectsTable)
    val title = text("title")
    val type = enum("type", LessonType::class)
    val order = integer("order")
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object StandardLessonsTable : UUIDTable("standard_lessons"), LoggedTable, DeletableTable {
    val lessonId = reference("lesson_id", LessonsTable)
    val fileId = reference("file_id", UploadedFilesTable)
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object NonGradedAssessmentsTable : UUIDTable("non_graded_assessments"), LoggedTable, DeletableTable {
    val lessonId = reference("lesson_id", LessonsTable)
    val form = jsonColumn<EntryListDto>("form") // Entries
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object GradedAssessmentsTable : UUIDTable("graded_assessments"), LoggedTable, DeletableTable {
    val lessonId = reference("lesson_id", LessonsTable)
    val form = jsonColumn<EntryListDto>("form") // Entries
    val areQuestionsShuffled = bool("are_questions_shuffled")
    val maximumAllowedAttempts = integer("maximum_allowed_attempts")
    val areAnswersShown = bool("are_answers_shown")
    val areOptionsShuffled = bool("are_options_shuffled")
    val passingPercentage = double("passing_percentage")
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}
