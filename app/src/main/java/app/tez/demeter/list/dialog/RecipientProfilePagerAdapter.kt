package app.tez.demeter.list.dialog

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import app.tez.demeter.R
import java.lang.Exception

/**
 * Created by Terence Zafindratafa on 27/09/2018
 */

private const val TAG = "RecipientProfilePager"

class RecipientProfilePagerAdapter(mgr: FragmentManager, var context: Context): FragmentPagerAdapter(mgr) {

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return HistoryFragment.newInstance()
            1 -> return InformationsFragment.newInstance()
        }

        throw Exception("Something went wrong with RecipientProfilePagerAdapter")
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        Log.d(TAG, "getPageTitle: position = $position")
        when(position){
            0 -> return context.getString(R.string.recipient_history)
            1 -> return context.getString(R.string.recipient_informations)
        }

        throw Exception("This tab doesn't exist")
    }
}