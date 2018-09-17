package app.tez.demeter.services.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import app.tez.demeter.R
import app.tez.demeter.models.Recipient

/**
 * Created by Terence Zafindratafa on 17/09/2018
 */
class SimpleServicesAdapter(private val recipientList: MutableList<Recipient>): RecyclerView.Adapter<SimpleServicesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): SimpleServicesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.simple_services_item, parent, false)

        return SimpleServicesViewHolder(view)
    }

    override fun getItemCount(): Int = recipientList.size

    override fun onBindViewHolder(viewHolder: SimpleServicesViewHolder, position: Int) {
        viewHolder.updateItem(recipientList[position])
        viewHolder.removeBtn.setOnClickListener {
            if(!recipientList.isEmpty()){
                recipientList.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, recipientList.size)
            }
        }
    }
}