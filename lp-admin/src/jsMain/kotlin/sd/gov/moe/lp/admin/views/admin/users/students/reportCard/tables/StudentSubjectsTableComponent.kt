package sd.gov.moe.lp.admin.views.admin.users.students.reportCard.tables

import com.narbase.kunafa.core.components.Component
import com.narbase.kunafa.core.components.View
import com.narbase.kunafa.core.components.verticalLayout
import com.narbase.kunafa.core.components.view
import com.narbase.kunafa.core.css.height
import com.narbase.kunafa.core.css.width
import com.narbase.kunafa.core.dimensions.dependent.matchParent
import com.narbase.kunafa.core.dimensions.dependent.wrapContent
import com.narbase.kunafa.core.lifecycle.LifecycleOwner
import sd.gov.moe.lp.admin.translations.localized
import sd.gov.moe.lp.admin.utils.scrollable.scrollable
import sd.gov.moe.lp.admin.utils.table.headerCell
import sd.gov.moe.lp.admin.utils.table.listTable
import sd.gov.moe.lp.admin.utils.table.tableCell
import sd.gov.moe.lp.admin.utils.table.tableRow
import sd.gov.moe.lp.admin.utils.verticalSeparator
import sd.gov.moe.lp.admin.utils.views.PaginationControls
import sd.gov.moe.lp.admin.utils.views.matchParentDimensions
import sd.gov.moe.lp.admin.utils.views.setupPaginationControls
import sd.gov.moe.lp.admin.utils.views.withLoadingAndError
import kotlin.js.Date

class StudentSubjectsTableComponent(private val viewModel: StudentSubjectsTableViewModel) : Component() {
    private var paginationControls: PaginationControls? = null

    private var listTableBody: View? = null
    private var contentLayout: View? = null

    override fun onViewCreated(lifecycleOwner: LifecycleOwner) {
        super.onViewCreated(lifecycleOwner)
        contentLayout?.withLoadingAndError(viewModel.uiState, onRetryClicked = {
            viewModel.getItems()
        }, onLoaded = {
            onListLoaded()
        })
    }

    override fun onViewMounted(lifecycleOwner: LifecycleOwner) {
        super.onViewMounted(lifecycleOwner)
        viewModel.getItems()
    }

    private fun onListLoaded() {
        paginationControls?.update(viewModel.pageNo, viewModel.pageSize, viewModel.total)
        listTableBody?.clearAllChildren()
        listTableBody?.apply {
            viewModel.data.forEachIndexed { index, item ->
                tableRow {
                    id = item.studentSubject.id
                    tableCell(item.subject.name, 1)
                    tableCell(item.studentSubject.progress.toString(), 1)
                    tableCell(item.studentSubjectAssessments.filter { it.progress >= 100.0 }.size.toString(), 1)
                    tableCell(item.studentSubjectAssessments.map { it.progress }.average().toString(), 1)
                    item.studentSubject.createdOn?.let { tableCell(Date(it.milliSeconds).toDateString(), 1) }
                    tableCell(item.studentSubject.completedOn?.let { Date(it.milliSeconds).toDateString() }
                        ?: "Not completed", 1)
                }

                if (index != viewModel.data.lastIndex) {
                    verticalSeparator()
                }
            }
        }
    }

    override fun View?.getView() = view {
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
                }
                listTableBody = listTable {
                    headerCell("Subject name".localized(), 1)
                    headerCell("% Subject progress".localized(), 1)
                    headerCell("Completed assessments".localized(), 1)
                    headerCell("% Average assessments score".localized(), 1)
                    headerCell("Enrolled on".localized(), 1)
                    headerCell("Completed on".localized(), 1)
                }
                paginationControls = setupPaginationControls(viewModel::getNextPage, viewModel::getPreviousPage)
            }
        }
    }

}