package com.narbase.oss.dto.common.forms

import com.narbase.oss.dto.common.entries.AnswerDto
import com.narbase.oss.dto.common.entries.EntryDto
import kotlin.js.JsExport

/*
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2017] -[2019] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 2024/08/02.
 */
@JsExport
class EntryListDto(
        val entries: Array<EntryDto>
)
@JsExport
class AnswersListDto(
        val answers: Array<AnswerDto>
)