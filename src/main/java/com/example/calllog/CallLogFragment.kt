package com.example.calllog

import android.database.Cursor
import android.os.Bundle
import android.provider.CallLog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CallLogFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var callLogAdapter: CallLogAdapter
    private val callList = mutableListOf<CallLogItem>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_call_log, container, false)
        recyclerView = view.findViewById(R.id.callLogRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        callLogAdapter = CallLogAdapter(callList)
        recyclerView.adapter = callLogAdapter

        loadCallLogs()

        return view
    }

    private fun loadCallLogs() {
        val cursor: Cursor? = context?.contentResolver?.query(
            CallLog.Calls.CONTENT_URI,
            null, null, null, CallLog.Calls.DATE + " DESC"
        )

        cursor?.use {
            val numberIndex = it.getColumnIndex(CallLog.Calls.NUMBER)
            val typeIndex = it.getColumnIndex(CallLog.Calls.TYPE)

            while (it.moveToNext()) {
                val number = it.getString(numberIndex)
                val type = it.getInt(typeIndex)
                val callType = when (type) {
                    CallLog.Calls.OUTGOING_TYPE -> "Outgoing"
                    CallLog.Calls.INCOMING_TYPE -> "Incoming"
                    CallLog.Calls.MISSED_TYPE -> "Missed"
                    else -> "Other"
                }
                callList.add(CallLogItem(number, callType))
            }
            callLogAdapter.notifyDataSetChanged()
        }
    }
}
