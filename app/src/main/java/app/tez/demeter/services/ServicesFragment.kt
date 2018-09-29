package app.tez.demeter.services


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
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import app.tez.demeter.Fake
import app.tez.demeter.R
import app.tez.demeter.models.DonationItem
import app.tez.demeter.models.Recipient
import app.tez.demeter.services.dialog.InventoryAddDialogFragment
import app.tez.demeter.services.dialog.LockersDialog
import app.tez.demeter.services.recyclerview.DonationsAdapter
import app.tez.demeter.services.recyclerview.SimpleServicesAdapter
import app.tez.demeter.utils.Utils
import kotlinx.android.synthetic.main.fragment_services.view.*

/**
 * A simple [Fragment] subclass.
 *
 */

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
        Fake.recipientList(userTestList)
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
        laundryDetailLayout.setOnClickListener { Utils.toggleDetails(laundryDetails, laundryImageViewExpand) }
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
        lockersDetailLayout.setOnClickListener { Utils.toggleDetails(lockersDetails, lockersImageViewExpand) }
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
        donationsDetailLayout.setOnClickListener { Utils.toggleDetails(donationsDetails, donationsImageViewExpand) }
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
        val dialog = InventoryAddDialogFragment()
        fragmentManager?.let {
            val ft = it.beginTransaction()
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            dialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Theme_Demeter)
            dialog.show(ft, InventoryAddDialogFragment.TAG)
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
}
