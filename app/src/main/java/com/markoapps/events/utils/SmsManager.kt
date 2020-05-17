package com.markoapps.events.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.provider.Telephony

object SmsManager {

    private val smsFilter =
        IntentFilter().apply { addAction(Telephony.Sms.Intents.SMS_RECEIVED_ACTION) }


    fun registerToSmsReciver(context: Context, smsCallback: (smsContent: smsContent) -> Unit) {
        context.registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                smsCallback(
                    smsContent(
                        intent!!.getStringExtra("smsName"),
                        intent!!.getStringExtra("smsCaller")
                    )
                )
            }
        }, smsFilter)
    }

}

public data class smsContent (val sender: String, val content: String)