package sd.gov.moe.lp.admin.views.admin.content.learningPaths

import com.narbase.kunafa.core.components.*
import com.narbase.kunafa.core.components.layout.LinearLayout
import com.narbase.kunafa.core.css.*
import com.narbase.kunafa.core.dimensions.dependent.matchParent
import com.narbase.kunafa.core.dimensions.dependent.wrapContent
import com.narbase.kunafa.core.dimensions.px
import com.narbase.kunafa.core.lifecycle.LifecycleOwner
import sd.gov.moe.lp.admin.translations.localized
import sd.gov.moe.lp.admin.utils.horizontalFiller
import sd.gov.moe.lp.admin.utils.scrollable.scrollable
import sd.gov.moe.lp.admin.utils.table.headerCell
import sd.gov.moe.lp.admin.utils.table.listTable
import sd.gov.moe.lp.admin.utils.table.tableCell
import sd.gov.moe.lp.admin.utils.table.tableRow
import sd.gov.moe.lp.admin.utils.verticalSeparator
import sd.gov.moe.lp.admin.utils.views.*
import sd.gov.moe.lp.admin.utils.views.theme.adminTheme

class LearningPathsManagementComponent : Component() {
    private var paginationControls: PaginationControls? = null
    private val viewModel = LearningPathsManagementViewModel()
    private val upsertDialog = UpsertPathDialog(viewModel)

    private var listTableBody: View? = null
    private var contentLayout: View? = null

    override fun onViewCreated(lifecycleOwner: LifecycleOwner) {
        super.onViewCreated(lifecycleOwner)
        contentLayout?.withLoadingAndError(viewModel.uiState, onRetryClicked = {
            viewModel.getPaths()
        }, onLoaded = {
            onListLoaded()
        })
    }

    override fun onViewMounted(lifecycleOwner: LifecycleOwner) {
        super.onViewMounted(lifecycleOwner)
        viewModel.getPaths()
    }

    private fun onListLoaded() {
        paginationControls?.update(viewModel.pageNo, viewModel.pageSize, viewModel.total)
        listTableBody?.clearAllChildren()
        listTableBody?.apply {
            viewModel.data.forEachIndexed { index, item ->
                tableRow {
                    id = item.name
                    tableCell(item.name, 3, 16.px)
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
        id = "pathManagementRootView"
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
                        text = "LearningPaths".localized()
                        style {
                            width = wrapContent
                            fontSize = 20.px
                            fontWeight = "bold"
                        }
                    }

                    horizontalFiller()

                    addButton()
                    horizontalFiller(adminTheme.narrowSpacing)
                    // todo: discuss add to the upsert dialog
                    adminTheme.mainButton(this, "Edit admins".localized()) {

                    }
                    horizontalFiller(adminTheme.narrowSpacing)
                    adminTheme.mainButton(this, "Edit rules".localized()) {

                    }

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
                    headerCell("Learning path".localized(), 1)
                }
                paginationControls = setupPaginationControls(viewModel::getNextPage, viewModel::getPreviousPage)
            }
        }
    }

    private fun LinearLayout.addButton() {
        adminTheme.mainButton(this, "+ Add new learning path".localized()) {
        onClick = {
                upsertDialog.add()
            }
            id = "addPathButton"
        }
    }

}