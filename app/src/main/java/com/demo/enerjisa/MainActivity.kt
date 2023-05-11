package com.demo.enerjisa

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.demo.enerjisa.util.toast
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.ActivityResult
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appUpdateManager: AppUpdateManager
    val REQUEST_CODE = 100
    val TAG = "IAU"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController =
            findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(
            this,
            navController
        )

        checkForUpdates()
    }


    private fun checkForUpdates() {
        appUpdateManager = AppUpdateManagerFactory.create(baseContext)

        appUpdateManager.appUpdateInfo
            .addOnSuccessListener { appUpdateInfo ->
                Log.e(TAG, appUpdateInfo.updateAvailability().toString())

                if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
                ) {
                    toast("Update Disponible")
                    appUpdateManager.startUpdateFlowForResult(
                        appUpdateInfo,
                        AppUpdateType.IMMEDIATE,
                        this,
                        REQUEST_CODE
                    )
                } else {
                    toast("Update NO Disponible")
                }
            }.addOnFailureListener {
                toast(it.message.toString())
                Log.e(TAG, it.message.toString())
            }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    Log.d(TAG, "Result Ok")
                    //  handle user's approval }
                }
                Activity.RESULT_CANCELED -> {
                    run {
                        //if you want to request the update again just call checkUpdate()
                    }
                    Log.d(TAG, "Result Cancelled")
                    //  handle user's rejection  }
                }
                ActivityResult.RESULT_IN_APP_UPDATE_FAILED -> {
                    //if you want to request the update again just call checkUpdate()
                    Log.d(TAG, "Update Failure")
                    //  handle update failure
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun onResume() {
        super.onResume()

        appUpdateManager
            .appUpdateInfo
            .addOnSuccessListener { appUpdateInfo ->

                if (appUpdateInfo.updateAvailability()
                    == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS
                ) {
                    appUpdateManager.startUpdateFlowForResult(
                        appUpdateInfo,
                        AppUpdateType.IMMEDIATE,
                        this,
                        REQUEST_CODE
                    )
                }
            }
    }
}