package sd.gov.moe.lp.admin.views.admin.content.learningPaths


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
import sd.gov.moe.lp.dto.models.LearningPathDto

class UpsertPathDialog(val viewModel: LearningPathsManagementViewModel) : Component() {
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

    private fun upsertDialog(learningPathDto: LearningPathDto? = null) {
        adminTheme.showDialog(
            popUp,
            title = if (learningPathDto == null) "Add learning path".localized() else "Edit learning path".localized()
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
                        onSaveButtonClicked(learningPathDto)
                    }
                }
                viewModel.upsertUiState.clearObservers()
                saveButton.withLoadingAndError(viewModel.upsertUiState,
                    onRetryClicked = {
                        onSaveButtonClicked(learningPathDto)
                    },
                    onLoaded = {
                        popUp?.dismissDialog()
                        viewModel.getPaths()
                    }
                )
            }
        }
        nameTextInput?.element?.focus()
    }

    private fun onSaveButtonClicked(learningPathDto: LearningPathDto? = null) {
        isDataValid = true
        val name = nameTextInput.validateAndGetText()?.trim()

        if (name.isNullOrBlank()) {
            isDataValid = false
        }

        errorTextView?.isVisible = isDataValid.not()
        if (isDataValid.not()) return

        val dto = LearningPathDto(
            learningPathDto?.id,
            name ?: return,
        )
        if (learningPathDto == null) {
            viewModel.addPath(dto)
        } else {
            viewModel.editPath(dto)
        }

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
                "Learning path".localized(),
                isRequired = true
            )
        }

    }

    fun add() {
        upsertDialog()
    }

    fun edit(dto: LearningPathDto) {
        upsertDialog(dto)
        nameTextInput?.text = dto.name
    }

}
