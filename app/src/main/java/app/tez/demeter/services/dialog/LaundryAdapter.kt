package app.tez.demeter.services.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.tez.demeter.R
import app.tez.demeter.models.DonationItem

class LaundryAdapter(private val itemList: MutableList<DonationItem>): RecyclerView.Adapter<LaundryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaundryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.laundry_item_rv, parent, false)

        return LaundryViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: LaundryViewHolder, position: Int) {
        holder.updateItem(itemList[position])
        holder.removeBtn.setOnClickListener {
            if(!itemList.isEmpty()){
                itemList.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, itemList.size)
            }
        }
    }

}