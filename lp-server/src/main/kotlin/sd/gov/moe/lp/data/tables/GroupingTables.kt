package sd.gov.moe.lp.data.tables

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.jodatime.date
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime
import sd.gov.moe.lp.data.columntypes.enum
import sd.gov.moe.lp.data.columntypes.jsonColumn
import sd.gov.moe.lp.data.models.monitoring.GroupMonitoringStats
import sd.gov.moe.lp.dto.common.enums.Education
import sd.gov.moe.lp.dto.common.enums.Gender

object CountriesTable : UUIDTable("countries"), LoggedTable {
    val name = text("name")
    override val createdOn = createdOnColumn()
}

object StatesTable : UUIDTable("states"), LoggedTable {
    val name = text("name")
    val countryId = reference("country_id", CountriesTable)
    override val createdOn = createdOnColumn()
}

object LocalitiesTable : UUIDTable("localities"), LoggedTable, DeletableTable {
    val name = text("name")
    val stateId = reference("state_id", StatesTable)
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object AdministrativeUnitsTable : UUIDTable("administrative_units"), LoggedTable, DeletableTable {
    val name = text("name")
    val localityId = reference("locality_id", LocalitiesTable)
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object CentersTable : UUIDTable("centers"), LoggedTable, DeletableTable {
    val name = text("name")
    val lat = decimal("lat", 8, 6)
    val lng = decimal("lng", 9, 6)
    val administrativeUnitId = reference("administrative_unit_id", AdministrativeUnitsTable)
    override val createdOn = createdOnColumn()
    override val isDeleted = deletedColumn()
}

object GroupsTable : UUIDTable("teachers"), LoggedTable, DeletableTableWithRequest{
    val name = text("name")
    val locationId = reference("location_id", CentersTable)
    val teacherId = reference("teacher_id", TeachersTable)
    val startDate = date("start_date")
    val endDate = date("end_date")
    val education = enum("education", Education::class)
    val partnerId = reference("partner_id", PartnersTable)
    override val isDeleted = deletedColumn()
    override val isDeleteRequested = bool("is_delete_requested").default(false)
    override val createdOn = createdOnColumn()
}

object GroupsMonitoringTable : UUIDTable("groups_monitoring"),LoggedTable {
    val groupId = reference("group_id", GroupsTable)
    val stats = jsonColumn<GroupMonitoringStats>("stats")
    override val createdOn = createdOnColumn()
    val updatedOn = datetime("updated_on").clientDefault { DateTime() }
}

object AutomaticGroupsTable : UUIDTable("automatic_groups"), LoggedTable, DeletableTable {
    val groupId = reference("group_id", GroupsTable)
    val gradeId = reference("grade_id", GradesTable)
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object GroupAdminsTable : UUIDTable("group_admins"), LoggedTable, DeletableTable {
    val groupId = reference("group_id", GroupsTable)
    val clientId = reference("client_id", ClientsTable)
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

object AutomaticLearningPathsTable : UUIDTable("automatic_learning_paths"), LoggedTable, DeletableTable {
    val pathId = reference("path_id", LearningPathsTable)
    val gradeId = reference("grade_id", GradesTable)
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object LearningPathAdminsTable : UUIDTable("learning_path_admins"), LoggedTable, DeletableTable {
    val pathId = reference("path_id", LearningPathsTable)
    val clientId = reference("client_id", ClientsTable)
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
