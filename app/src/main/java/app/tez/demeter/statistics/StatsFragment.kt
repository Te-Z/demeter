package app.tez.demeter.statistics


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import app.tez.demeter.R
import app.tez.demeter.utils.Utils
import kotlinx.android.synthetic.main.fragment_stats.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class StatsFragment : Fragment() {

    companion object {
        fun newInstance(): StatsFragment = StatsFragment()
    }

    // DESIGN
    private lateinit var rootView: View
    private lateinit var womenRateTextView: TextView
    private lateinit var moodRateTextView: TextView
    private lateinit var presenceRateTextView: TextView
    private lateinit var statsSpinner: Spinner
    private lateinit var beginDateButton: Button
    private lateinit var endDateButton: Button
    private lateinit var submitButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        this.rootView = inflater.inflate(R.layout.fragment_stats, container, false)
        this.initViews()
        Utils.configureDatePicker(beginDateButton, context)
        Utils.configureDatePicker(endDateButton, context)
        return rootView
    }

    // --------------
    // UI
    // --------------

    private fun initViews(){
        this.womenRateTextView = rootView.fragment_stats_summary_women_rate
        this.moodRateTextView = rootView.fragment_stats_summary_women_rate
        this.presenceRateTextView = rootView.fragment_stats_summary_presence_rate
        this.statsSpinner = rootView.fragment_stats_spinner
        this.beginDateButton = rootView.fragment_stats_date_range_begin
        this.endDateButton = rootView.fragment_stats_date_range_end
        this.submitButton = rootView.fragment_stats_submit
    }
}
