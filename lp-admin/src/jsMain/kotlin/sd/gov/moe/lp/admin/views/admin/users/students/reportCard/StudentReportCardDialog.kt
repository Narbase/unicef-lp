package sd.gov.moe.lp.admin.views.admin.users.students.reportCard

import com.narbase.kunafa.core.components.*
import com.narbase.kunafa.core.components.Page.mount
import com.narbase.kunafa.core.components.layout.LinearLayout
import com.narbase.kunafa.core.css.*
import com.narbase.kunafa.core.dimensions.dependent.matchParent
import com.narbase.kunafa.core.dimensions.dimen
import com.narbase.kunafa.core.dimensions.percent
import com.narbase.kunafa.core.dimensions.px
import com.narbase.kunafa.core.lifecycle.LifecycleOwner
import sd.gov.moe.lp.admin.common.AppColors
import sd.gov.moe.lp.admin.common.AppFontSizes
import sd.gov.moe.lp.admin.common.AppImages
import sd.gov.moe.lp.admin.translations.localized
import sd.gov.moe.lp.admin.utils.horizontalFiller
import sd.gov.moe.lp.admin.utils.horizontalSeparator
import sd.gov.moe.lp.admin.utils.table.headerCell
import sd.gov.moe.lp.admin.utils.table.listTable
import sd.gov.moe.lp.admin.utils.table.tableCell
import sd.gov.moe.lp.admin.utils.table.tableRow
import sd.gov.moe.lp.admin.utils.verticalFiller
import sd.gov.moe.lp.admin.utils.views.pointerCursor
import sd.gov.moe.lp.admin.utils.views.popUpDialog
import sd.gov.moe.lp.admin.utils.views.theme.adminTheme
import sd.gov.moe.lp.admin.utils.views.withLoadingAndError
import sd.gov.moe.lp.dto.common.StringUUID
import sd.gov.moe.lp.dto.models.ExtendedStudentReportCardDto
import kotlin.js.Date

class StudentReportCardDialog : Component() {
    val viewModel = StudentReportCardViewModel()
    private var popUp = popUpDialog { }

    //    private var paginationControls: PaginationControls? = null
    private var tableContainer: View? = null
    private var tableBody: View? = null

    enum class ReportTabs(val title: String) {
        Subjects("Enrolled subjects".localized()),
        Groups("Enrolled groups".localized()),
        Paths("Learning paths".localized())
    }

    private val tabsToViews = mutableMapOf<ReportTabs, View>()

    override fun onViewMounted(lifecycleOwner: LifecycleOwner) {
        super.onViewMounted(lifecycleOwner)
        viewModel.getReportCard()
    }

    override fun View?.getView() = view {
        style {
            width = 0.px
            height = 0.px
        }
        view {
            style {
                width = 0.px
                height = 0.px
            }
            withLoadingAndError(
                uiState = viewModel.uiState,
                onLoaded = { cardDialog() },
                onRetryClicked = { viewModel.getReportCard() }
            )
        }
    }

    private fun cardDialog() {
        val reportCard = viewModel.reportCard ?: return
        adminTheme.showDialog(
            popUp,
            title = ""
        )
        { reportCard(reportCard) }
    }

