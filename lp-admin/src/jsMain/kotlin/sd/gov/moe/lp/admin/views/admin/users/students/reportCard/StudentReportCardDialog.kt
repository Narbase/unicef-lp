package sd.gov.moe.lp.admin.views.admin.users.students.reportCard

import com.narbase.kunafa.core.components.*
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
import sd.gov.moe.lp.admin.utils.BasicUiState
import sd.gov.moe.lp.admin.utils.horizontalFiller
import sd.gov.moe.lp.admin.utils.horizontalSeparator
import sd.gov.moe.lp.admin.utils.verticalFiller
import sd.gov.moe.lp.admin.utils.views.pointerCursor
import sd.gov.moe.lp.admin.utils.views.popUpDialog
import sd.gov.moe.lp.admin.utils.views.theme.adminTheme
import sd.gov.moe.lp.admin.utils.views.withLoadingAndError
import sd.gov.moe.lp.admin.views.admin.content.learningPaths.LearningPathsManagementViewModel
import sd.gov.moe.lp.admin.views.admin.users.groups.GroupsManagementViewModel
import sd.gov.moe.lp.admin.views.admin.users.students.reportCard.tables.GroupsTableComponent
import sd.gov.moe.lp.admin.views.admin.users.students.reportCard.tables.LearningPathsTableComponent
import sd.gov.moe.lp.admin.views.admin.users.students.reportCard.tables.StudentSubjectsTableComponent
import sd.gov.moe.lp.admin.views.admin.users.students.reportCard.tables.StudentSubjectsTableViewModel
import sd.gov.moe.lp.dto.common.StringUUID
import sd.gov.moe.lp.dto.models.ExtendedStudentReportCardDto

class StudentReportCardDialog(val viewModel: StudentReportCardViewModel) : Component() {
    //    val viewModel = StudentReportCardViewModel()
    private var popUp = popUpDialog { }

    //    private var paginationControls: PaginationControls? = null
    private var tableBody: View? = null
    private val learningPathsManagementViewModel = LearningPathsManagementViewModel()
    private val groupsManagementViewModel = GroupsManagementViewModel()
    private val studentSubjectsTableViewModel = StudentSubjectsTableViewModel()

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
                uiState = viewModel.getReportUiState,
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
                                extendedStudentSubject.studentSubject.progress >= 100.0
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
                            text = reportCard.studentGroupsCount.toString()
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
                            text = reportCard.studentLearningPathsCount.toString()
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
            tableBody = view {
                style {
                    width = matchParent
                }
            }
            setSelectedTab(ReportTabs.Subjects)

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
    }

    private fun setSelectedTab(selectedTab: ReportTabs) {
        tabsToViews.forEach {
            if (it.key != selectedTab) {
                it.value.removeRuleSet(selectedTabRuleSet)
            }
        }
        tabsToViews[selectedTab]?.addRuleSet(selectedTabRuleSet)
        tableBody?.apply {
            clearAllChildren()
            when (selectedTab) {
                // fixme: use list and total and get each tab in a separate controller and paginate the tables and change the report dto to use counts
                ReportTabs.Subjects -> {
                    mount(StudentSubjectsTableComponent(studentSubjectsTableViewModel))
                }

                ReportTabs.Groups -> {
                    mount(GroupsTableComponent(groupsManagementViewModel))
                }

                ReportTabs.Paths -> mount(LearningPathsTableComponent(learningPathsManagementViewModel))
            }
        }

    }

    private fun LinearLayout.tabItem(tab: ReportTabs) = horizontalLayout {
        val count = when (tab) {
            ReportTabs.Subjects -> viewModel.reportCard?.studentSubjects?.size
            ReportTabs.Groups -> viewModel.reportCard?.studentGroupsCount
            ReportTabs.Paths -> viewModel.reportCard?.studentLearningPathsCount
        }
        style {
            padding = "4px 12px".dimen()
            pointerCursor()
        }
        textView {
            style {
                fontSize = AppFontSizes.smallText
                color = AppColors.textDarkGrey
            }
            text = "${tab.title} (${count ?: "-"})"
        }
        onClick = {
            setSelectedTab(tab)
        }
    }

    val selectedTabRuleSet by lazy {
        classRuleSet {
            backgroundColor = AppColors.extraLightBackground

        }
    }

    fun show(id: StringUUID) {
        viewModel.setStudentId(id)
        viewModel.getReportUiState.clearObservers()
        viewModel.getReportCard()
        learningPathsManagementViewModel.filters.studentId = id
        groupsManagementViewModel.filters.studentId = id
        studentSubjectsTableViewModel.setStudentId(id)
        viewModel.getReportUiState.observe {
            if (it == BasicUiState.Loaded)
                cardDialog()
        }
    }
}
