package sd.gov.moe.lp.web.views.admin.users.students


import com.narbase.kunafa.core.components.*
import com.narbase.kunafa.core.css.*
import com.narbase.kunafa.core.dimensions.dependent.matchParent
import com.narbase.kunafa.core.dimensions.dependent.weightOf
import com.narbase.kunafa.core.dimensions.dependent.wrapContent
import com.narbase.kunafa.core.dimensions.dimen
import com.narbase.kunafa.core.dimensions.px
import com.narbase.kunafa.core.dimensions.vh
import com.narbase.kunafa.core.drawable.Color
import sd.gov.moe.lp.dto.common.network.ItemList
import sd.gov.moe.lp.dto.domain.admin.GetGradesEndpoint
import sd.gov.moe.lp.dto.models.*
import sd.gov.moe.lp.web.common.AppColors
import sd.gov.moe.lp.web.network.remoteProcess
import sd.gov.moe.lp.web.translations.localized
import sd.gov.moe.lp.web.utils.dialog.*
import sd.gov.moe.lp.web.utils.scrollable.ScrollableView
import sd.gov.moe.lp.web.utils.scrollable.scrollable
import sd.gov.moe.lp.web.utils.views.*

class UpsertStudentDialog(val viewModel: StudentsManagementViewModel) : Component() {
    private var popUp: PopUpDialog? = null
    private var popupScrollable: ScrollableView? = null

    private var errorTextView: TextView? = null
    private var studentNameTextInput: TextInput? = null
    private var userNameTextInput: TextInput? = null
    private var passwordTextInput: TextInput? = null
    private var gradeDropDownList: RemoteDropDownList<GradeDto>? = null
    private var studentGrade: GradeDto? = null

    override fun View?.getView() = view {
        style {
            width = 0.px
            height = 0.px
        }
        popUp = popUpDialog { }
    }

    private fun upsertDialog(extendedStudentProfileInfoDto: ExtendedStudentProfileInfoDto? = null) {
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
                        text =
                            if (extendedStudentProfileInfoDto == null) "Add student".localized() else "Edit student".localized()
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
                            infoForm()
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
                            onSaveButtonClicked(extendedStudentProfileInfoDto)
                        }
                    }
                    viewModel.upsertUiState.clearObservers()
                    saveButton.withLoadingAndError(viewModel.upsertUiState,
                        onRetryClicked = {
                            onSaveButtonClicked(extendedStudentProfileInfoDto)
                        },
                        onLoaded = {
                            popUp?.dismissDialog()
                            viewModel.getStudents()
                        }
                    )
                }
            }
        }
        studentNameTextInput?.element?.focus()
    }

    private fun onSaveButtonClicked(extendedStudentProfileInfoDto: ExtendedStudentProfileInfoDto? = null) {
        isDataValid = true
        val name = studentNameTextInput.validateAndGetText()?.trim()
        val userName = userNameTextInput.validateAndGetText()?.trim()
        val password = passwordTextInput.validateAndGetText()?.trim()
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
            verticalLayout {
                style {
                    width = weightOf(1)
                }
                studentNameTextInput = labeledTextInput("Full name".localized())
                studentNameTextInput?.element?.oninput = {
                    studentNameTextInput?.handleOnChange()
                }
                studentNameTextInput?.id = "FullNameInput"

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
                    showAutoComplete = true,
                )
                userNameTextInput = labeledTextInput("User name".localized())
                userNameTextInput?.element?.oninput = {
                    userNameTextInput?.handleOnChange()
                }
                userNameTextInput?.id = "userNameInput"

                passwordTextInput = labeledTextInput("password".localized())
                passwordTextInput?.element?.oninput = {
                    passwordTextInput?.handleOnChange()
                }
                passwordTextInput?.id = "passwordInput"
            }
            verticalLayout {
                style {
                    width = weightOf(1)
                    marginStart = 12.px
                }
            }
        }

    }

    fun add() {
        upsertDialog()
        studentGrade = null
    }

    fun edit(dto: ExtendedStudentProfileInfoDto) {
        upsertDialog(dto)
        studentNameTextInput?.text = dto.student.fullName
        studentGrade = dto.student.grade
        userNameTextInput?.text = dto.client.userName
        passwordTextInput?.text = dto.client.password
    }

}
