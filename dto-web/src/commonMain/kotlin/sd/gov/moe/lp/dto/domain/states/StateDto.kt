package sd.gov.moe.lp.dto.domain.states

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import kotlin.String
import sd.gov.moe.lp.dto.common.DtoName
import sd.gov.moe.lp.dto.common.enums.Country
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class StateDto(
	val id: StringUUID?,
	val name: String,
	val country: DtoName<Country>,
	val createdOn: DateTimeDto?,
)

