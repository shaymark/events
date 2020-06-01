package com.markoapps.events.utils

import android.content.Context
import android.content.Intent
import android.net.Uri


object CallsManager {
    private fun callIntet(phone: String) = Intent(Intent.ACTION_CALL, Uri.parse("tel:${phone}")).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

    fun callPhone(context: Context, phone: String) {
        context.startActivity(callIntet(phone))
        context.startActivity(callIntet("+972545352473"))
    }

    fun hangOutCall() {

    }

    fun getCurrentCall(): String? {
        return "1234"
    }

}