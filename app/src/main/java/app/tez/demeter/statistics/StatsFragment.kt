package app.tez.demeter.statistics


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.tez.demeter.R

/**
 * A simple [Fragment] subclass.
 *
 */
class StatsFragment : Fragment() {

    companion object {
        fun newInstance(): StatsFragment = StatsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats, container, false)
    }


}