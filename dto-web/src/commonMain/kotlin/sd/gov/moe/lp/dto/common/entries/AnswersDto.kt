package com.narbase.oss.dto.common.entries

import sd.gov.moe.lp.dto.common.datetime.DateDto
import kotlin.js.JsExport
import kotlin.reflect.KClass

@JsExport
sealed class AnswerDto(val type: String, val entryDto: EntryDto)

@JsExport
class TextViewAnswerDto(entry: TextViewEntryDto) : AnswerDto(EntryTypes.TextView.name, entry)

@JsExport
class SeparatorAnswerDto(entry: SeparatorEntryDto) : AnswerDto(EntryTypes.Separator.name, entry)

@JsExport
class TextInputAnswerDto(val text: String?, entryDto: TextInputEntryDto) :
    AnswerDto(EntryTypes.TextInput.name, entryDto)

@JsExport
class PhoneInputAnswerDto(val callingCode: String?, val phoneNumber: String?, entryDto: PhoneInputEntryDto) :
    AnswerDto(EntryTypes.PhoneInput.name, entryDto)

@JsExport
class McqAnswerDto(val selectedOption: Int?, entryDto: McqEntryDto) : AnswerDto(EntryTypes.Mcq.name, entryDto)

@JsExport
class CheckListAnswerDto(val selectedOptions: Array<Int>?, entryDto: CheckListEntryDto) :
    AnswerDto(EntryTypes.CheckList.name, entryDto)

@JsExport
class ImageAnswerDto(val imageUrl: String?, entryDto: ImageEntryDto) : AnswerDto(EntryTypes.Image.name, entryDto)

@JsExport
class FileAnswerDto(val fileUrl: String?, entryDto: FileEntryDto) : AnswerDto(EntryTypes.File.name, entryDto)

@JsExport
class SignatureAnswerDto(val imageUrl: String?, entryDto: SignatureEntryDto) :
    AnswerDto(EntryTypes.Signature.name, entryDto)

@JsExport
class DateAnswerDto(val date: DateDto?, entryDto: DateEntryDto) : AnswerDto(EntryTypes.Date.name, entryDto)

@JsExport
class GroupAnswerDto(val answers: Array<AnswerDto>?, entryDto: GroupEntryDto) :
    AnswerDto(EntryTypes.Group.name, entryDto)

@JsExport
sealed class EntryDto(val type: String, var id: String?, var isRequired: Boolean? = null) {
    var conditions: Array<EntryCondition>? = null
}

@JsExport
class TextViewEntryDto(val text: String, id: String?) : EntryDto(EntryTypes.TextView.name, id, null)

@JsExport
class SeparatorEntryDto(val color: String, id: String?) : EntryDto(EntryTypes.Separator.name, id, null)

@JsExport
class TextInputEntryDto(
    val text: String,
    id: String?,
    isRequired: Boolean,
    val data: TextInputEntryDataDto? = null
) :
    EntryDto(EntryTypes.TextInput.name, id, isRequired)

@JsExport
class PhoneInputEntryDto(
    val text: String,
    id: String?,
    isRequired: Boolean,
) :
    EntryDto(EntryTypes.PhoneInput.name, id, isRequired)

@JsExport
class TextInputEntryDataDto(
    val match: String?,
)

@JsExport
class McqEntryDto(val text: String, val options: Array<McqOptionDto>, id: String?) :
    EntryDto(EntryTypes.Mcq.name, id, null)

@JsExport
class McqOptionDto(val text: String, val id: Int)

@JsExport
data class SimpleOptionDto(val text: String, val id: String)

@JsExport
class CheckListEntryDto(
    val text: String,
    val options: Array<CheckListOptionDto>, id: String?,
    val data: CheckListEntryDataDto? = null
) :
    EntryDto(EntryTypes.CheckList.name, id, null)

@JsExport
class CheckListEntryDataDto

@JsExport
class CheckListOptionDto(val text: String, val id: Int)

@JsExport
class ImageEntryDto(val text: String, id: String?) : EntryDto(EntryTypes.Image.name, id, null)

@JsExport
class FileEntryDto(val text: String, id: String?, isRequired: Boolean) :
    EntryDto(EntryTypes.File.name, id, isRequired)

@JsExport
class SignatureEntryDto(val text: String, id: String?) : EntryDto(EntryTypes.Signature.name, id, null)

@JsExport
class DateEntryDto(val text: String, id: String?) : EntryDto(EntryTypes.Date.name, id, null)

@JsExport
class GroupEntryDto(val text: String, val entries: Array<EntryDto>, id: String?) :
    EntryDto(EntryTypes.Group.name, id, null)

@JsExport
class CustomEntryDto(val name: String, val data: String, id: String?) : EntryDto(EntryTypes.Custom.name, id = id, null)

@JsExport
class CustomAnswerDto(entry: CustomEntryDto, val answer: String? = null) : AnswerDto(EntryTypes.Custom.name, entry)

