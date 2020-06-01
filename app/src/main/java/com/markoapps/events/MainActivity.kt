package com.markoapps.events

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.markoapps.events.ui.main.EventsAdapter
import com.markoapps.events.ui.main.MainFragment
import com.markoapps.events.utils.CallsManager
import com.markoapps.events.utils.SmsManager
import kotlinx.android.synthetic.main.main_fragment.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        checkForReadSmsPermissions()
        checkForCallPermissions()

        // CallsManager.callPhone(this.applicationContext, "+972545352473")


    }

    fun checkForReadSmsPermissions() {
        checkForPermissions(Manifest.permission.RECEIVE_SMS, 124)
    }

    fun checkForCallPermissions() {
        checkForPermissions(Manifest.permission.CALL_PHONE, 123)
    }

    fun checkForPermissions(permissionName: String, requestNumber: Int){
        if (ActivityCompat.checkSelfPermission(
                this,
                permissionName
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.d(SmsManager.TAG, this.getString(R.string.sms_permission_not_granted));
            // Permission not yet granted. Use requestPermissions().
            // MY_PERMISSIONS_REQUEST_SEND_SMS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
            ActivityCompat.requestPermissions(
                this,
                arrayOf(permissionName),
                requestNumber
            )
        }
    }

}
