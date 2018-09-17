package app.tez.demeter.services.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.View
import app.tez.demeter.models.DonationItem
import kotlinx.android.synthetic.main.donation_item.view.*

/**
 * Created by Terence Zafindratafa on 17/09/2018
 */
class DonationsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val labelView = itemView.donation_item_label
    private val stockView = itemView.donation_item_stock_value

    fun updateView(item: DonationItem){
        labelView.text = item.name
        stockView.text = item.quantity.toString()
    }
}