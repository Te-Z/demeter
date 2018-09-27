package app.tez.demeter.list.dialog


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.viewpager.widget.ViewPager

import app.tez.demeter.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_recipient_profile_dialog.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class RecipientProfileDialogFragment : DialogFragment() {

    companion object {
        fun newInstance(): RecipientProfileDialogFragment = RecipientProfileDialogFragment()
    }

    // DESIGN
    private lateinit var rootView: View
    private lateinit var moodImage: ImageView
    private lateinit var profilePic: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var moodTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var backButton: ImageButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        this.rootView = inflater.inflate(R.layout.fragment_recipient_profile_dialog, container, false)
        this.initViews()
        this.configureDismissButton()
        this.configureViewPager()
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog.window?.let { it.attributes.windowAnimations = R.style.DialogAnimation }
    }

    // -----------------
    // UI
    // -----------------

    private fun initViews(){
        this.moodImage = rootView.fragment_recipient_profile_mood
        this.profilePic = rootView.fragment_recipient_profile_pic
        this.nameTextView = rootView.fragment_recipient_profile_name
        this.moodTextView = rootView.fragment_recipient_profile_mood_value
        this.dateTextView = rootView.fragment_recipient_profile_date
        this.backButton = rootView.fragment_recipient_profile_back_button
    }

    private fun configureDismissButton(){
        this.backButton.setOnClickListener { dialog.dismiss() }
    }

    private fun configureViewPager(){
        val pager = rootView.findViewById<ViewPager>(R.id.fragment_recipient_profile_dialog_viewpager)
        context?.let { pager.adapter = RecipientProfilePagerAdapter(childFragmentManager, it) }
        val tabs = rootView.findViewById<TabLayout>(R.id.fragment_recipient_profil_tab_layout)
        tabs.setupWithViewPager(pager)
        tabs.tabMode = TabLayout.MODE_FIXED
    }
}
