package app.tez.demeter.services.dialog


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import app.tez.demeter.R
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.fragment_add_laundry.view.*

class AddLaundryFragment: DialogFragment(), ChipGroup.OnCheckedChangeListener {

    companion object {
        const val TAG = "AddLaundryFragment"
    }

    // DESIGN
    private lateinit var rootView: View
    private lateinit var chipGroup: ChipGroup

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        this.rootView = inflater.inflate(R.layout.fragment_add_laundry, container, false)

        this.configureChips()

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog.window?.let { it.attributes.windowAnimations = R.style.DialogAnimation }
    }

    // -----------------
    // UI
    // -----------------

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
