package app.tez.demeter.list.dialog


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.tez.demeter.Fake

import app.tez.demeter.R
import app.tez.demeter.list.ListAdapterTAG
import app.tez.demeter.models.Recipient
import app.tez.demeter.utils.Utils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_recipient_profile_dialog.view.*

/**
 * A simple [Fragment] subclass.
 *
 */

private const val TAG = "RecipientProfileDialog"

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
    private lateinit var sexTextView: TextView
    private lateinit var birthDateTextView: TextView
    private lateinit var nationalityTextView: TextView
    private lateinit var meetingPlaceTextView: TextView
    private lateinit var partnerTextView: TextView
    private lateinit var expandButton: ImageView
    private lateinit var detailsLayout: LinearLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipientProfileDialogAdapter

    // DATA
    private lateinit var recipient: Recipient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.recipient = arguments?.getParcelable(ListAdapterTAG) as Recipient
        Log.d(TAG, "onCreate: bundle test: ${recipient.firstName} ${recipient.lastName}")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        this.rootView = inflater.inflate(R.layout.fragment_recipient_profile_dialog, container, false)
        this.initViews()
        this.configureDismissButton()
        //this.configureViewPager()
        this.configureViews()
        this.configureRecyclerView()
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog.window?.let { it.attributes.windowAnimations = R.style.DialogAnimation }
    }

    // -----------------
    // CONFIGURATION
    // -----------------

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
        this.sexTextView = rootView.fragment_recipient_profile_sex
        this.birthDateTextView = rootView.fragment_recipient_profile_birth
        this.nationalityTextView = rootView.fragment_recipient_profile_nationality
        this.meetingPlaceTextView = rootView.fragment_recipient_profile_meeting_place
        this.partnerTextView = rootView.fragment_recipient_profile_meeting_partner
        this.expandButton = rootView.fragment_recipient_profile_expand_button
        this.detailsLayout = rootView.fragment_recipient_profile_details
    }

    private fun configureViews(){
        val color = when(recipient.mood){
            in 0..24 ->  R.drawable.bad_mood
            in 25..49 -> R.drawable.quarter_mood
            in 50..74 -> R.drawable.quarter_sec_mood
            in 57..100 -> R.drawable.top_mood
            else -> throw Exception("This mood is not between 0 and 100: ${recipient.mood}")
        }
        context?.let {
            val glide = Glide.with(it)
            if(recipient.avatar == null){
                glide.load(ContextCompat.getDrawable(it, R.drawable.default_avatar)).apply(RequestOptions().centerCrop()).into(profilePic)
            } else {
                glide.load(recipient.avatar).apply(RequestOptions().centerCrop()).into(profilePic)
            }
            glide.load(ContextCompat.getDrawable(it, color)).apply(RequestOptions().circleCrop()).into(moodImage)
        }

        val name = "${recipient.firstName} ${recipient.lastName.toUpperCase()}"
        val mood = "${recipient.mood} %"
        nameTextView.text = name
        moodTextView.text = mood
        sexTextView.text = recipient.sexe
        dateTextView.text = recipient.firstRegistration
        birthDateTextView.text = recipient.dateOfBirth
        nationalityTextView.text = recipient.nationality
        meetingPlaceTextView.text = recipient.meetingPlace
        partnerTextView.text = if(recipient.partner != null) recipient.partner else getString(R.string.aucun)

        expandButton.setOnClickListener { Utils.toggleDetails(detailsLayout, expandButton) }
    }

    private fun configureDismissButton(){
        this.backButton.setOnClickListener { dialog.dismiss() }
    }

    private fun configureRecyclerView(){
        context?.let {ctx ->
            this.adapter = RecipientProfileDialogAdapter(Fake.ActionItemList())
            this.recyclerView = rootView.fragment_recipient_profile_dialog_rv
            this.recyclerView.adapter = this.adapter
            this.recyclerView.layoutManager = LinearLayoutManager(ctx)
        }
    }
}
