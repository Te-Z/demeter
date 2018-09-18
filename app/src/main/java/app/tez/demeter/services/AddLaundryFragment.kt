package app.tez.demeter.services


import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import app.tez.demeter.R

class AddLaundryFragment: DialogFragment() {

    companion object {
        val TAG = "AddLaundryFragment"
    }

    // DESGIN
    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        this.rootView = inflater.inflate(R.layout.fragment_add_laundry, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog.window?.let { it.attributes.windowAnimations = R.style.DialogAnimation }
    }
}
