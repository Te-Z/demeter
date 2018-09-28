package app.tez.demeter.addRecipient

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

import app.tez.demeter.R
import app.tez.demeter.bases.BaseEditText
import app.tez.demeter.exceptions.DATE_OF_BIRTH_MUST_NOT_BE_EMPTY
import app.tez.demeter.exceptions.RecipientException
import app.tez.demeter.models.Recipient
import app.tez.demeter.utils.DialogUtils
import kotlinx.android.synthetic.main.fragment_add_recipient.view.*
import java.lang.Exception
import java.util.*

private const val TAG = "AddRecipientFragment"

class AddRecipientFragment : Fragment(), AdapterView.OnItemSelectedListener {

    companion object {
        fun newInstance(): AddRecipientFragment = AddRecipientFragment()
    }

    // DESIGN
    private lateinit var rootView: View
    private lateinit var lastNameEditText: BaseEditText
    private lateinit var firstNameEditText: BaseEditText
    private lateinit var dateOfBirthButton: Button
    private lateinit var sexSpinner: Spinner
    private lateinit var nationalityEditText: BaseEditText
    private lateinit var meetingPlaceEditText: BaseEditText
    private lateinit var isPlannedSwitch: Switch
    private lateinit var isContactedSwitch: Switch
    private lateinit var contactSpinner: Spinner
    private lateinit var isWithPartnerSwitch: Switch
    private lateinit var partnerSpinner: Spinner
    private lateinit var submitButton: Button

    // DATA
    private lateinit var selectedSex: String
    private lateinit var sexArrayAdapter: ArrayAdapter<CharSequence>
    private lateinit var selectedContact: String
    private lateinit var contactArrayAdapter: ArrayAdapter<CharSequence>
    private lateinit var selectedPartner: String
    private lateinit var partnerArrayAdapter: ArrayAdapter<CharSequence>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        this.rootView = inflater.inflate(R.layout.fragment_add_recipient, container, false)
        this.initViews()
        this.configureSexSpinner()
        this.configureContactSwitch()
        this.configurePartnerSwitch()
        this.configureContactSpinner()
        this.configurePartnerSpinner()
        DialogUtils.configureDatePicker(dateOfBirthButton, context)
        this.configureOnSubmitClick()
        return rootView
    }

    // ------------
    // ACTIONS
    // ------------

    private fun configureOnSubmitClick(){
        submitButton.setOnClickListener { this.onClickSubmitVerifications() }
    }
    
    private fun onClickSubmitVerifications(){
        try{
            lastNameEditText.checkIfEmpty()
            firstNameEditText.checkIfEmpty()
            nationalityEditText.checkIfEmpty()
            meetingPlaceEditText.checkIfEmpty()
            if (dateOfBirthButton.text == getString(R.string.recipient_date_birth_hint)) throw RecipientException(DATE_OF_BIRTH_MUST_NOT_BE_EMPTY)
            this.createRecipient()
        } catch (e: Exception){
            when(e){
                is RecipientException -> if (e.message == DATE_OF_BIRTH_MUST_NOT_BE_EMPTY) DialogUtils.openDateAlertDialog(context, R.string.set_birth_date_error)
            }
        }
    }

    private fun createRecipient(){
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year =  calendar.get(Calendar.YEAR)

        val newRecipient = Recipient(
                firstNameEditText.text.toString(),
                lastNameEditText.text.toString(),
                dateOfBirthButton.text.toString(),
                selectedSex,
                50,
                null,
                meetingPlaceEditText.text.toString(),
                isPlannedSwitch.isChecked,
                if (isWithPartnerSwitch.isChecked) selectedPartner else null,
                "$day/$month/$year",
                nationalityEditText.text.toString()
        )

        Toast.makeText(context, getString(R.string.recipient_created), Toast.LENGTH_SHORT).show()
    }

    // ------------
    // UI
    // ------------

    private fun initViews(){
        this.lastNameEditText = rootView.fragment_add_recipient_lastname
        this.firstNameEditText = rootView.fragment_add_recipient_firstname
        this.dateOfBirthButton = rootView.fragment_add_recipient_date_birth
        this.sexSpinner = rootView.fragment_add_recipient_sexe_spinner
        this.nationalityEditText = rootView.fragment_add_recipient_nationality
        this.meetingPlaceEditText = rootView.fragment_add_recipient_meeting_place
        this.isPlannedSwitch = rootView.fragment_add_recipient_planned_switch
        this.isContactedSwitch = rootView.fragment_add_recipient_contact_switch
        this.contactSpinner = rootView.fragment_add_recipient_contact_spinner
        this.isWithPartnerSwitch = rootView.fragment_add_recipient_partner_switch
        this.partnerSpinner = rootView.fragment_add_recipient_partner_spinner
        this.submitButton = rootView.fragment_add_recipient_submit
    }

    private fun configureSexSpinner(){
        context?.let {
            this.sexArrayAdapter = ArrayAdapter.createFromResource(it, R.array.recipient_sex_array, android.R.layout.simple_spinner_item)
            this.sexArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            this.sexSpinner.onItemSelectedListener = this
            this.sexSpinner.adapter = sexArrayAdapter
        }
    }

    private fun configureContactSwitch(){
        this.isContactedSwitch.setOnCheckedChangeListener { _, b ->
            when(b){
                true -> contactSpinner.visibility = View.VISIBLE
                false -> contactSpinner.visibility = View.GONE
            }
        }
    }

    private fun configurePartnerSwitch(){
        this.isWithPartnerSwitch.setOnCheckedChangeListener { _, b -> 
            when(b){
                true -> partnerSpinner.visibility = View.VISIBLE
                false -> partnerSpinner.visibility = View.GONE
            }
        }
    }

    private fun configureContactSpinner(){
        context?.let {
            this.contactArrayAdapter = ArrayAdapter.createFromResource(it, R.array.recipient_contact_agent_array, android.R.layout.simple_spinner_item)
            this.contactArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            this.contactSpinner.onItemSelectedListener = this
            this.contactSpinner.adapter = contactArrayAdapter
        }
    }

    private fun configurePartnerSpinner(){
        context?.let {
            this.partnerArrayAdapter = ArrayAdapter.createFromResource(it, R.array.partners_array, android.R.layout.simple_spinner_item)
            this.partnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            this.partnerSpinner.onItemSelectedListener = this
            this.partnerSpinner.adapter = contactArrayAdapter
        }
    }

    // ------------
    // UTILS
    // ------------

    override fun onNothingSelected(p0: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(parent?.id){
            sexSpinner.id -> {
                val sex = parent.getItemAtPosition(position) as String
                when(sex){
                    getString(R.string.recipient_male) -> selectedSex = "M"
                    getString(R.string.recipient_female) -> selectedSex = "F"
                    getString(R.string.recipient_other_sex) -> selectedSex = "O"
                }
                Log.d(TAG, "onItemSelected: selectedSex = $selectedSex")
            }
            contactSpinner.id -> {
                selectedContact = parent.getItemAtPosition(position) as String
                Log.d(TAG, "onItemSelected: selectedContact = $selectedContact")
            }
            partnerSpinner.id -> {
                selectedPartner = parent.getItemAtPosition(position) as String
                Log.d(TAG, "onItemSelected: selectedPartner = $selectedPartner")
            }
        }
    }
}
