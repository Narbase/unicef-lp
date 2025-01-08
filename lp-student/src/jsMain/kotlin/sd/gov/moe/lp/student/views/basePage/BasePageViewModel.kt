package sd.gov.moe.lp.student.views.basePage

import sd.gov.moe.lp.student.utils.notifications.NotificationsController


class BasePageViewModel {

    fun onViewCreated() {
        NotificationsController.connect()
    }

    data class RouteDetails(var href: String, val title: String, val image: String? = null)

}
