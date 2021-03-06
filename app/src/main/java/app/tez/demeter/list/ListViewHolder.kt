package app.tez.demeter.list

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import app.tez.demeter.R
import app.tez.demeter.models.Recipient
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_list_item.view.*

/**
 * Created by Terence Zafindratafa on 11/09/2018
 */
class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val mood = itemView.fragment_list_item_mood
    private val picture = itemView.fragment_list_item_pic
    private val username = itemView.fragment_list_item_name
    private val hourOfArrival = itemView.fragment_list_item_hour
    val layout: ConstraintLayout = itemView.fragment_list_item_left

    fun updateItem(recipient: Recipient, glide: RequestManager, context: Context){

        val color = when(recipient.mood){
                in 0..24 ->  R.drawable.bad_mood
                in 25..49 -> R.drawable.quarter_mood
                in 50..74 -> R.drawable.quarter_sec_mood
                in 57..100 -> R.drawable.quarter_sec_mood
                else -> throw Exception("This mood is not between 0 and 100: ${recipient.mood}")
            }

        glide.load(ContextCompat.getDrawable(context, color)).apply(RequestOptions().circleCrop()).into(mood)

        if(recipient.avatar != null){
            glide.load(recipient.avatar).apply(RequestOptions().circleCrop()).into(picture)
        } else {
            glide.load(ContextCompat.getDrawable(context, R.drawable.default_avatar)).apply(RequestOptions().circleCrop()).into(picture)
        }

        val name = "${recipient.firstName} ${recipient.lastName.toUpperCase()}"
        username.text = name
        // TODO:
        hourOfArrival.text = "Heure d'arrivée"
    }
}