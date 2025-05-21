package com.example.calllog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class CallLogItem(val phoneNumber: String, val callType: String)

class CallLogAdapter(private val callList: List<CallLogItem>) : RecyclerView.Adapter<CallLogAdapter.CallLogViewHolder>() {

    class CallLogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val phoneNumber: TextView = itemView.findViewById(R.id.tvPhoneNumber)
        val callType: TextView = itemView.findViewById(R.id.tvCallType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallLogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_call_log, parent, false)
        return CallLogViewHolder(view)
    }

    override fun onBindViewHolder(holder: CallLogViewHolder, position: Int) {
        val call = callList[position]
        holder.phoneNumber.text = call.phoneNumber
        holder.callType.text = call.callType
    }

    override fun getItemCount(): Int = callList.size
}
