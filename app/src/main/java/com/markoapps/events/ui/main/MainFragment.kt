package com.markoapps.events.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.markoapps.events.R
import com.markoapps.events.core.Event
import com.markoapps.events.core.EventManger
import com.markoapps.events.core.eventToEventData
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    val adapter = EventsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel

        viewModel.createEvent("545352473", "shay", "089493923", null)


        viewModel.eventList.observe(viewLifecycleOwner, Observer<List<Event>> {
            adapter.eventList = it.map { eventToEventData(it) }
        })

        initUi()

        viewModel.activateEvent()
    }

    fun initUi(){
        eventsList.layoutManager = LinearLayoutManager(context)
        eventsList.adapter = adapter
    }

}
