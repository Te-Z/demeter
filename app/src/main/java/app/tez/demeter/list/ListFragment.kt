package app.tez.demeter.list


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        rootView = inflater.inflate(R.layout.fragment_list, container, false)
        this.setTestList()
        this.configureRecyclerView()
        // Inflate the layout for this fragment
        return rootView
    }

    // -----------------
    // CONFIGURATION
    // -----------------

    private fun setTestList(){
        val recipient1 = Recipient("Tez", "Zet", "1990-01-14", 65, null)
        val recipient2 = Recipient("Baz", "Zab", "1990-01-04", 15, null)
        val recipient3 = Recipient("Piou", "Ouip", "1992-11-14", 85, null)
        val recipient4 = Recipient("Bbg", "Gbb", "1993-05-14", 25, null)
        val recipient5 = Recipient("Gez", "Zeg", "1950-01-25", 45, null)
        val recipient6 = Recipient("Chaz", "Zach", "1970-01-14", 95, null)
        val recipient7 = Recipient("Milo", "Olim", "2014-01-14", 15, null)
        val recipient8 = Recipient("Leia", "Tez", "2015-08-31", 5, null)
        val recipient9= Recipient("Fury", "Gan", "1850-01-14", 50, null)
        val recipient0 = Recipient("Sam", "Ouraï", "1900-01-14", 70, null)

        testList.add(recipient0)
        testList.add(recipient1)
        testList.add(recipient2)
        testList.add(recipient3)
        testList.add(recipient4)
        testList.add(recipient5)
        testList.add(recipient6)
        testList.add(recipient7)
        testList.add(recipient8)
        testList.add(recipient9)
    }

    private fun configureRecyclerView(){
        this.adapter = ListAdapter(testList, Glide.with(this))
        this.recyclerView = rootView.list_recyclerview
        this.recyclerView.adapter = this.adapter
        this.recyclerView.layoutManager = LinearLayoutManager(context)
    }
}
