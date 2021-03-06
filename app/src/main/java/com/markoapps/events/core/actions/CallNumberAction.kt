package com.markoapps.events.core.actions

import android.os.Handler
import android.os.Looper
import com.markoapps.events.core.Action
import com.markoapps.events.utils.CallsManager


class CallNumberAction(val numberToCall: String,
                       val hangoutAfterSeconds: Int?)
    : Action() {


    override fun startAction() {
        callPhone(numberToCall)
    }

    private val handler: Handler = Handler(Looper.getMainLooper())

    private fun callPhone(numberToCall: String) {
        CallsManager.callPhone(event!!.context!!, event!!.properties["smsNumber"] as String)
        if(hangoutAfterSeconds != null) {
           hangOutPhoneCall(numberToCall, hangoutAfterSeconds)
        }
    }

    private fun hangOutPhoneCall(numberToHangout: String, afterSecons: Int) {
        handler.postDelayed({ CallsManager.getCurrentCall()?.let {
            if(it.contains(numberToHangout)) {
                CallsManager.hangOutCall()
            }
        }}, afterSecons * 1000L)
    }

}