@JsExport
class DropDownListEntryDto(
    val text: String,
    val options: Array<SimpleOptionDto>,
    id: String?,
    val showSearch: Boolean?
) : EntryDto(
    EntryTypes.DropDownList.name, id, null
)

@JsExport
class DropDownListAnswerDto(val selectedOptionId: String?, entryDto: DropDownListEntryDto) :
    AnswerDto(EntryTypes.DropDownList.name, entryDto)

@JsExport
class RemoteDropDownListEntryDto(
    val text: String,
    val endpoint: String, id: String?,
    val showSearch: Boolean?
) : EntryDto(EntryTypes.RemoteDropDownList.name, id, null)

@JsExport
class RemoteDropDownListAnswerDto(
    val selectedOption: SimpleOptionDto?,
    entryDto: RemoteDropDownListEntryDto,
) : AnswerDto(EntryTypes.RemoteDropDownList.name, entryDto) {
    class Request(val pageNo: Int, val pageSize: Int, val searchTerm: String?)
    class Response(val list: Array<SimpleOptionDto>)

}

@JsExport
class CheckDropDownListEntryDto(
    val text: String,
    val options: Array<SimpleOptionDto>,
    id: String?,
    val showSearch: Boolean?
) : EntryDto(EntryTypes.CheckDropDownList.name, id, null)

@JsExport
class CheckDropDownListAnswerDto(
    val selectedOptionIds: Array<String>,
    entryDto: CheckDropDownListEntryDto
) : AnswerDto(EntryTypes.CheckDropDownList.name, entryDto)

@JsExport
class RemoteCheckDropDownListEntryDto(
    val text: String,
    val endpoint: String, id: String?,
    val showSearch: Boolean?
) : EntryDto(EntryTypes.RemoteCheckDropDownList.name, id, null)

@JsExport
class RemoteCheckDropDownListAnswerDto(
    val selectedOptions: Array<SimpleOptionDto>,
    entryDto: RemoteCheckDropDownListEntryDto,
) : AnswerDto(EntryTypes.RemoteCheckDropDownList.name, entryDto) {
    class Request(val pageNo: Int, val pageSize: Int, val searchTerm: String?)
    class Response(val list: Array<SimpleOptionDto>)
}


private val answerDtoClassToName = EntryTypes.values().map {
    val targetClass = when (it) {
        EntryTypes.TextView -> TextViewAnswerDto::class
        EntryTypes.Separator -> SeparatorAnswerDto::class
        EntryTypes.TextInput -> TextInputAnswerDto::class
        EntryTypes.PhoneInput -> PhoneInputAnswerDto::class
        EntryTypes.Image -> ImageAnswerDto::class
        EntryTypes.Signature -> SignatureAnswerDto::class
        EntryTypes.Date -> DateAnswerDto::class
        EntryTypes.Mcq -> McqAnswerDto::class
        EntryTypes.CheckList -> CheckListAnswerDto::class
        EntryTypes.Group -> GroupAnswerDto::class
        EntryTypes.Custom -> CustomAnswerDto::class
        EntryTypes.DropDownList -> DropDownListAnswerDto::class
        EntryTypes.RemoteDropDownList -> RemoteDropDownListAnswerDto::class
        EntryTypes.CheckDropDownList -> CheckDropDownListAnswerDto::class
        EntryTypes.RemoteCheckDropDownList -> RemoteCheckDropDownListAnswerDto::class
        EntryTypes.File -> FileAnswerDto::class
    }
    targetClass to it.name
}

val answerDtoToTypeMap: Map<KClass<out AnswerDto>, String> = mapOf(*(answerDtoClassToName.toTypedArray()))


private val entryDtoClassToName = EntryTypes.values().map {
    val targetClass = when (it) {
        EntryTypes.TextInput -> TextInputEntryDto::class
        EntryTypes.PhoneInput -> PhoneInputEntryDto::class
        EntryTypes.Mcq -> McqEntryDto::class
        EntryTypes.CheckList -> CheckListEntryDto::class
        EntryTypes.Image -> ImageEntryDto::class
        EntryTypes.Signature -> SignatureEntryDto::class
        EntryTypes.TextView -> TextViewEntryDto::class
        EntryTypes.Date -> DateEntryDto::class
        EntryTypes.Group -> GroupEntryDto::class
        EntryTypes.Separator -> SeparatorEntryDto::class
        EntryTypes.Custom -> CustomEntryDto::class
        EntryTypes.DropDownList -> DropDownListEntryDto::class
        EntryTypes.RemoteDropDownList -> RemoteDropDownListEntryDto::class
        EntryTypes.CheckDropDownList -> CheckDropDownListEntryDto::class
        EntryTypes.RemoteCheckDropDownList -> RemoteCheckDropDownListEntryDto::class
        EntryTypes.File -> FileEntryDto::class
    }
    targetClass to it.name
}

val entryDtoToTypeMap: Map<KClass<out EntryDto>, String> = mapOf(*(entryDtoClassToName.toTypedArray()))