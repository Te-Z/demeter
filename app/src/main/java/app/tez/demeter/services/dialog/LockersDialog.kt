package app.tez.demeter.services.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import app.tez.demeter.Fake
import app.tez.demeter.R
import app.tez.demeter.models.Recipient
import kotlinx.android.synthetic.main.dialog_lockers.view.*

/**
 * Created by Terence Zafindratafa on 22/09/2018
 */
class LockersDialog : DialogFragment(), AdapterView.OnItemSelectedListener{

    companion object {
        val TAG = "LockersDialog"
    }

    // DESIGN
    private lateinit var rootView: View
    private lateinit var positiveButton: Button
    private lateinit var negativeButton: Button
    private lateinit var lockersSpinner: Spinner
    private lateinit var personArrayAdapter: ArrayAdapter<String>

    // TODO: For test
    private var userList = mutableListOf<Recipient>()
    private var personList = mutableListOf<String>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        val inflater = LayoutInflater.from(context)
        this.rootView = inflater.inflate(R.layout.dialog_lockers, null)

        // TODO: enlever les lignes de test
        Fake.recipientList(userList)
        for(recipient in userList){
            val string = recipient.firstName + " " + recipient.lastName
            personList.add(string)
        }
        this.configureSpinner(personList)
        this.configureButtons()
        builder.setView(rootView)

        return builder.create()
    }

    private fun configureSpinner(username: List<String>){
        this.lockersSpinner = this.rootView.dialog_lockers_spinner
        context?.let {
            this.personArrayAdapter = ArrayAdapter(it, android.R.layout.simple_spinner_item, username)
            this.personArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            this.lockersSpinner.onItemSelectedListener = this
            this.lockersSpinner.adapter = personArrayAdapter
        }
    }

    private fun configureButtons(){
        this.positiveButton = this.rootView.dialog_lockers_submit
        this.negativeButton = this.rootView.dialog_lockers_cancel

        this.negativeButton.setOnClickListener { dialog.cancel() }
        this.positiveButton.setOnClickListener { dialog.dismiss() }
    }

    // ----------------
    // SPINNER ITEM
    // ----------------

    override fun onNothingSelected(p0: AdapterView<*>?) {}

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}
}