package com.markoapps.events.core

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import com.markoapps.events.CustomApplication
import com.markoapps.events.utils.SmsManager
import com.markoapps.events.utils.smsContent
import java.util.*
import kotlin.collections.ArrayList

class Event(val triger: Triger, val action: Action, val eventId: String = UUID.randomUUID().toString()) {
    val properties = mutableMapOf<String, Any>()
    val log: List<String> = ArrayList()
    var context: Context? = CustomApplication.context
    var status: EventStatus = EventStatus.notActive


    val toString = "if ${triger.toString()} then ${action.toString()}"

    init {
        triger.event = this
        action.event = this
    }

    fun startEvent() {
        status = EventStatus.activeTriger
        triger.registerTrigger()
    }

    fun stopEvent() {
        status = EventStatus.canceled
        triger.unRegisterTrigger()
    }

    fun trigerEvent(){
        status = EventStatus.activeAction
        action.startAction()
    }

}

enum class EventStatus {
    notActive,
    activeTriger,
    activeAction,
    finished,
    canceled,
}

data class EventData(val eventId: String, val evnetStatus: EventStatus, val eventDescription: String)

fun eventToEventData(event: Event): EventData {
    return EventData(eventId = event.eventId, evnetStatus = event.status, eventDescription = event.toString)
}

abstract class Triger() {
    var event: Event? = null

    abstract fun registerTrigger()
    abstract fun unRegisterTrigger()
    abstract override fun toString(): String
}

abstract class Action() {
    var event: Event? = null

    abstract fun startAction()
}

