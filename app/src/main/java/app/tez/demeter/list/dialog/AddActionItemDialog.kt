package app.tez.demeter.list.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.DialogFragment
import app.tez.demeter.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.add_action_item_dialog.view.*
import java.time.DayOfWeek
import java.util.*

/**
 * Created by Terence Zafindratafa on 02/10/2018
 */
const val AddActionItemDialogTag = "AddActionItemDialog"

class AddActionItemDialog: DialogFragment(), AdapterView.OnItemSelectedListener {

    // DESIGN
    private lateinit var rootView: View
    private lateinit var educatorTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var hourTextView: TextView
    private lateinit var typeSpinner: Spinner
    private lateinit var actionSpinner: Spinner
    private lateinit var detailSpinner: Spinner
    private lateinit var cancelButton: Button
    private lateinit var sendButton: Button
    private lateinit var badMoodImageView: ImageView
    private lateinit var goodMoodImageView: ImageView

    // DATA
    private lateinit var selectedType: String
    private lateinit var selectedAction: String
    private lateinit var selectedDetails: String
    private lateinit var typeArrayAdapter: ArrayAdapter<CharSequence>
    private lateinit var detailArrayAdapter: ArrayAdapter<CharSequence>
    private lateinit var actionArrayAdapter: ArrayAdapter<CharSequence>

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        val inflater = LayoutInflater.from(context)
        this.rootView = inflater.inflate(R.layout.add_action_item_dialog, null)

        this.initViews()
        this.setMoodImages()
        this.setButtons()
        this.setTime()
        this.getEducator()
        this.configureTypeSpinner()

        builder.setView(rootView)
        return builder.create()
    }

    private fun initViews(){
        this.educatorTextView = rootView.add_ai_dialog_educator_name
        this.dateTextView = rootView.add_ai_dialog_date
        this.hourTextView = rootView.add_ai_dialog_hour
        this.typeSpinner = rootView.add_ai_dialog_type_spinner
        this.actionSpinner = rootView.add_ai_dialog_action_spinner
        this.detailSpinner = rootView.add_ai_dialog_action_details
        this.cancelButton = rootView.add_ai_dialog_cancel_button
        this.sendButton = rootView.add_ai_dialog_send_button
        this.badMoodImageView = rootView.add_ai_dialog_bad_mood
        this.goodMoodImageView = rootView.add_ai_dialog_good_mood
    }

    private fun setMoodImages(){
        context?.let {ctx ->
            this.badMoodImageView.setOnClickListener {
                ImageViewCompat.setImageTintList(badMoodImageView, ColorStateList.valueOf(ContextCompat.getColor(ctx, R.color.colorBadMood)))
                ImageViewCompat.setImageTintList(goodMoodImageView, ColorStateList.valueOf(ContextCompat.getColor(ctx, R.color.colorPrimaryDark)))
            }
            this.goodMoodImageView.setOnClickListener {
                ImageViewCompat.setImageTintList(goodMoodImageView, ColorStateList.valueOf(ContextCompat.getColor(ctx, R.color.colorGoodMood)))
                ImageViewCompat.setImageTintList(badMoodImageView, ColorStateList.valueOf(ContextCompat.getColor(ctx, R.color.colorPrimaryDark)))
            }
        }
    }

    private fun setButtons(){
        cancelButton.setOnClickListener { dialog.cancel() }
        sendButton.setOnClickListener {
            Toast.makeText(context, "Evènement ajouté !", Toast.LENGTH_SHORT).show()
            dialog.cancel()
        }
    }

    private fun setTime(){
        val cal = Calendar.getInstance()
        val day = if(cal.get(Calendar.DAY_OF_WEEK) < 10) "0${cal.get(Calendar.DAY_OF_WEEK)}" else cal.get(Calendar.DAY_OF_WEEK).toString()
        val month = if(cal.get(Calendar.MONTH) < 10) "0${cal.get(Calendar.MONTH)}" else cal.get(Calendar.MONTH).toString()
        val year = cal.get(Calendar.YEAR)
        val min = if(cal.get(Calendar.MINUTE) < 10) "0${cal.get(Calendar.MINUTE)}" else cal.get(Calendar.MINUTE).toString()
        val hour = if(cal.get(Calendar.HOUR_OF_DAY) < 10) "0${cal.get(Calendar.HOUR_OF_DAY)}" else cal.get(Calendar.HOUR_OF_DAY).toString()

        val date = "$day/$month/$year"
        val hourTxt = "$hour:$min"

        dateTextView.text = date
        hourTextView.text = hourTxt
    }

    private fun getEducator(){
        educatorTextView.text = "Terence Zafindratafa"
    }

    private fun configureTypeSpinner(){
        context?.let {
            this.typeArrayAdapter = ArrayAdapter.createFromResource(it, R.array.action_types, android.R.layout.simple_spinner_item)
            this.typeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            this.typeSpinner.onItemSelectedListener = this
            this.typeSpinner.adapter = typeArrayAdapter
        }
    }

    private fun configureActionSpinner(textArrayResId: Int){
        context?.let {
            this.actionArrayAdapter = ArrayAdapter.createFromResource(it, textArrayResId, android.R.layout.simple_spinner_item)
            this.actionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            this.actionSpinner.onItemSelectedListener = this
            this.actionSpinner.adapter = actionArrayAdapter
        }
        actionSpinner.visibility = View.VISIBLE
    }

    private fun configureDetailsSpinner(textArrayResId: Int){
        context?.let {
            this.detailArrayAdapter = ArrayAdapter.createFromResource(it, textArrayResId, android.R.layout.simple_spinner_item)
            this.detailArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            this.detailSpinner.onItemSelectedListener = this
            this.detailSpinner.adapter = detailArrayAdapter
        }
        detailSpinner.visibility = View.VISIBLE
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(parent?.id){
            typeSpinner.id -> {
                selectedType = parent.getItemAtPosition(position) as String
                when(selectedType){
                    "Entretien" -> configureActionSpinner(R.array.items_entretien)
                    "Action" -> configureActionSpinner(R.array.items_action)
                    "Démarche" -> configureActionSpinner(R.array.items_demarches)
                    "Accompagnement" -> configureActionSpinner(R.array.items_accompagnement)
                    "Visites" -> configureActionSpinner(R.array.items_visites)
                    else -> actionSpinner.visibility = View.GONE
                }
            }
            actionSpinner.id -> {
                selectedAction = parent.getItemAtPosition(position) as String
                when(selectedAction){
                    "Orientation" -> configureDetailsSpinner(R.array.orientation_details)
                    "Appel d'urgence" -> configureDetailsSpinner(R.array.appel_urgence_details)
                    "Démarches Sociale" -> configureDetailsSpinner(R.array.demarche_sociale_details)
                    "Administration" -> configureDetailsSpinner(R.array.administration_details)
                    "Démarche Médicale" -> configureDetailsSpinner(R.array.demarche_medical_details)
                    "Accompagnement Médical" -> configureDetailsSpinner(R.array.accompagnement_medical_details)
                    "Nuit" -> configureDetailsSpinner(R.array.accompagnement_nuit_details)
                    "Accompagnement Social" -> configureDetailsSpinner(R.array.accompagnement_social_details)
                    "Achats" -> configureDetailsSpinner(R.array.accompagnement_achats)
                    else -> detailSpinner.visibility = View.GONE
                }
            }
        }
    }
}