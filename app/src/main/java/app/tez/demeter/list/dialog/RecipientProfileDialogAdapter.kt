package app.tez.demeter.list.dialog

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.tez.demeter.R
import app.tez.demeter.models.ActionItem
import app.tez.demeter.utils.Utils

/**
 * Created by Terence Zafindratafa on 29/09/2018
 */

private const val TAG = "RPDAdapter"
class RecipientProfileDialogAdapter(private val actionItemList: List<ActionItem>): RecyclerView.Adapter<RecipientProfileDialogViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipientProfileDialogViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.fragment_informations_item, parent, false)

        return RecipientProfileDialogViewHolder(view)
    }

    override fun getItemCount(): Int = actionItemList.size

    override fun onBindViewHolder(holder: RecipientProfileDialogViewHolder, position: Int) {
        val actionItem = actionItemList[position]
        Log.d(TAG, "onBindViewHolder: ${actionItem.action} on $position")
        holder.updateItem(actionItem)
        if (actionItem.comment != null){
            holder.toggleDetailsButton.visibility = View.VISIBLE
            holder.toggleDetailsButton.setOnClickListener { Utils.toggleDetails(holder.detailsLayout, holder.toggleDetailsButton) }
        }
    }
}