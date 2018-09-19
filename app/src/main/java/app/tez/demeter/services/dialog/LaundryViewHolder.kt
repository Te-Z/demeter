package app.tez.demeter.services.dialog

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import app.tez.demeter.models.DonationItem
import kotlinx.android.synthetic.main.laundry_item_rv.view.*

class LaundryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val label = itemView.laundry_item_rv_label
    private val nb = itemView.laundry_item_rv_nb
    val removeBtn = itemView.laundry_item_remove

    fun updateItem(item: DonationItem){
        label.text = item.name
        nb.text = item.quantity.toString()
    }
}