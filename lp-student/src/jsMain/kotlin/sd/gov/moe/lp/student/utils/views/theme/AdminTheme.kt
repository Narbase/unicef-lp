package sd.gov.moe.lp.student.utils.views.theme

import com.narbase.kunafa.core.components.*
import com.narbase.kunafa.core.components.layout.LinearLayout
import com.narbase.kunafa.core.css.*
import com.narbase.kunafa.core.dimensions.*
import com.narbase.kunafa.core.dimensions.dependent.matchParent
import com.narbase.kunafa.core.dimensions.dependent.wrapContent
import com.narbase.kunafa.core.drawable.Color
import sd.gov.moe.lp.student.common.AppColors
import sd.gov.moe.lp.student.common.AppFontSizes
import sd.gov.moe.lp.student.translations.localized
import sd.gov.moe.lp.student.utils.dialog.handleOnChange
import sd.gov.moe.lp.student.utils.horizontalFiller
import sd.gov.moe.lp.student.utils.scrollable.scrollable
import sd.gov.moe.lp.student.utils.views.*

object AdminTheme {
    fun mainButton(
        parent: View,
        buttonText: String? = null,
        block: Button.() -> Unit
    ): Button {
        return parent.button {
            text = buttonText
            style {
                border = "none"
                color = Color.white
                padding = "2px 12px".dimen()
                backgroundColor = AppColors.narcoreColor
                borderRadius = 12.px
                pointerCursor()
                fontSize = 18.px
                hover {
                    backgroundColor = AppColors.narcoreDarkColor
                }
            }
            block()
        }
    }

    fun textInput(
        parent: View,
        hint: String? = null,
        block: TextInput.() -> Unit
    ): TextInput {
        return parent.textInput {
            style {
                width = matchParent
                borderRadius = standardInputRadius
                backgroundColor = AppColors.white
                border = standardBorder
                padding = standardInputPadding
                alignItems = Alignment.Center
                placeholder = hint ?: ""
                fontSize = AppFontSizes.normalButtonSize
                marginBottom = standardFormSpacing
            }
            block()
        }
    }

    fun textArea(
        parent: View,
        hint: String? = null,
        block: TextArea.() -> Unit
    ): TextArea {
        return parent.textArea {
            style {
                borderRadius = 4.px
                backgroundColor = AppColors.white
                border = standardBorder
                padding = "12px 12px".dimen()
                alignItems = Alignment.Center
                placeholder = hint ?: ""
                fontSize = AppFontSizes.normalButtonSize
                resize = "vertical"
            }
            block()
        }
    }


    fun labeledTextInput(
        parent: View,
        label: String,
        isRequired: Boolean,
        parentRuleSet: RuleSet? = null,
        block: TextInput.() -> Unit = {}
    ): TextInput {
        var textInput: TextInput? = null
        parent.verticalLayout {
            if (parentRuleSet != null)
                addRuleSet(parentRuleSet) else style {
                width = matchParent
            }
            label(this, label, isRequired = isRequired)
            textInput = textInput(this, label) {
                id = "${label}TextInput"
                if (isRequired) {
                    element.oninput = {
                        this.handleOnChange()
                    }
                }
                block()
            }
        }
        return textInput!!
    }

    fun labeledTextArea(
        parent: View,
        label: String,
        isRequired: Boolean = false,
        parentRuleSet: RuleSet? = null,
        block: TextArea.() -> Unit = {}
    ): TextArea {
        var textArea: TextArea? = null
        parent.verticalLayout {
            if (parentRuleSet != null)
                addRuleSet(parentRuleSet)
            else style {
                width = matchParent
            }
            label(this, label, isRequired = isRequired)
            textArea = textArea(this) {
                block()
            }
        }
        return textArea!!
    }


    fun label(parent: View, label: String, isRequired: Boolean): LinearLayout {
        return parent.horizontalLayout {
            style {
                width = matchParent
                height = wrapContent
            }
            horizontalLayout {
                style {
                    paddingEnd = 8.px
                    position = "relative"
                }
                simpleLabel(this) {
                    text = label
                }
                horizontalFiller(standardFormSpacing)

                if (isRequired) {
                    redStarTextView(this).apply {
                        style {
                            position = "absolute"
                            end = 8.px
                        }
                    }
                }
            }
        }
    }


    fun redStarTextView(parent: View): TextView {
        return parent.textView {
            addClass("red_star_text_view")
            text = "*"
            style {
                fontStyle = "bold"
                fontSize = 12.px
                color = AppColors.redLight
            }
        }
    }

    fun simpleLabel(parent: View, block: TextView.() -> Unit): TextView {
        return parent.textView {
            style {
                color = AppColors.black
                fontSize = AppFontSizes.smallText
                marginBottom = 8.px
                fontWeight = "bold"
            }
            block()
        }
    }


    fun errorText(parent: View, block: TextView.() -> Unit = {}): TextView {
        return parent.textView {
            style {
                marginTop = 8.px
                width = wrapContent
                height = wrapContent
                fontSize = 14.px
                textAlign = TextAlign.Start
                color = AppColors.redLight
            }
            text = "Please enter valid fields values".localized()
            isVisible = false
            block()

        }
    }

    val standardBorder = "1px solid ${AppColors.borderColor}"
    val standardFocusedBorder = "1px solid ${AppColors.focusInputBorderColor}"
    val standardErrorBorder = "1px solid ${AppColors.redLight}"
    val standardInputPadding = "8px 12px".dimen()
    val standardFormSpacing = 8.px
    val standardInputRadius = 4.px

    val textInputStyle by lazy {
        classRuleSet {
            border = standardBorder
            focus {
                border = standardFocusedBorder
            }
        }
    }

    val textAreaStyle by lazy {
        classRuleSet {
            border = standardBorder
            focus {
                border = standardFocusedBorder
            }
        }
    }
    val textInputErrorStyle by lazy {
        classRuleSet {
            border = standardErrorBorder
        }
    }

    fun showDialog(
        dialog: PopUpDialog,
        title: String,
        isDismissible: Boolean = true,
        block: LinearLayout.() -> Unit
    ) {
        dialog.showDialog(isDismissible) {
            verticalLayout {
                id = "upsertMemberRootView"
                style {
                    height = wrapContent
                    minWidth = 800.px
                    width = matchParent
                    backgroundColor = Color.white
                    borderRadius = 8.px
                }
                horizontalLayout {
                    style {
                        width = matchParent
                    }
                    textView {
                        style {
                            fontWeight = "bold"
                            padding = 20.px
                            fontSize = 16.px
                        }
                        text = title
                    }

                }
                verticalLayout {
                    style {
                        width = matchParent
                        maxHeight = 60.vh
                    }

                    scrollable {
                        style {
                            width = matchParent
                            maxHeight = 60.vh
                        }
                        verticalLayout {
                            style {
                                width = matchParent
                                height = wrapContent
                                padding = 20.px
                            }
                            block()
                        }
                    }
                }

            }
        }
    }

    fun deleteButton(parent: View, block: View.() -> Unit): View =
        parent.apply {
            imageView {
                style {
                    width = 24.px
                    height = 24.px
                    borderRadius = 4.px
                    border = "1px solid transparent"
                    hover {
                        backgroundColor = AppColors.white
                        border = "1px solid ${AppColors.separatorNormal}"
                    }
                }
                block()
                element.src = "/public/img/delete.png"
            }
        }
}