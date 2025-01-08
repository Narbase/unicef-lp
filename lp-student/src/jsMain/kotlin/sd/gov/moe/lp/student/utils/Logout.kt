package sd.gov.moe.lp.student.utils

import sd.gov.moe.lp.student.AppViewController
import sd.gov.moe.lp.student.storage.StorageManager

fun logoutUser() {
    StorageManager.accessToken = null
    StorageManager.setUserLoggedIn(false)
    AppViewController.loginState.value = false
}
