package sd.gov.moe.lp.data.models.lists

import sd.gov.moe.lp.data.access.utils.ModelWithId
import java.util.*

data class ListItem(
    override val id: UUID?,
    val name: String,
) : ModelWithId<UUID>