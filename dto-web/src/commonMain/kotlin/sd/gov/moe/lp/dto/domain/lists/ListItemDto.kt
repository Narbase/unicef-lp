package sd.gov.moe.lp.dto.domain.lists

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport

@JsExport
data class ListItemDto(
    val id: StringUUID?,
    val name: String,
)

fun ListItemDto.copyX(): ListItemDto {
    return ListItemDto(id = id, name = name)
}