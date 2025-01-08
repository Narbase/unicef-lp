package sd.gov.moe.lp.admin.views.admin

import sd.gov.moe.lp.admin.translations.localized
import sd.gov.moe.lp.admin.views.basePage.BasePageViewModel

/*
 * Copyright 2017-2020 Narbase technologies and contributors. Use of this source code is governed by the MIT License.
 */
enum class AdminTabs(val routeDetails: BasePageViewModel.RouteDetails) {
    Staff(BasePageViewModel.RouteDetails("/users", "Current Staff".localized())),
    InActiveStaff(BasePageViewModel.RouteDetails("/inactive_staff", "Inactive Staff".localized())),
    Roles(BasePageViewModel.RouteDetails("/roles", "Roles".localized())),

    Students(BasePageViewModel.RouteDetails("/students", "Students".localized())),
    Groups(BasePageViewModel.RouteDetails("/groups", "Groups".localized())),

    ;

    companion object {
        val subLists = listOf(
            AdminTabSubList("Staff Management".localized(), listOf(Staff, InActiveStaff, Roles)),
            AdminTabSubList("Users Management".localized(), listOf(Groups, Students)),
        )
    }
}

class AdminTabSubList(
    val title: String,
    val tabs: List<AdminTabs>
)
