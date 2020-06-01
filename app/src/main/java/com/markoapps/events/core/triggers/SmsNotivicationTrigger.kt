package com.markoapps.events.core.triggers

import com.markoapps.events.core.Triger
import com.markoapps.events.utils.SmsManager
import com.markoapps.events.utils.smsContent

class SmsNotificationTrigger(val smsNumber: String?,
                             val smsShouldContain:String?)
    : Triger() {

    override
    fun registerTrigger() {
        SmsManager.registerToSmsReciver(event!!.context!!, ::handleEvent)
    }

    override
    fun unRegisterTrigger() {

    }

    fun handleEvent (smsContent: smsContent) {

        event?.properties!!["smsNumber"] = smsContent.sender
        event?.properties!!["smsContnet"] = smsContent.content

        if(smsNumber != null && !smsContent.sender.contains(smsNumber)){
            return
        }

        if(smsShouldContain != null && !smsContent.content.contains(smsContent.content)) {
            return
        }

        event!!.trigerEvent()
    }

    override fun toString() = " you got sms from number ${smsNumber} and contain ${smsShouldContain}"
}