package app.tez.demeter.list.dialog

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import app.tez.demeter.R
import java.lang.Exception

/**
 * Created by Terence Zafindratafa on 27/09/2018
 */
class RecipientProfilePagerAdapter(mgr: FragmentManager, var context: Context): FragmentPagerAdapter(mgr) {

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> HistoryFragment.newInstance()
            1 -> InformationsFragment.newInstance()
        }

        throw Exception("Something went wrong with RecipientProfilePagerAdapter")
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> context.getString(R.string.recipient_history)
            1 -> context.getString(R.string.recipient_informations)
        }

        throw Exception("This tab doesn't exist")
    }
}