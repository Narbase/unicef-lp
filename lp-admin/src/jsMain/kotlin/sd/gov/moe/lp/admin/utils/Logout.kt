package sd.gov.moe.lp.admin.utils

import sd.gov.moe.lp.admin.AppViewController
import sd.gov.moe.lp.admin.storage.StorageManager

fun logoutUser() {
    StorageManager.accessToken = null
    StorageManager.setUserLoggedIn(false)
    AppViewController.loginState.value = false
}
