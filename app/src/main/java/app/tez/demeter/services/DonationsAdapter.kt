package app.tez.demeter.services

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import app.tez.demeter.R
import app.tez.demeter.models.DonationItem
import kotlinx.android.synthetic.main.donation_item.view.*

/**
 * Created by Terence Zafindratafa on 17/09/2018
 */
class DonationsAdapter(private val itemList: List<DonationItem>): RecyclerView.Adapter<DonationsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): DonationsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.donation_item, parent, false)

        return DonationsViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(viewHolder: DonationsViewHolder, position: Int) {
        viewHolder.updateView(itemList[position])
    }
}