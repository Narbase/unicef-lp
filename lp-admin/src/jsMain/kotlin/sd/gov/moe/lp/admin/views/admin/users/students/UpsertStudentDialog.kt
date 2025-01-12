package sd.gov.moe.lp.admin.views.admin.users.students


import com.narbase.kunafa.core.components.*
import com.narbase.kunafa.core.css.*
import com.narbase.kunafa.core.dimensions.dependent.matchParent
import com.narbase.kunafa.core.dimensions.dependent.weightOf
import com.narbase.kunafa.core.dimensions.dependent.wrapContent
import com.narbase.kunafa.core.dimensions.px
import sd.gov.moe.lp.dto.common.network.ItemList
import sd.gov.moe.lp.dto.domain.admin.GetGradesEndpoint
import sd.gov.moe.lp.dto.models.ClientDto
import sd.gov.moe.lp.dto.models.ExtendedStudentDto
import sd.gov.moe.lp.dto.models.ExtendedStudentProfileInfoDto
import sd.gov.moe.lp.dto.models.GradeDto
import sd.gov.moe.lp.admin.network.remoteProcess
import sd.gov.moe.lp.admin.translations.localized
import sd.gov.moe.lp.admin.utils.dialog.validateAndGetText
import sd.gov.moe.lp.admin.utils.horizontalFiller
import sd.gov.moe.lp.admin.utils.uploaders.ImageUploader
import sd.gov.moe.lp.admin.utils.uploaders.imageUploader
import sd.gov.moe.lp.admin.utils.verticalFiller
import sd.gov.moe.lp.admin.utils.views.RemoteDropDownList
import sd.gov.moe.lp.admin.utils.views.popUpDialog
import sd.gov.moe.lp.admin.utils.views.setupRemoteDropDownList
import sd.gov.moe.lp.admin.utils.views.theme.adminTheme
import sd.gov.moe.lp.admin.utils.views.withLoadingAndError

class UpsertStudentDialog(val viewModel: StudentsManagementViewModel) : Component() {
    private var popUp = popUpDialog { }

    private var errorTextView: TextView? = null
    private var studentNameTextInput: TextInput? = null
    private var userNameTextInput: TextInput? = null
    private var passwordTextInput: TextInput? = null
    private var gradeDropDownList: RemoteDropDownList<GradeDto>? = null
    private var studentGrade: GradeDto? = null
    private var imageUploader: ImageUploader? = null
    private var thumbnailUrl: String? = null

    override fun View?.getView() = view {
        style {
            width = 0.px
            height = 0.px
        }
    }

    private fun upsertDialog(extendedStudentProfileInfoDto: ExtendedStudentProfileInfoDto? = null) {
        adminTheme.showDialog(
            popUp,
            title = if (extendedStudentProfileInfoDto == null) "Add student".localized() else "Edit student".localized()
        ) {
            infoForm()
            errorTextView = adminTheme.errorText(this)

            horizontalLayout {
                style {
                    width = matchParent
                    height = wrapContent
                    justifyContent = JustifyContent.End
                }

                val saveButton = adminTheme.mainButton(this) {
                    text = "Save".localized()
                    id = "saveButton"
                    onClick = {
                        onSaveButtonClicked(extendedStudentProfileInfoDto)
                    }
                }
                viewModel.upsertUiState.clearObservers()
                saveButton.withLoadingAndError(viewModel.upsertUiState,
                    onRetryClicked = {
                        onSaveButtonClicked(extendedStudentProfileInfoDto)
                    },
                    onLoaded = {
                        popUp.dismissDialog()
                        viewModel.getStudents()
                    }
                )
            }
        }
        studentNameTextInput?.element?.focus()
    }

    private fun onSaveButtonClicked(extendedStudentProfileInfoDto: ExtendedStudentProfileInfoDto? = null) {
        isDataValid = true
        val name = studentNameTextInput.validateAndGetText()?.trim()
        val userName = userNameTextInput.validateAndGetText()?.trim()
        val password = passwordTextInput.validateAndGetText()?.trim()
        thumbnailUrl = imageUploader?.imageUrl
        //  fixme: clean the validation
        if (studentGrade == null || name.isNullOrBlank() || userName.isNullOrBlank() || password.isNullOrBlank()) {
            isDataValid = false
        }
        errorTextView?.isVisible = isDataValid.not()
        if (isDataValid.not()) return

        val dto = ExtendedStudentProfileInfoDto(
            ExtendedStudentDto(
                id = extendedStudentProfileInfoDto?.student?.id,
                fullName = name ?: return,
                grade = studentGrade ?: return,
                thumbnailUrl = thumbnailUrl
            ),
            ClientDto(
                id = extendedStudentProfileInfoDto?.client?.id,
                userName = userName ?: return,
                password = password ?: return,
            )
        )
        if (extendedStudentProfileInfoDto == null) {
            viewModel.addStudent(dto)
        } else {
            viewModel.editStudent(dto)
        }

    }

    private var isDataValid = false

    private fun View.infoForm() {
        verticalLayout {
            style {
                width = matchParent
                marginBottom = 12.px
            }
            horizontalLayout {
                style {
                    width = matchParent
                }
                verticalLayout {
                    style {
                        width = weightOf(1)
                    }
                    studentNameTextInput = adminTheme.labeledTextInput(this, "Full name".localized(), isRequired = true)
                }
                horizontalFiller(adminTheme.standardSpacing)
                verticalLayout {
                    style {
                        width = weightOf(1)
                    }
                    adminTheme.label(this, "Grade".localized(), isRequired = true)
                    gradeDropDownList = setupRemoteDropDownList(
                        name = "Grade".localized(),
                        getList = { pageNo, pageSize, searchTerm ->
                            val response = GetGradesEndpoint.remoteProcess(
                                GetGradesEndpoint.Request(
                                    pageNo = pageNo,
                                    pageSize = pageSize,
                                    searchTerm = searchTerm,
                                )
                            )
                            ItemList(response.data.listAndTotal.list, response.data.listAndTotal.total.toInt())
                        },
                        itemToString = { it.name },
                        onItemSelected = { studentGrade = it },
                        defaultItem = studentGrade,
                        viewWidthFactory = { matchParent },
                        showAutoComplete = true,
                    )
                }
            }
            adminTheme.label(this, "Profile picture".localized(), isRequired = true)
            imageUploader = imageUploader(defaultImageUrl = thumbnailUrl)
            verticalFiller(adminTheme.standardSpacing)
            horizontalLayout {
                style {
                    width = matchParent
                }
                verticalLayout {
                    style {
                        width = weightOf(1)
                    }
                    userNameTextInput = adminTheme.labeledTextInput(this, "User name".localized(), isRequired = true)
                }
                horizontalFiller(adminTheme.standardSpacing)
                verticalLayout {
                    style {
                        width = weightOf(1)
                    }

                    passwordTextInput = adminTheme.labeledTextInput(this, "Password".localized(), isRequired = true)
                }
            }
        }

    }

    fun add() {
        studentGrade = null
        thumbnailUrl = null
        upsertDialog()
    }

    fun edit(dto: ExtendedStudentProfileInfoDto) {
        studentGrade = dto.student.grade
        thumbnailUrl = dto.student.thumbnailUrl
        upsertDialog(dto)
        studentNameTextInput?.text = dto.student.fullName
        userNameTextInput?.text = dto.client.userName
        passwordTextInput?.text = dto.client.password
    }

}
