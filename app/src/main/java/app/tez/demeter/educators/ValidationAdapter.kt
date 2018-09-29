package app.tez.demeter.educators

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import app.tez.demeter.R
import app.tez.demeter.models.Educator

/**
 * Created by Terence Zafindratafa on 29/09/2018
 */
class ValidationAdapter(private val educatorList: List<Educator>): RecyclerView.Adapter<ValidationViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ValidationViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.activity_validation_item, parent, false)

        return ValidationViewHolder(view)
    }

    override fun getItemCount(): Int = educatorList.size

    override fun onBindViewHolder(holder: ValidationViewHolder, position: Int) {
        holder.updateItem(educatorList[position])
        holder.validationButton.setOnClickListener { Toast.makeText(context, "Validé !", Toast.LENGTH_SHORT).show() }
        holder.deleteButton.setOnClickListener { Toast.makeText(context, "Stoppé !", Toast.LENGTH_LONG).show() }
    }
}