package sd.gov.moe.lp.common.db.migrations.usermigrations

import sd.gov.moe.lp.common.db.migrations.Migration
import sd.gov.moe.lp.common.db.migrations.version
import org.jetbrains.exposed.sql.transactions.transaction

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
object AddCountryToStaffTable : Migration("AddCountryToStaffTable", version(2025, 1, 23, 13, 46)) {
    override fun up() {
        transaction {
            exec(
                """
                    ALTER TABLE staff DROP COLUMN calling_code;
                    ALTER TABLE staff DROP COLUMN local_phone;

                    ALTER TABLE staff ADD country text NOT NULL;

                """.trimIndent()
            )

        }

    }

    override fun down() {
        transaction {
            exec(
                """
                    ALTER TABLE staff DROP COLUMN country;

                    ALTER TABLE staff ADD calling_code text NOT NULL;
                    ALTER TABLE staff ADD local_phone text NOT NULL;

                     """
            )
        }

    }

}
