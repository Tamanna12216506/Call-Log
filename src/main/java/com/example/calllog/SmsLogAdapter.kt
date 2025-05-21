package com.example.calllog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class SmsLogItem(val sender: String, val body: String)

class SmsLogAdapter(private val smsList: List<SmsLogItem>) : RecyclerView.Adapter<SmsLogAdapter.SmsLogViewHolder>() {

    class SmsLogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sender: TextView = itemView.findViewById(R.id.tvSmsSender)
        val body: TextView = itemView.findViewById(R.id.tvSmsBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmsLogViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sms_log, parent, false)
        return SmsLogViewHolder(view)
    }

    override fun onBindViewHolder(holder: SmsLogViewHolder, position: Int) {
        val sms = smsList[position]
        holder.sender.text = sms.sender
        holder.body.text = sms.body
    }

    override fun getItemCount(): Int = smsList.size
}
