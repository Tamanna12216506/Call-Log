package com.example.calllog

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.Telephony
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SmsLogFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var smsAdapter: SmsLogAdapter
    private val smsList = mutableListOf<SmsLogItem>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sms_log, container, false)
        recyclerView = view.findViewById(R.id.smsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        smsAdapter = SmsLogAdapter(smsList)
        recyclerView.adapter = smsAdapter

        loadSmsLogs()

        return view
    }

    private fun loadSmsLogs() {
        val uri: Uri = Telephony.Sms.CONTENT_URI
        val cursor: Cursor? = context?.contentResolver?.query(
            uri, null, null, null, Telephony.Sms.DEFAULT_SORT_ORDER
        )

        cursor?.use {
            val bodyIndex = it.getColumnIndex(Telephony.Sms.BODY)
            val addressIndex = it.getColumnIndex(Telephony.Sms.ADDRESS)

            while (it.moveToNext()) {
                val body = it.getString(bodyIndex)
                val address = it.getString(addressIndex)
                smsList.add(SmsLogItem(address, body))
            }
            smsAdapter.notifyDataSetChanged()
        }
    }
}
