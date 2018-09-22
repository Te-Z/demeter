package app.tez.demeter.services.dialog


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.tez.demeter.Fake

import app.tez.demeter.R
import app.tez.demeter.models.DonationItem
import app.tez.demeter.models.Recipient
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.dialog_add_laundry.view.*

class InventoryAddDialog: DialogFragment(), ChipGroup.OnCheckedChangeListener , AdapterView.OnItemSelectedListener {

    companion object {
        const val TAG = "InventoryAddDialog"
    }

    // DESIGN
    private lateinit var rootView: View
    private lateinit var toolbar: Toolbar
    private lateinit var chipGroup: ChipGroup
    private lateinit var personSpinner: Spinner
    private lateinit var itemSpinner: Spinner
    private lateinit var personArrayAdapter: ArrayAdapter<String>
    private lateinit var itemArrayAdapter: ArrayAdapter<CharSequence>
    private lateinit var recyclerViewAdapter: LaundryAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var addBtn:Button

    // DATA
    private lateinit var selectedItem: String
    private lateinit var selectedPerson: String
    private var itemList = mutableListOf<DonationItem>()

    // For test
    private var userList = mutableListOf<Recipient>()
    private var personList = mutableListOf<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        this.rootView = inflater.inflate(R.layout.dialog_add_laundry, container, false)

        this.configureToolbar()
        this.configureChips()

        // TODO: enlever les lignes de test
        Fake.userList(userList)
        for(recipient in userList){
            val string = recipient.firstName + " " + recipient.lastName
            personList.add(string)
        }

        this.configurePersonSpinner(personList)
        this.configureItemSpinner()
        this.configureRecyclerview()
        this.configureAddItemButton()

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog.window?.let { it.attributes.windowAnimations = R.style.DialogAnimation }
    }

    // -----------------
    // ACTIONS
    // -----------------

    private fun configureAddItemButton(){
        this.addBtn = rootView.laundry_add_item
        this.addBtn.isClickable = false
    }

    // -----------------
    // UI
    // -----------------

    private fun configureToolbar(){
        this.toolbar = rootView.laundry_dialog_toolbar
        this.toolbar.setNavigationOnClickListener{ dialog.dismiss() }
        this.toolbar.inflateMenu(R.menu.laundry_menu)
        this.toolbar.menu.getItem(0).setOnMenuItemClickListener {
            Log.d(TAG, "configureToolbar: click !")
            dialog.dismiss()
            return@setOnMenuItemClickListener true
        }
    }

    private fun configureRecyclerview(){
        this.recyclerViewAdapter = LaundryAdapter(itemList)
        this.recyclerView = rootView.add_laundry_recyclerview
        this.recyclerView.adapter = this.recyclerViewAdapter
        this.recyclerView.layoutManager = LinearLayoutManager(context)
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

    @SuppressLint("ResourceType")
    override fun onCheckedChanged(chipGroup: ChipGroup?, position: Int) {
        Log.d(TAG, "onCheckedChanged: $position")
        if(position > 0){
            this.addBtn.isClickable = true
            this.addBtn.setOnClickListener {
                val item = DonationItem(selectedItem, position)
                itemList.add(item)
                recyclerViewAdapter.notifyDataSetChanged()
            }
        } else {
            this.addBtn.isClickable = false
            this.addBtn.setOnClickListener { }
        }
    }
}
