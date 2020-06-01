package com.markoapps.events.core

import androidx.lifecycle.MutableLiveData

object EventManger {

    val eventDataListLiveData: MutableLiveData<List<EventData>> = MutableLiveData()
    val eventListLiveData: MutableLiveData<List<Event>> = MutableLiveData()


    val eventList: MutableMap<String, Event> = mutableMapOf()

    fun activateAllEvents() {
        for (event in eventList.values) {
            event.startEvent()
        }

        eventListLiveData.value = eventList.values.toList()
        eventDataListLiveData.value = eventList.map { eventToEventData(it.value) }
    }

    fun disableAllEvents() {
        for (event in eventList.values) {
            event.stopEvent()
        }
        eventListLiveData.value = eventList.values.toList()
        eventDataListLiveData.value = eventList.map { eventToEventData(it.value) }
    }

    fun addEvent(event: Event) {
        eventList[event.eventId] = event
        eventListLiveData.value = eventList.values.toList()
        eventDataListLiveData.value = eventList.map { eventToEventData(it.value) }
    }

    fun removeEvent(eventid: String) {
        eventList.remove(eventid)
        eventListLiveData.value = eventList.values.toList()
        eventDataListLiveData.value = eventList.map { eventToEventData(it.value) }
    }

    fun startEvent(eventid: String) {
        eventList[eventid]?.startEvent()
        eventListLiveData.value = eventList.values.toList()
        eventDataListLiveData.value = eventList.map { eventToEventData(it.value) }
    }

    fun stopEvent(eventid: String) {
        eventList[eventid]?.stopEvent()
        eventListLiveData.value = eventList.values.toList()
        eventDataListLiveData.value = eventList.map { eventToEventData(it.value) }
    }

}