package app.tez.demeter.services


import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import app.tez.demeter.Fake
import app.tez.demeter.R
import app.tez.demeter.models.DonationItem
import app.tez.demeter.models.Recipient
import app.tez.demeter.services.dialog.InventoryAddDialog
import app.tez.demeter.services.dialog.LockersDialog
import app.tez.demeter.services.recyclerview.DonationsAdapter
import app.tez.demeter.services.recyclerview.SimpleServicesAdapter
import app.tez.demeter.utils.ExpandAndCollapseViewUtil
import kotlinx.android.synthetic.main.fragment_services.view.*
import java.util.concurrent.locks.Lock

/**
 * A simple [Fragment] subclass.
 *
 */

private const val DURATION = 250
private const val TAG = "ServicesFragment"

class ServicesFragment : Fragment() {

    companion object {
        fun newInstance(): ServicesFragment = ServicesFragment()
    }

    // DATA
    private var userTestList = mutableListOf<Recipient>()
    private var itemTestList = mutableListOf<DonationItem>()

    // DESIGN
    private lateinit var rootView: View
    private lateinit var laundryDetails: LinearLayout
    private lateinit var laundryImageViewExpand: ImageView
    private lateinit var laundryDetailLayout: LinearLayout
    private lateinit var laundryRecyclerView: RecyclerView
    private lateinit var laundryAdapter: SimpleServicesAdapter
    private lateinit var laundryDialogButton: ImageButton

    private lateinit var lockersDetails: LinearLayout
    private lateinit var lockersImageViewExpand: ImageView
    private lateinit var lockersDetailLayout: LinearLayout
    private lateinit var lockersRecyclerView: RecyclerView
    private lateinit var lockersAdapter: SimpleServicesAdapter
    private lateinit var lockersDialogButton: ImageButton

    private lateinit var donationsDetails: LinearLayout
    private lateinit var donationsImageViewExpand: ImageView
    private lateinit var donationsDetailLayout: LinearLayout
    private lateinit var donationsRecyclerView: RecyclerView
    private lateinit var donationsAdapter: DonationsAdapter
    private lateinit var donationsDialogButton: ImageButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_services, container, false)

        //TODO: remove testLists
        Fake.userList(userTestList)
        context?.let { Fake.donationItemList(itemTestList, it) }

        this.configureLaundryCardView()
        this.configureLaundryRecyclerView()
        this.configureLockersCardView()
        this.configureLockersRecyclerView()
        this.configureDonationsCardView()
        this.configureDonationsRecyclerView()
        // Inflate the layout for this fragment
        return rootView
    }

    // --------------
    // UI
    // --------------

    private fun configureLaundryCardView(){
        laundryDetails = rootView.laundry_details
        laundryImageViewExpand = rootView.laundry_imageViewExpand
        laundryDetailLayout = rootView.laundry_detail_content
        laundryDialogButton = rootView.laundry_card_btn
        laundryDialogButton.setOnClickListener { this.showInventoryDialog() }

        // Animate the expand/collapse of the bottom
        laundryDetailLayout.setOnClickListener { this.toggleDetails(laundryDetails, laundryImageViewExpand) }
    }

    private fun configureLaundryRecyclerView(){
        this.laundryAdapter = SimpleServicesAdapter(userTestList)
        val laundryLayoutManager = LinearLayoutManager(context)
        laundryRecyclerView = rootView.laundry_rv.apply {
            adapter = laundryAdapter
            layoutManager = laundryLayoutManager
        }
    }

    private fun configureLockersCardView(){
        lockersDetails = rootView.lockers_details
        lockersImageViewExpand = rootView.lockers_imageViewExpand
        lockersDetailLayout = rootView.lockers_detail_content
        lockersDialogButton = rootView.lockers_card_btn
        lockersDialogButton.setOnClickListener{ this.showLockersDialog() }

        // Animate the expand/collapse of the bottom
        lockersDetailLayout.setOnClickListener { this.toggleDetails(lockersDetails, lockersImageViewExpand) }
    }

    private fun configureLockersRecyclerView(){
        this.lockersAdapter = SimpleServicesAdapter(userTestList)
        val lockersLayoutManager = LinearLayoutManager(context)
        lockersRecyclerView = rootView.lockers_rv.apply {
            adapter = lockersAdapter
            layoutManager = lockersLayoutManager
        }
    }

    private fun configureDonationsCardView(){
        donationsDetails = rootView.donations_details
        donationsImageViewExpand = rootView.donations_imageViewExpand
        donationsDetailLayout = rootView.donations_detail_content
        donationsDialogButton = rootView.donations_card_btn
        donationsDialogButton.setOnClickListener { this.showInventoryDialog() }

        // Animate the expand/collapse of the bottom
        donationsDetailLayout.setOnClickListener { this.toggleDetails(donationsDetails, donationsImageViewExpand) }
    }

    private fun configureDonationsRecyclerView(){
        this.donationsAdapter = DonationsAdapter(itemTestList)
        val donationsLayoutManager = LinearLayoutManager(context)
        donationsRecyclerView = rootView.donations_rv.apply {
            adapter = donationsAdapter
            layoutManager = donationsLayoutManager
        }
    }

    // -------------
    // UI UTILS
    // -------------

    private fun showInventoryDialog(){
        Log.d(TAG, "showInventoryDialog: click !")
        val dialog = InventoryAddDialog()
        fragmentManager?.let {
            val ft = it.beginTransaction()
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            dialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_Demeter)
            dialog.show(ft, InventoryAddDialog.TAG)
        }
    }

    private fun showLockersDialog(){
        Log.d(TAG, "showLockersDialog: click !")
        val dialog = LockersDialog()
        fragmentManager?.let {
            val ft = it.beginTransaction()
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            dialog.show(ft, LockersDialog.TAG)
        }
    }

    private fun toggleDetails(layout: LinearLayout, imageView: ImageView) {
        if (layout.visibility == View.GONE) {
            ExpandAndCollapseViewUtil.expand(layout, DURATION)
            imageView.setImageResource(R.drawable.ic_more)
            context?.let { ImageViewCompat.setImageTintList(imageView, ColorStateList.valueOf(ContextCompat.getColor(it, R.color.colorNeutral))) }
            rotate(-180.0f, imageView)
        } else {
            ExpandAndCollapseViewUtil.collapse(layout, DURATION)
            imageView.setImageResource(R.drawable.ic_less)
            context?.let { ImageViewCompat.setImageTintList(imageView, ColorStateList.valueOf(ContextCompat.getColor(it, R.color.colorNeutral))) }
            rotate(180.0f, imageView)
        }
    }

    private fun rotate(angle: Float, imageView: ImageView) {
        val animation = RotateAnimation(0.0f, angle, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)
        animation.fillAfter = true
        animation.duration = DURATION.toLong()
        imageView.startAnimation(animation)
    }
}
