package sd.gov.moe.lp.student.utils.dialog

import com.narbase.kunafa.core.components.TextInput
import com.narbase.kunafa.core.components.View
import com.narbase.kunafa.core.components.textInput
import com.narbase.kunafa.core.components.textView
import com.narbase.kunafa.core.css.*
import com.narbase.kunafa.core.dimensions.dependent.matchParent
import com.narbase.kunafa.core.dimensions.px
import com.narbase.kunafa.core.drawable.Color
import sd.gov.moe.lp.student.common.AppColors
import sd.gov.moe.lp.student.common.AppFontSizes
import sd.gov.moe.lp.student.utils.views.TextArea
import sd.gov.moe.lp.student.utils.views.textArea
import sd.gov.moe.lp.student.utils.views.theme.AdminTheme.textAreaStyle
import sd.gov.moe.lp.student.utils.views.theme.AdminTheme.textInputErrorStyle
import sd.gov.moe.lp.student.utils.views.theme.AdminTheme.textInputStyle

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */


fun View.labeledTextInput(
    title: String,
    onChange: (() -> Unit)? = null,
    textInputOption: (TextInput.() -> Unit)? = null
): TextInput {
    titleField(title)
    val textInput = textInputField(title, textInputOption)
    onChange?.let { textInput.element.oninput = { onChange() } }
    return textInput
}

fun TextInput.handleOnChange() {
    resetStyle()
}

fun View.addErrorStyle() {
    removeRuleSet(textInputStyle)
    addRuleSet(textInputErrorStyle)
}

fun View.resetStyle() {
    removeRuleSet(textInputErrorStyle)
    addRuleSet(textInputStyle)
}

fun TextInput?.validateAndGetText(): String? {
    val text = this?.text ?: ""
    if (text.isBlank()) {
        this?.addErrorStyle()
        return null
    } else {
        this?.resetStyle()
    }
    return text
}


fun View.labeledTextArea(
    title: String,
    onChange: (() -> Unit)? = null,
    textAreaOption: (TextArea.() -> Unit)? = null
): TextArea {
    titleField(title)
    val textArea = textAreaField(title, textAreaOption)
    onChange?.let { textArea.element.onchange = { onChange() } }
    return textArea
}


fun View.titleField(title: String, textColor: Color = AppColors.black) {
    textView {
        style {
            color = textColor
            fontSize = AppFontSizes.smallText
            marginBottom = 8.px
            fontWeight = "bold"
        }
        text = title
    }
}

fun View.textInputField(title: String, textInputOption: (TextInput.() -> Unit)? = null) = textInput {
    placeholder = title
    style {
        width = matchParent
        marginBottom = 16.px
    }
    addRuleSet(textInputStyle)
    textInputOption?.invoke(this)
}

private fun View.textAreaField(title: String, textAreaOption: (TextArea.() -> Unit)? = null) = textArea {
    placeholder = title
    style {
        width = matchParent
        marginBottom = 16.px
    }
    addRuleSet(textAreaStyle)
    textAreaOption?.invoke(this)
}
