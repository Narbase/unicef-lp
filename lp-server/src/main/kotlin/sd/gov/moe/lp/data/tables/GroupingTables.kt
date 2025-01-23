package sd.gov.moe.lp.data.tables

import org.jetbrains.exposed.dao.id.UUIDTable
import sd.gov.moe.lp.data.columntypes.dateWithoutTimezone
import sd.gov.moe.lp.data.columntypes.enum
import sd.gov.moe.lp.data.columntypes.jsonColumn
import sd.gov.moe.lp.data.models.monitoring.GroupMonitoringStats
import sd.gov.moe.lp.dto.common.enums.Country
import sd.gov.moe.lp.dto.common.enums.Education


object StatesTable : UUIDTable("states"), LoggedTable {
    val name = text("name")
    val country = enum("country", Country::class)
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

object GroupsTable : UUIDTable("teachers"), LoggedTable, DeletableTableWithRequest {
    val name = text("name")
    val centerId = reference("center_id", CentersTable)
    val teacherId = reference("teacher_id", TeachersTable)

    val startDate = dateWithoutTimezone("start_date")
    val endDate = dateWithoutTimezone("end_date")
    val education = enum("education", Education::class)
    val partnerId = reference("partner_id", PartnersTable)
    override val isDeleted = deletedColumn()
    override val isDeleteRequested = deleteRequestedColumn()
    override val createdOn = createdOnColumn()
}

object GroupsMonitoringTable : UUIDTable("groups_monitoring"), LoggedTable {
    val groupId = reference("group_id", GroupsTable)
    val stats = jsonColumn<GroupMonitoringStats>("stats")
    override val createdOn = createdOnColumn()
    val updatedOn = updatedOnColumn()
}

// From online LP
object AutomaticGroupsTable : UUIDTable("automatic_groups"), LoggedTable, DeletableTable {
    val groupId = reference("group_id", GroupsTable)
    val gradeId = reference("grade_id", GradesTable)
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}

object GroupAdminsTable : UUIDTable("group_admins"), LoggedTable, DeletableTable {
    val groupId = reference("group_id", GroupsTable)
    val staffId = reference("staff_id", StaffTable)
    override val isDeleted = deletedColumn()
    override val createdOn = createdOnColumn()
}
