package com.devicente.convidados.view.viewholder

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devicente.convidados.R
import com.devicente.convidados.service.model.GuestModel
import com.devicente.convidados.view.listener.GuestListener

class GuestViewHolder(itemView: View, private val listener: GuestListener) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(guest: GuestModel) {
        val textName = itemView.findViewById<TextView>(R.id.text_name)
        textName.text = guest.name

        textName.setOnClickListener {
            listener.onClick(guest.id)
        }

        textName.setOnLongClickListener {

            AlertDialog.Builder(itemView.context).setTitle("Remocao de convidado")
                .setMessage("DESEJA REMOVER ?")
                .setPositiveButton("REMOVER") { dialog, whic ->
                    listener.onDelete(guest.id)
                }.setNeutralButton("CANCELAR", null)
                .show()
            true
        }

    }


}