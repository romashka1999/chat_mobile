package com.example.chat_mobile.ui.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chat_mobile.R
import com.example.chat_mobile.payload.Group

class GroupListAdapter(private val groupList: List<Group>): RecyclerView.Adapter<GroupViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_group, null)
        return  GroupViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroupViewHolder, position: Int) {
        holder.setContent(groupList[position])
    }

    override fun getItemCount(): Int = groupList.size;
}

class  GroupViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val groupTitle = itemView.findViewById<TextView>(R.id.groupTitle)
    private val groupAdminName = itemView.findViewById<TextView>(R.id.groupAdminName)

    fun setContent(group: Group) {
        with(group) {
            groupTitle.text = title
            groupAdminName.text = adminName
        }
    }
}