    private fun LinearLayout.reportCard(reportCard: ExtendedStudentReportCardDto) {
        verticalLayout {
            style {
                width = matchParent
            }
            horizontalLayout {
                style {
                    width = matchParent
                    alignItems = Alignment.Center
                }
                imageView {
                    style {
                        width = 48.px
                        height = 48.px
                        borderRadius = 50.percent
                        border = adminTheme.standardBorder
                    }
                    element.src = AppImages.USER_ICON
                }
                horizontalFiller(adminTheme.standardSpacing)
                verticalLayout {
                    textView {
                        text = reportCard.studentProfile.student.fullName
                    }
                    verticalFiller(adminTheme.standardSpacing)
                    textView {
                        text = reportCard.studentProfile.client.userName
                    }
                }
            }
            verticalFiller(adminTheme.wideSpacing)
            horizontalLayout {
                style {
                    width = matchParent
                    alignItems = Alignment.Center
                }
                horizontalLayout {
                    id = "assessmentsAverage"
                    style {
                        backgroundColor = AppColors.extraLightBackground
                        padding = 16.px
                        border = adminTheme.standardBorder
                        borderRadius = adminTheme.standardRadius
                    }
                    imageView {
                        style {
                            width = 48.px
                            height = 48.px
                            borderRadius = 50.percent
                            border = adminTheme.standardBorder
                        }
                        element.src = AppImages.USER_ICON
                    }
                    horizontalFiller(adminTheme.standardSpacing)
                    verticalLayout {
                        textView {
                            text = reportCard.studentSubjects.map { extendedStudentSubject ->
                                extendedStudentSubject.studentSubjectAssessments.map { it.progress }.average()
                            }.average().toString()
                        }
                        verticalFiller(adminTheme.standardSpacing)
                        textView {
                            text = "Avg assessment score".localized()
                        }
                    }
                }
                horizontalFiller(adminTheme.standardSpacing)
                horizontalLayout {
                    id = "courses"
                    style {
                        backgroundColor = AppColors.extraLightBackground
                        padding = 16.px
                        border = adminTheme.standardBorder
                        borderRadius = adminTheme.standardRadius
                    }
                    imageView {
                        style {
                            width = 48.px
                            height = 48.px
                            borderRadius = 50.percent
                            border = adminTheme.standardBorder
                        }
                        element.src = AppImages.USER_ICON
                    }
                    horizontalFiller(adminTheme.standardSpacing)
                    verticalLayout {
                        textView {
                            text = reportCard.studentSubjects.filter { extendedStudentSubject ->
                                extendedStudentSubject.studentSubject.progress > 100.0
                            }.size.toString()
                        }
                        verticalFiller(adminTheme.standardSpacing)
                        textView {
                            text = "Completed subjects".localized()
                        }
                    }
                    horizontalFiller(adminTheme.narrowSpacing)
                    horizontalSeparator()
                    horizontalFiller(adminTheme.narrowSpacing)
                    verticalLayout {
                        textView {
                            text = reportCard.studentSubjects.size.toString()
                        }
                        verticalFiller(adminTheme.standardSpacing)
                        textView {
                            text = "Registered subjects".localized()
                        }
                    }
                }
                horizontalFiller(adminTheme.standardSpacing)
                horizontalLayout {
                    id = "groups"
                    style {
                        backgroundColor = AppColors.extraLightBackground
                        padding = 16.px
                        border = adminTheme.standardBorder
                        borderRadius = adminTheme.standardRadius
                    }
                    imageView {
                        style {
                            width = 48.px
                            height = 48.px
                            borderRadius = 50.percent
                            border = adminTheme.standardBorder
                        }
                        element.src = AppImages.USER_ICON
                    }
                    horizontalFiller(adminTheme.standardSpacing)
                    verticalLayout {
                        textView {
                            text = reportCard.studentGroups.size.toString()
                        }
                        verticalFiller(adminTheme.standardSpacing)
                        textView {
                            text = "Enrolled groups".localized()
                        }
                    }
                }
                horizontalFiller(adminTheme.standardSpacing)
                horizontalLayout {
                    id = "learningPaths"
                    style {
                        backgroundColor = AppColors.extraLightBackground
                        padding = 16.px
                        border = adminTheme.standardBorder
                        borderRadius = adminTheme.standardRadius
                    }
                    imageView {
                        style {
                            width = 48.px
                            height = 48.px
                            borderRadius = 50.percent
                            border = adminTheme.standardBorder
                        }
                        element.src = AppImages.USER_ICON
                    }
                    horizontalFiller(adminTheme.standardSpacing)
                    verticalLayout {
                        textView {
                            text = reportCard.studentLearningPaths.size.toString()
                        }
                        verticalFiller(adminTheme.standardSpacing)
                        textView {
                            text = "Enrolled learning paths".localized()
                        }
                    }
                }

            }
            verticalFiller(adminTheme.wideSpacing)
            tabsView()
            tableContainer = view {
                tableBody = view {

                }
            }

        }
    }

