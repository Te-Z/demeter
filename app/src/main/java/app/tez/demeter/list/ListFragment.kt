package app.tez.demeter.list


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.tez.demeter.Fake
import app.tez.demeter.R
import app.tez.demeter.models.Recipient
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_list.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class ListFragment : Fragment() {

    companion object {
        fun newInstance(): ListFragment = ListFragment()
    }

    private lateinit var rootView: View
    private val testList = mutableListOf<Recipient>()
    private lateinit var adapter: ListAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        //TODO: remove testList

        rootView = inflater.inflate(R.layout.fragment_list, container, false)
        Fake.userList(testList)
        this.configureRecyclerView()
        // Inflate the layout for this fragment
        return rootView
    }

    // -----------------
    // CONFIGURATION
    // -----------------

    private fun configureRecyclerView(){
        context?.let {ctx ->
            fragmentManager?.let {manager ->
                this.adapter = ListAdapter(testList, Glide.with(ctx), manager)
                this.recyclerView = rootView.list_recyclerview
                this.recyclerView.adapter = this.adapter
                this.recyclerView.layoutManager = LinearLayoutManager(ctx)
            }
        }
    }
}
