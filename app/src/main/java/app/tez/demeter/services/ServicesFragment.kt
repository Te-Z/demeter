package app.tez.demeter.services


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import app.tez.demeter.FakeUsers
import app.tez.demeter.R
import app.tez.demeter.models.Recipient
import app.tez.demeter.utils.ExpandAndCollapseViewUtil
import kotlinx.android.synthetic.main.fragment_services.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class ServicesFragment : Fragment() {

    companion object {
        fun newInstance(): ServicesFragment = ServicesFragment()
        private val DURATION = 250
    }

    // DATA
    private var testList = mutableListOf<Recipient>()

    private lateinit var rootView: View
    private lateinit var laundryDetails: LinearLayout
    private lateinit var laundryImageViewExpand: ImageView
    private lateinit var laundryDetailLayout: LinearLayout
    private lateinit var laundryRecyclerView: RecyclerView
    private lateinit var laundryAdapter: SimpleServicesAdapter

    private lateinit var lockersDetails: LinearLayout
    private lateinit var lockersImageViewExpand: ImageView
    private lateinit var lockersDetailLayout: LinearLayout
    private lateinit var lockersRecyclerView: RecyclerView
    private lateinit var lockersAdapter: SimpleServicesAdapter

    private lateinit var donationsDetails: LinearLayout
    private lateinit var donationsImageViewExpand: ImageView
    private lateinit var donationsDetailLayout: LinearLayout
    private lateinit var donationsRecyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_services, container, false)

        //TODO: remove testList
        FakeUsers.setTestList(testList)

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

        val laundryToolbar = rootView.laundry_toolbar
        laundryToolbar.title = getString(R.string.laundry)
        laundryToolbar.subtitle = getString(R.string.laundry)

        laundryToolbar.inflateMenu(R.menu.services_menu)
        laundryToolbar.menu.getItem(0).setOnMenuItemClickListener {
            Toast.makeText(context, "Laundry", Toast.LENGTH_SHORT).show()
            return@setOnMenuItemClickListener true
        }

        // Animate the expand/collapse of the bottom
        laundryDetailLayout.setOnClickListener { this.toggleDetails(laundryDetails, laundryImageViewExpand) }
    }

    private fun configureLaundryRecyclerView(){
        this.laundryAdapter = SimpleServicesAdapter(testList)
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

        val lockersToolbar = rootView.lockers_toolbar
        lockersToolbar.title = getString(R.string.lockers)
        lockersToolbar.subtitle = getString(R.string.lockers)

        lockersToolbar.inflateMenu(R.menu.services_menu)
        lockersToolbar.menu.getItem(0).setOnMenuItemClickListener{
            Toast.makeText(context, "Lockers", Toast.LENGTH_SHORT).show()
            return@setOnMenuItemClickListener true
        }

        // Animate the expand/collapse of the bottom
        lockersDetailLayout.setOnClickListener { this.toggleDetails(lockersDetails, lockersImageViewExpand) }
    }

    private fun configureLockersRecyclerView(){
        this.lockersAdapter = SimpleServicesAdapter(testList)
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

        val donationsToolbar = rootView.donations_toolbar
        donationsToolbar.title = getString(R.string.donations)
        donationsToolbar.subtitle = getString(R.string.donations)

        donationsToolbar.inflateMenu(R.menu.services_menu)
        donationsToolbar.menu.getItem(0).setOnMenuItemClickListener {
            Toast.makeText(context, "Donations", Toast.LENGTH_SHORT).show()
            return@setOnMenuItemClickListener true
        }

        // Animate the expand/collapse of the bottom
        donationsDetailLayout.setOnClickListener { this.toggleDetails(donationsDetails, donationsImageViewExpand) }
    }

    private fun configureDonationsRecyclerView(){

    }

    // -------------
    // UI UTILS
    // -------------

    private fun toggleDetails(layout: LinearLayout, imageView: ImageView) {
        if (layout.visibility == View.GONE) {
            ExpandAndCollapseViewUtil.expand(layout, DURATION)
            imageView.setImageResource(R.drawable.ic_more)
            rotate(-180.0f, imageView)
        } else {
            ExpandAndCollapseViewUtil.collapse(layout, DURATION)
            imageView.setImageResource(R.drawable.ic_less)
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
