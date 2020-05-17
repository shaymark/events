package com.markoapps.events.core.builders

import com.markoapps.events.core.Event
import com.markoapps.events.core.actions.CallNumberAction
import com.markoapps.events.core.triggers.SmsNotificationTrigger

fun createDefaultSmsEvent(sender: String?, containText: String?, numberToCall: String, handAfter: Int?): Event {
    return Event(
        triger = SmsNotificationTrigger(sender, containText),
        action = CallNumberAction(numberToCall = numberToCall, hangoutAfterSeconds = handAfter)
    )
}