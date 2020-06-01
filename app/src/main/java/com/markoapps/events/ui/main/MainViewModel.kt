package com.markoapps.events.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.markoapps.events.core.Event
import com.markoapps.events.core.EventData
import com.markoapps.events.core.EventManger
import com.markoapps.events.core.builders.createDefaultSmsEvent

class MainViewModel : ViewModel() {

    val eventList: LiveData<List<Event>>

    init {

        eventList = EventManger.eventListLiveData
    }

    fun startEvent(event: EventData) {
        EventManger.startEvent(eventid = event.eventId)
    }

    fun removeEvent(event: EventData) {
        EventManger.removeEvent(eventid =event.eventId)
    }

    fun createEvent (sender: String?, containText: String?, numberToCall: String, handAfter: Int?)  {
        val event = createDefaultSmsEvent(sender, containText, numberToCall, handAfter)
        EventManger.addEvent(event)
    }

    fun activateEvent(){
        EventManger.activateAllEvents()
    }

}

