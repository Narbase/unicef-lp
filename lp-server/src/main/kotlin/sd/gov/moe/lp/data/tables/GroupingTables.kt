package sd.gov.moe.lp.data.tables

import org.jetbrains.exposed.dao.id.UUIDTable

object GroupsTable : UUIDTable("groups"), LoggedTable, DeletableTable {
    val name = text("name")
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object GroupAdminsTable : UUIDTable("group_admins"), LoggedTable, DeletableTable {
    val groupId = reference("group_id", GroupsTable)
    val clientId = reference("client_id",ClientsTable)
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object LearningPathsTable : UUIDTable("learning_paths"), LoggedTable, DeletableTable {
    val name = text("name")
    val description = text("description").nullable()
    val thumbnailUrl = text("thumbnail_url").nullable()
    val hasCertificate = bool("has_certificate")
    val isOrderingRestricted = bool("is_ordering_restricted")
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object LearningPathAdminsTable : UUIDTable("learning_path_admins"), LoggedTable, DeletableTable {
    val pathId = reference("path_id", LearningPathsTable)
    val clientId = reference("client_id",ClientsTable)
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object LearningPathSubjectsTable : UUIDTable("learning_path_subjects"), LoggedTable, DeletableTable {
    val pathId = reference("path_id", LearningPathsTable)
    val subjectId = reference("subject_id", SubjectsTable)
    val order = integer("order")
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}
