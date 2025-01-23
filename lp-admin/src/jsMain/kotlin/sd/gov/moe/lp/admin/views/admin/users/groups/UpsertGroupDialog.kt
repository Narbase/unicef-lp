package sd.gov.moe.lp.admin.views.admin.users.groups


import com.narbase.kunafa.core.components.*
import com.narbase.kunafa.core.css.*
import com.narbase.kunafa.core.dimensions.dependent.matchParent
import com.narbase.kunafa.core.dimensions.dependent.wrapContent
import com.narbase.kunafa.core.dimensions.px
import sd.gov.moe.lp.admin.translations.localized
import sd.gov.moe.lp.admin.utils.dialog.validateAndGetText
import sd.gov.moe.lp.admin.utils.views.popUpDialog
import sd.gov.moe.lp.admin.utils.views.theme.adminTheme
import sd.gov.moe.lp.admin.utils.views.withLoadingAndError
import sd.gov.moe.lp.dto.domain.groups.GroupDto

class UpsertGroupDialog(val viewModel: GroupsManagementViewModel) : Component() {
    private var popUp = popUpDialog { }

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
        adminTheme.showDialog(
            popUp,
            title = if (groupDto == null) "Add group".localized() else "Edit group".localized()
        ) {
            form()
            errorTextView = adminTheme.errorText(this)

            horizontalLayout {
                style {
                    width = matchParent
                    height = wrapContent
                    justifyContent = JustifyContent.End
                }

                val saveButton = adminTheme.mainButton(this) {
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
        nameTextInput?.element?.focus()
    }

    private fun onSaveButtonClicked(groupDto: GroupDto? = null) {
        isDataValid = true
        val groupName = nameTextInput.validateAndGetText()?.trim()

        if (groupName.isNullOrBlank()) {
            isDataValid = false
        }

        errorTextView?.isVisible = isDataValid.not()
        if (isDataValid.not()) return

//        val dto = GroupDto(
//            groupDto?.id,
//            groupName ?: return,
//        )
//        if (groupDto == null) {
//            viewModel.addGroup(dto)
//        } else {
//            viewModel.editGroup(dto)
//        }

    }

    private var isDataValid = false

    private fun View.form() {
        horizontalLayout {
            style {
                width = matchParent
                marginBottom = 12.px
            }

            nameTextInput = adminTheme.labeledTextInput(
                this,
                "Group name".localized(),
                isRequired = true
            )
        }

    }

    fun add() {
        upsertDialog()
    }

    fun edit(dto: GroupDto) {
        upsertDialog(dto)
        nameTextInput?.text = dto.name
    }

}
