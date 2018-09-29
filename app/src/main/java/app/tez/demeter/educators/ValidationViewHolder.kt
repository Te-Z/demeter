package app.tez.demeter.educators

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import app.tez.demeter.models.Educator
import kotlinx.android.synthetic.main.activity_validation_item.view.*

/**
 * Created by Terence Zafindratafa on 29/09/2018
 */
class ValidationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val usernameTextView = itemView.activity_validation_item_username
    val validationButton = itemView.activity_validation_item_button
    val deleteButton = itemView.activity_validation_item_clear

    fun updateItem(educator: Educator){
        usernameTextView.text = educator.username
    }
}