package sd.gov.moe.lp.web.views.admin.users.groups


import com.narbase.kunafa.core.components.*
import com.narbase.kunafa.core.css.*
import com.narbase.kunafa.core.dimensions.dependent.matchParent
import com.narbase.kunafa.core.dimensions.dependent.weightOf
import com.narbase.kunafa.core.dimensions.dependent.wrapContent
import com.narbase.kunafa.core.dimensions.dimen
import com.narbase.kunafa.core.dimensions.px
import com.narbase.kunafa.core.dimensions.vh
import com.narbase.kunafa.core.drawable.Color
import sd.gov.moe.lp.dto.models.GroupDto
import sd.gov.moe.lp.web.common.AppColors
import sd.gov.moe.lp.web.translations.localized
import sd.gov.moe.lp.web.utils.dialog.labeledTextInput
import sd.gov.moe.lp.web.utils.dialog.textInputStyle
import sd.gov.moe.lp.web.utils.scrollable.ScrollableView
import sd.gov.moe.lp.web.utils.scrollable.scrollable
import sd.gov.moe.lp.web.utils.views.*

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
class UpsertGroupDialog(val viewModel: GroupsManagementViewModel) : Component() {
    private var popUp: PopUpDialog? = null
    private var popupScrollable: ScrollableView? = null

    private var errorTextView: TextView? = null
    private var nameTextInput: TextInput? = null

    override fun View?.getView() = view {
        style {
            width = 0.px
            height = 0.px
        }
        popUp = popUpDialog { }
    }

    private fun upsertDialog(groupDto: GroupDto? = null) {
        popUp?.showDialog {
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
                        text = if (groupDto == null) "Add group".localized() else "Edit group".localized()
                    }

                }

                verticalLayout {
                    style {
                        width = matchParent
                        maxHeight = 60.vh
                    }

                    popupScrollable = scrollable {
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
                            fullNameAndPreferredName()
                        }

                    }
                }

                errorTextView = textView {
                    style {
                        marginBottom = 8.px
                        fontSize = 14.px
                        color = AppColors.redLight
                        padding = 20.px
                    }
                    isVisible = false
                    text = "Please enter valid fields values".localized()
                }

                horizontalLayout {
                    style {
                        width = matchParent
                        height = wrapContent
                        justifyContent = JustifyContent.End
                        padding = 20.px
                    }

                    val saveButton = button {
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
                        text = "Save".localized()
                        id = "SaveButton"
                        onClick = {
                            onSaveButtonClicked(groupDto)
                        }
                    }
                    viewModel.upsertUiState.clearObservers()
                    saveButton.withLoadingAndError(viewModel.upsertUiState,
                        onRetryClicked = {
                            onSaveButtonClicked(groupDto)
                        },
                        onLoaded = {
                            popUp?.dismissDialog()
                            viewModel.getGroups()
                        }
                    )
                }
            }
        }
        nameTextInput?.element?.focus()
    }

    private fun onSaveButtonClicked(groupDto: GroupDto? = null) {
        isDataValid = true
        val groupName = nameTextInput.validateAndGetText().trim()

        errorTextView?.isVisible = isDataValid.not()
        if (isDataValid.not()) return

        val dto = GroupDto(
            groupDto?.id,
            groupName,
        )
        if (groupDto == null) {
            viewModel.addGroup(dto)
        } else {
            viewModel.editGroup(dto)
        }

    }

    private var isDataValid = false
    private fun TextInput?.validateAndGetText(): String {
        val text = this?.text ?: ""
        if (text.isBlank()) {
            isDataValid = false
            this?.addErrorStyle()
        } else {
            this?.resetStyle()
        }
        return text
    }

    private fun View.fullNameAndPreferredName() {
        horizontalLayout {
            style {
                width = matchParent
                marginBottom = 12.px
            }
            verticalLayout {
                style {
                    width = weightOf(3)
                }

                nameTextInput = labeledTextInput("Full name".localized())
                nameTextInput?.element?.oninput = {
                    nameTextInput?.handleOnFullNameChanged()
                }
                nameTextInput?.id = "FullNameInput"
            }
            verticalLayout {
                style {
                    width = weightOf(1)
                    marginStart = 12.px
                }
            }
        }

    }

    private fun TextInput.handleOnFullNameChanged() {
        resetStyle()
    }

    private fun View.addErrorStyle() {
        removeRuleSet(textInputStyle)
        addRuleSet(textInputErrorStyle)
    }

    private fun View.resetStyle() {
        removeRuleSet(textInputErrorStyle)
        addRuleSet(textInputStyle)
    }

    fun add() {
        upsertDialog()
    }

    fun edit(dto: GroupDto) {
        upsertDialog(dto)
        nameTextInput?.text = dto.name
    }

    private val textInputErrorStyle = classRuleSet {
        padding = 4.px
        fontSize = 14.px
        padding = "6px 12px".dimen()
        border = "1px solid ${AppColors.redLight}"
        borderRadius = 4.px
    }
}