    private fun LinearLayout.tabsView() = horizontalLayout {
        style {
            borderRadius = adminTheme.standardRadius
            padding = 12.px
        }
        ReportTabs.entries.forEach {
            tabItem(it).let { view ->
                tabsToViews[it] = view
            }
        }

        setSelectedTab(ReportTabs.Subjects)
    }

    private fun LinearLayout.setSelectedTab(selectedTab: ReportTabs) {
        tabsToViews.forEach {
            if (it.key != selectedTab) {
                it.value.removeRuleSet(selectedTabRuleSet)
            }
        }
        tabsToViews[selectedTab]?.addRuleSet(selectedTabRuleSet)
        tableContainer?.clearAllChildren()
        tableBody?.apply {
            clearAllChildren()
            when (selectedTab) {
                // fixme: use list and total in the extended dto and paginate the tables
                ReportTabs.Subjects -> subjectsTable()
                ReportTabs.Groups -> groupsTable()
                ReportTabs.Paths -> pathsTable()
            }
        }

    }

    private fun LinearLayout.tabItem(tab: ReportTabs) = linearLayout {
        style {
            padding = "4px 12px".dimen()
            pointerCursor()
        }
        textView {
            style {
                fontSize = AppFontSizes.smallText
                color = AppColors.textDarkGrey
            }
            text = "${tab.title} (${viewModel.reportCard?.studentSubjects?.size ?: "-"})"
        }
        onClick = {
            setSelectedTab(tab)
        }
    }

    private fun LinearLayout.subjectsTable() {
        listTable {
            headerCell("Subject name".localized(), 1)
            headerCell("% Subject progress".localized(), 1)
            headerCell("Completed assessments".localized(), 1)
            headerCell("% Average assessments score".localized(), 1)
            headerCell("Enrolled on".localized(), 1)
            headerCell("Completed on".localized(), 1)
            viewModel.reportCard?.studentSubjects?.forEach { item ->
                tableRow {
                    id = item.studentSubject.id
                    tableCell(item.subject.name, 1)
                    tableCell(item.studentSubject.progress.toString(), 1)
                    tableCell(item.studentSubjectAssessments.filter { it.progress >= 100.0 }.size.toString(), 1)
                    tableCell(item.studentSubjectAssessments.map { it.progress }.average().toString(), 1)
                    tableCell(Date(item.studentSubject.enrolledOn.milliSeconds).toDateString(), 1)
                    tableCell(item.studentSubject.completedOn?.let { Date(it.milliSeconds).toDateString() }
                        ?: "Not completed", 1)
                }
            }
        }

    }

    private fun LinearLayout.groupsTable() {
        listTable {
            headerCell("Group name".localized(), 1)
            viewModel.reportCard?.studentGroups?.forEach { item ->
                tableRow {
                    id = item.name
                    tableCell(item.name, 1)
                }
            }
        }

    }

    private fun LinearLayout.pathsTable() {
        listTable {
            headerCell("Learning path".localized(), 1)
            viewModel.reportCard?.studentLearningPaths?.forEach { item ->
                tableRow {
                    id = item.name
                    tableCell(item.name, 1)
                }
            }
        }
    }

    val selectedTabRuleSet by lazy {
        classRuleSet {
            backgroundColor = AppColors.extraLightBackground

        }
    }

    fun show(id: StringUUID) {
        viewModel.setStudentId(id)
        mount(this@StudentReportCardDialog)
    }
}
