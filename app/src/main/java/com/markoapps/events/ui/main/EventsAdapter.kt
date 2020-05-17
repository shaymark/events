package com.markoapps.events.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.markoapps.events.R
import com.markoapps.events.core.EventData
import kotlinx.android.synthetic.main.event_list_item.view.*

class EventsAdapter(): RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    val eventList: List<EventData> = listOf<EventData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.event_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return eventList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = eventList[position]
        holder.itemView.event_id.text = event.eventId
        holder.itemView.description.text = event.eventDescription
        holder.itemView.status.text = event.evnetStatus.name
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}