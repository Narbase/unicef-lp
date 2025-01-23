package sd.gov.moe.lp.data.conversions.formtemplates

import sd.gov.moe.lp.data.access.formtemplates.FormTemplate
import sd.gov.moe.lp.dto.domain.formtemplates.FormTemplateDto
import sd.gov.moe.lp.dto.common.toStringUUID
import sd.gov.moe.lp.common.toUUID
import sd.gov.moe.lp.domain.utils.toDto
import sd.gov.moe.lp.domain.utils.toDateTime

fun FormTemplate.toDto(): FormTemplateDto {
	return FormTemplateDto(
		id = id?.toStringUUID(),
		template = template,
		name = name,
		createdOn = createdOn?.toDto(),
	)
}

fun FormTemplateDto.toModel(): FormTemplate {
	return FormTemplate(
		id = id?.toUUID(),
		template = template,
		name = name,
		createdOn = createdOn?.toDateTime(),
	)
}
