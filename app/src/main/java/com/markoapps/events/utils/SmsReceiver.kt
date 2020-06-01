package com.markoapps.events.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class SmsReceiver: BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("sms", "onRecive")
        CallsManager.callPhone(context!!, "+972545352473")
    }

}