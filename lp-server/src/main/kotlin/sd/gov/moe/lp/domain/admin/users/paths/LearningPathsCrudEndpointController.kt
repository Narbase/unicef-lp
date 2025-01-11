@file:Suppress("unused")

package sd.gov.moe.lp.domain.admin.users.paths

import sd.gov.moe.lp.common.auth.loggedin.AuthorizedClientData
import sd.gov.moe.lp.data.models.utils.ListAndTotal
import sd.gov.moe.lp.domain.user.crud.EndpointCrudController
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.dto.domain.admin.LearningPathsCrudEndpoint
import sd.gov.moe.lp.dto.models.LearningPathDto
import java.util.*

class LearningPathsCrudEndpointController : EndpointCrudController<LearningPathDto, LearningPathsCrudEndpoint.Filters>(
    listOf(LearningPathsCrudEndpoint),
    LearningPathDto::class,
    LearningPathsCrudEndpoint.Filters::class,
) {
    override fun getItemsList(
        pageNo: Long,
        pageSize: Int,
        searchTerm: String,
        filters: Map<String, String>,
        data: LearningPathsCrudEndpoint.Filters?,
        clientData: AuthorizedClientData?
    ): ListAndTotal<LearningPathDto> {
        return ListAndTotal(list = learningPathList, total = learningPathList.size.toLong())
    }

    override fun createItem(item: LearningPathDto, clientData: AuthorizedClientData?): LearningPathDto {
        val newItem = LearningPathDto(id = UUID.randomUUID().toStringUUID(), name = item.name)
        learningPathList = learningPathList.plus(newItem)
        return learningPathList.find { it.id == newItem.id } ?: throw IllegalArgumentException("not found")
    }

    override fun updateItem(item: LearningPathDto, clientData: AuthorizedClientData?): LearningPathDto {
        learningPathList = learningPathList.map { if (it.id == item.id) item else it }
        return learningPathList.find { it.id == item.id } ?: throw IllegalArgumentException("not found")
    }

    override fun deleteItem(id: UUID?, clientData: AuthorizedClientData?) {
        TODO("Not yet implemented")
    }

}

var learningPathList =
    listOf(
        LearningPathDto(id = UUID.randomUUID().toStringUUID(), name = "path 1"),
        LearningPathDto(id = UUID.randomUUID().toStringUUID(), name = "path 2"),
        LearningPathDto(id = UUID.randomUUID().toStringUUID(), name = "path 3"),
        LearningPathDto(id = UUID.randomUUID().toStringUUID(), name = "path 4"),
    )

