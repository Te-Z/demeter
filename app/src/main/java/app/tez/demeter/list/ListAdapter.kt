package app.tez.demeter.list

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import app.tez.demeter.R
import app.tez.demeter.models.Recipient
import com.bumptech.glide.RequestManager

/**
 * Created by Terence Zafindratafa on 11/09/2018
 */

class ListAdapter(private val recipientList: List<Recipient>, private val glide: RequestManager): RecyclerView.Adapter<ListViewHolder>(){

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ListViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.fragment_list_item, parent, false)

        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = recipientList.size

    override fun onBindViewHolder(viewHolder: ListViewHolder, position: Int) {
        viewHolder.updateItem(recipientList[position], glide, context)
    }
}