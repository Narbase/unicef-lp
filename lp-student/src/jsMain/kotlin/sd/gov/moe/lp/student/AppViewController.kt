package sd.gov.moe.lp.student

import com.narbase.kunafa.core.lifecycle.Observable
import sd.gov.moe.lp.student.login.LoginPageNavigator
import sd.gov.moe.lp.student.storage.StorageManager
import sd.gov.moe.lp.student.views.basePage.HomePageNavigator


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
