package com.markoapps.events.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.provider.Telephony
import android.telephony.SmsMessage
import android.util.Log


object SmsManager {

    const val TAG = "SmsManager"

    private val smsFilter =
        IntentFilter().apply { addAction(Telephony.Sms.Intents.SMS_RECEIVED_ACTION) }


    fun registerToSmsReciver(context: Context, smsCallback: (smsContent: smsContent) -> Unit) {
        context.applicationContext.registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val bundle = intent!!.extras
                val pdus = bundle?.get("pdus") as Array<Any>
                val messages: Array<SmsMessage?> = arrayOfNulls<SmsMessage>(pdus.size)
                for (i in pdus.indices) {
                    messages[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray)
                }

                val from = messages[0]?.originatingAddress ?: ""
                val body = messages[0]?.messageBody ?: ""

                smsCallback(
                    smsContent(
                        from,
                        body
                    )
                )
            }
        }, smsFilter)
    }
}

data class smsContent (val sender: String, val content: String)