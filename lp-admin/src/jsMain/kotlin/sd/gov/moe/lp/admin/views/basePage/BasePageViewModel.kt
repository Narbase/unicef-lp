package sd.gov.moe.lp.admin.views.basePage

import sd.gov.moe.lp.admin.utils.notifications.NotificationsController


class BasePageViewModel {

    fun onViewCreated() {
        NotificationsController.connect()
    }

    data class RouteDetails(var href: String, val title: String, val image: String? = null)

}
