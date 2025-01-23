package sd.gov.moe.lp.dto.domain.nongradedassessments

import sd.gov.moe.lp.dto.common.StringUUID
import kotlin.js.JsExport
import com.narbase.oss.dto.common.forms.EntryListDto
import sd.gov.moe.lp.dto.common.datetime.DateTimeDto

@JsExport
data class NonGradedAssessmentDto(
	val id: StringUUID?,
	val lessonId: StringUUID,
	val form: EntryListDto,
	val createdOn: DateTimeDto?,
)

