package sd.gov.moe.lp.dto.domain.formtemplates

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import com.narbase.oss.dto.common.forms.EntryListDto
import kotlin.String
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class FormTemplateDto(
	val id: StringUUID?,
	val template: EntryListDto,
	val name: String,
	val createdOn: DateTimeDto?,
)

