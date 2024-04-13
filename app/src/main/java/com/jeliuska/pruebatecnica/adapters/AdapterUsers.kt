package com.jeliuska.pruebatecnica.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jeliuska.pruebatecnica.R
import com.jeliuska.pruebatecnica.data.remote.response.UsersResponseItem

class AdapterUsers(private val listUsers : ArrayList<UsersResponseItem>,private val listener: OnUserListener) :
    RecyclerView.Adapter<AdapterUsers.MenuViewHolder>() {

    interface OnUserListener {
        fun onMenuClick(usersResponseItem : UsersResponseItem)
    }

    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameUser_item: TextView = itemView.findViewById(R.id.tvName_item)
        private val emailUser_item: TextView = itemView.findViewById(R.id.tvEmail_item)
        private val websiteUser_item: TextView = itemView.findViewById(R.id.tvWebsite_item)
        private val btnViewMore : Button = itemView.findViewById(R.id.btnViewMore)

        fun bind(usersResponseItem: UsersResponseItem, position: Int) {
            nameUser_item.text = usersResponseItem.name
            emailUser_item.text = usersResponseItem.email
            websiteUser_item.text = usersResponseItem.website

            btnViewMore.setOnClickListener {
                listener.onMenuClick(usersResponseItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_menu, parent, false)
        return MenuViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listUsers.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(listUsers[position], position)

    }


}