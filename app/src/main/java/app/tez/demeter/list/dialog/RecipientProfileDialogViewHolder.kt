package app.tez.demeter.list.dialog

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import app.tez.demeter.models.ActionItem
import kotlinx.android.synthetic.main.fragment_informations_item.view.*

/**
 * Created by Terence Zafindratafa on 29/09/2018
 */
class RecipientProfileDialogViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val typeTextView = itemView.fragment_info_item_type
    private val actionTextView = itemView.fragment_info_item_action
    private val dateTextView = itemView.fragment_info_item_date
    private val educatorTextView = itemView.fragment_info_item_educator
    private val commentTextView = itemView.fragment_info_item_details_text
    val toggleDetailsButton = itemView.fragment_info_item_details_button
    val detailsLayout = itemView.fragment_info_item_details_layout

    fun updateItem(actionItem: ActionItem){
        typeTextView.text = actionItem.type
        actionTextView.text = actionItem.action
        dateTextView.text = actionItem.date
        educatorTextView.text = actionItem.educatorName
        commentTextView.text = actionItem.comment
    }
}