package app.tez.demeter.services.recyclerview

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import app.tez.demeter.models.Recipient
import kotlinx.android.synthetic.main.simple_services_item.view.*

/**
 * Created by Terence Zafindratafa on 17/09/2018
 */
class SimpleServicesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val username = itemView.simple_services_item_name
    val removeBtn = itemView.simple_services_item_remove_btn

    fun updateItem(recipient: Recipient){
        val name = "${recipient.firstName} ${recipient.lastName.toUpperCase()}"
        username.text = name
    }
}