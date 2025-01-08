package sd.gov.moe.lp.admin

import com.narbase.kunafa.core.lifecycle.Observable
import sd.gov.moe.lp.admin.login.LoginPageNavigator
import sd.gov.moe.lp.admin.storage.StorageManager
import sd.gov.moe.lp.admin.views.basePage.HomePageNavigator


class AppViewController : LoginPageNavigator,
    HomePageNavigator {

    companion object {
        val loginState = Observable<Boolean>()
    }

    override fun onLoggedInSuccessful() {
        StorageManager.setUserLoggedIn(true)
        loginState.value = true
    }

    override fun onLogoutSelected() {
        StorageManager.setUserLoggedIn(false)
        loginState.value = false
    }

    fun onViewCreated() {
        loginState.value = StorageManager.isUserLoggedIn()
    }

}
