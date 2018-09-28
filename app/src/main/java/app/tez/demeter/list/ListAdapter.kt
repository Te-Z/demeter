package app.tez.demeter.list

import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import app.tez.demeter.R
import app.tez.demeter.list.dialog.RecipientProfileDialogFragment
import app.tez.demeter.models.Recipient
import com.bumptech.glide.RequestManager

/**
 * Created by Terence Zafindratafa on 11/09/2018
 */

const val ListAdapterTAG = "ListAdapter"

class ListAdapter(private val recipientList: List<Recipient>, private val glide: RequestManager, private val manager: FragmentManager): RecyclerView.Adapter<ListViewHolder>(){

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
        viewHolder.layout.setOnClickListener {
            val ft = manager.beginTransaction()
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            val dialog = RecipientProfileDialogFragment()
            val bundle = Bundle()
            bundle.putParcelable(ListAdapterTAG, recipientList[position])
            dialog.arguments = bundle
            dialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_Demeter)
            dialog.show(ft, ListAdapterTAG)
        }
    }
}