package app.tez.demeter.services.dialog


import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import androidx.appcompat.widget.Toolbar
import app.tez.demeter.Fake

import app.tez.demeter.R
import app.tez.demeter.models.Recipient
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.fragment_add_laundry.view.*

class AddLaundryFragment: DialogFragment(), ChipGroup.OnCheckedChangeListener , AdapterView.OnItemSelectedListener {

    companion object {
        const val TAG = "AddLaundryFragment"
    }

    // DESIGN
    private lateinit var rootView: View
    private lateinit var toolbar: Toolbar
    private lateinit var chipGroup: ChipGroup
    private lateinit var personSpinner: Spinner
    private lateinit var itemSpinner: Spinner
    private lateinit var personArrayAdapter: ArrayAdapter<String>
    private lateinit var itemArrayAdapter: ArrayAdapter<CharSequence>

    // DATA
    private lateinit var selectedItem: String
    private lateinit var selectedPerson: String

    // For test
    private var userList = mutableListOf<Recipient>()
    private var personList = mutableListOf<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        this.rootView = inflater.inflate(R.layout.fragment_add_laundry, container, false)

        this.configureToolbar()
        this.configureChips()

        // TODO: enlever les lignes de test
        Fake.userList(userList)
        for(recipient in userList){
            val string = recipient.firstName + recipient.lastName
            personList.add(string)
        }

        this.configurePersonSpinner(personList)
        this.configureItemSpinner()
        this.configureRecyclerview()

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog.window?.let { it.attributes.windowAnimations = R.style.DialogAnimation }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.services_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    // -----------------
    // UI
    // -----------------

    private fun configureToolbar(){
        this.toolbar = rootView.laundry_toolbar
        this.toolbar.setOnClickListener{ dialog.dismiss() }
        this.toolbar.title = getString(R.string.laundry)
        this.toolbar.menu
    }

    private fun configureRecyclerview(){

    }

    private fun configurePersonSpinner(username: List<String>){
        this.personSpinner = rootView.add_laundry_person_spinner
        context?.let {
            this.personArrayAdapter = ArrayAdapter(it, android.R.layout.simple_spinner_item, username)
            this.personArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            this.personSpinner.onItemSelectedListener = this
            this.personSpinner.adapter = personArrayAdapter
        }
    }

    private fun configureItemSpinner(){
        this.itemSpinner = rootView.add_laundry_item_spinner
        context?.let {
            this.itemArrayAdapter = ArrayAdapter.createFromResource(it, R.array.donations_array, android.R.layout.simple_spinner_item)
            this.itemArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            this.itemSpinner.onItemSelectedListener = this
            this.itemSpinner.adapter = itemArrayAdapter
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(parent?.id){
            rootView.add_laundry_item_spinner.id -> {
                selectedItem = parent.getItemAtPosition(position) as String
                Log.d(TAG, "onItemSelected: selectedItem = $selectedItem")
            }
            rootView.add_laundry_person_spinner.id -> {
                selectedPerson = parent.getItemAtPosition(position) as String
                Log.d(TAG, "onItemSelected: selectedPersone = $selectedPerson")
            }
        }
    }

    private fun configureChips(){
        this.chipGroup = rootView.laundry_chipgroup
        for (i in 1..30){
            val chip = Chip(context, null, R.style.Widget_MaterialComponents_Chip_Choice)
            chip.text = i.toString()
            chip.isClickable = true
            chip.isCheckable = true
            chipGroup.addView(chip)
        }
        chipGroup.setOnCheckedChangeListener(this)
    }

    override fun onCheckedChanged(chipGroup: ChipGroup?, position: Int) {
        Log.d(TAG, "onCheckedChanged: $position")
    }
}
