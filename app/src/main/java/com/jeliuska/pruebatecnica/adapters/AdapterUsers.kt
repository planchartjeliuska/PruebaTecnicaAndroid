package com.jeliuska.pruebatecnica.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jeliuska.pruebatecnica.R

class AdapterUsers(private val listener: OnMainMenuListener) :
    RecyclerView.Adapter<AdapterUsers.MenuViewHolder>() {

    interface OnMainMenuListener {
        fun onMenuClick()
    }

    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameUser_item: TextView = itemView.findViewById(R.id.tvName_item)
        private val emailUser_item: TextView = itemView.findViewById(R.id.tvEmail_item)
        private val websiteUser_item: TextView = itemView.findViewById(R.id.tvWebsite_item)
        private val btnViewMore : Button = itemView.findViewById(R.id.btnViewMore)

        fun bind() {
            //itemText.text = incidentType.nombre

            btnViewMore.setOnClickListener {
                listener.onMenuClick()
            }
            /*itemView.setOnClickListener {
                listener.onMenuClick(incidentType)
            }*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_menu, parent, false)
        return MenuViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        //return listMenu.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        //holder.bind(listMenu[position], position)

    }
}

