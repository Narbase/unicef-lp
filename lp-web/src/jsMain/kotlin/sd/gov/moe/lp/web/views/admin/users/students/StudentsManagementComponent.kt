package sd.gov.moe.lp.web.views.admin.users.students

import com.narbase.kunafa.core.components.*
import com.narbase.kunafa.core.components.layout.LinearLayout
import com.narbase.kunafa.core.css.*
import com.narbase.kunafa.core.dimensions.dependent.matchParent
import com.narbase.kunafa.core.dimensions.dependent.wrapContent
import com.narbase.kunafa.core.dimensions.dimen
import com.narbase.kunafa.core.dimensions.px
import com.narbase.kunafa.core.drawable.Color
import com.narbase.kunafa.core.lifecycle.LifecycleOwner
import sd.gov.moe.lp.web.common.AppColors
import sd.gov.moe.lp.web.translations.localized
import sd.gov.moe.lp.web.utils.horizontalFiller
import sd.gov.moe.lp.web.utils.scrollable.scrollable
import sd.gov.moe.lp.web.utils.table.headerCell
import sd.gov.moe.lp.web.utils.table.listTable
import sd.gov.moe.lp.web.utils.table.tableCell
import sd.gov.moe.lp.web.utils.table.tableRow
import sd.gov.moe.lp.web.utils.verticalSeparator
import sd.gov.moe.lp.web.utils.views.*

class StudentsManagementComponent : Component() {
    private var paginationControls: PaginationControls? = null
    private val viewModel = StudentsManagementViewModel()
    private val upsertDialog = UpsertStudentDialog(viewModel)

    private var listTableBody: View? = null
    private var contentLayout: View? = null

    override fun onViewCreated(lifecycleOwner: LifecycleOwner) {
        super.onViewCreated(lifecycleOwner)
        contentLayout?.withLoadingAndError(viewModel.uiState, onRetryClicked = {
            viewModel.getStudents()
        }, onLoaded = {
            onListLoaded()
        })
    }

    override fun onViewMounted(lifecycleOwner: LifecycleOwner) {
        super.onViewMounted(lifecycleOwner)
        viewModel.getStudents()
    }

    private fun onListLoaded() {
        paginationControls?.update(viewModel.pageNo, viewModel.pageSize, viewModel.total)
        listTableBody?.clearAllChildren()
        listTableBody?.apply {
            viewModel.data.forEachIndexed { index, item ->
                tableRow {
                    id = item.student.fullName
                    tableCell(item.student.fullName, 3, 16.px)
                    tableCell(item.client.userName, 3, 16.px)
                    tableCell(item.student.grade.name, 3, 16.px)
                    onClick = {
                        upsertDialog.edit(item)
                    }
                }

                if (index != viewModel.data.lastIndex) {
                    verticalSeparator()
                }
            }
        }
    }

    override fun View?.getView() = view {
        id = "studentManagementRootView"
        mount(upsertDialog)
        style {
            matchParentDimensions
        }
        scrollable {
            style {
                matchParentDimensions
            }

            contentLayout = verticalLayout {
                style {
                    width = matchParent
                    height = wrapContent
                    padding = 32.px
                }
                horizontalLayout {
                    style {
                        width = matchParent
                    }
                    textView {
                        text = "Students".localized()
                        style {
                            width = wrapContent
                            fontSize = 20.px
                            fontWeight = "bold"
                        }
                    }

                    horizontalFiller()

                    addStudentButton()


                }

                horizontalLayout {
                    style {
                        width = matchParent
                        marginTop = 16.px
                    }

                    horizontalFiller()
                    searchTextInput("Search".localized()) {
                        viewModel.searchFor(it)
                    }
                }

                listTableBody = listTable {
                    headerCell("Student name".localized(), 1)
                }
                paginationControls = setupPaginationControls(viewModel::getNextPage, viewModel::getPreviousPage)
            }
        }
    }

    private fun LinearLayout.addStudentButton() {
        textView {
            style {
                color = Color.white
                padding = "2px 12px".dimen()
                backgroundColor = AppColors.narcoreColor
                borderRadius = 12.px
                pointerCursor()
                hover {
                    backgroundColor = AppColors.narcoreDarkColor
                }
            }

            onClick = {
                upsertDialog.add()
            }

            id = "AddStudentButton"
            text = "+ Add new student"
        }
    }

}