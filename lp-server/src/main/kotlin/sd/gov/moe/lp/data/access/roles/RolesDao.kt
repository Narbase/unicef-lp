package sd.gov.moe.lp.data.access.roles

import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.statements.UpdateBuilder
import org.jetbrains.exposed.sql.update
import org.joda.time.DateTime
import sd.gov.moe.lp.data.access.utils.BasicDao
import sd.gov.moe.lp.data.access.utils.ModelWithId
import sd.gov.moe.lp.data.columntypes.RolePrivilegesColumn
import sd.gov.moe.lp.data.tables.roles.RolesTable
import sd.gov.moe.lp.dto.models.roles.Privilege
import java.util.*

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
data class Role(
    override val id: UUID?,
    val createdOn: DateTime?,
    val name: String,
    val role: RolePrivilegesColumn,
) : ModelWithId<UUID>

object RolesDao : BasicDao<RolesTable, UUID, Role>(RolesTable) {

    /*
//        todo: those should be in the roles table, inserted from the Dao (getOrCreate) or migration?
ROLE_TEACHER("ROLE_TEACHER", "ROLE_TEACHER"),
ROLE_STUDENT_ADMIN_OFFICER("ROLE_STUDENT_ADMIN_OFFICER", "ROLE_STUDENT_ADMIN_OFFICER"),
ROLE_COUNTRY_SUPERVISOR("ROLE_COUNTRY_SUPERVISOR", "ROLE_COUNTRY_SUPERVISOR"),
ROLE_IT_SUPPORT("ROLE_IT_SUPPORT", "ROLE_IT_SUPPORT"),
ROLE_MONITORING_AND_EVALUATION_OFFICER("ROLE_MONITORING_AND_EVALUATION_OFFICER", "ROLE_MON_AND_EVAL_OFFICER"),
ROLE_STAKEHOLDER("ROLE_STAKEHOLDER", "ROLE_STAKEHOLDER"),
*/

    override fun toStatement(model: Role, row: UpdateBuilder<Int>) {
        row[table.name] = model.name
        row[table.role] = model.role
    }

    override fun toModel(row: ResultRow): Role {
        return Role(
            id = row[table.id].value,
            createdOn = row[table.createdOn],
            name = row[table.name],
            role = row[table.role],
        )
    }

    override fun filterWithSearchTerm(query: Query, searchTerm: String) {
        TODO("Not yet implemented")
    }

    fun create(name: String, privileges: List<Privilege>): UUID {
        val id = RolesTable.insert {
            it[RolesTable.name] = name
            it[role] = RolePrivilegesColumn(privileges)
        } get RolesTable.id
        return id.value
    }

    fun update(id: UUID, name: String, privileges: List<Privilege>) {
        RolesTable.update({
            RolesTable.id eq id
        }) {
            it[RolesTable.name] = name
            it[role] = RolePrivilegesColumn(privileges)
        }
    }

}