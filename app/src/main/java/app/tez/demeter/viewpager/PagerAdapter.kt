package app.tez.demeter.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import app.tez.demeter.list.ListFragment
import app.tez.demeter.services.ServicesFragment
import app.tez.demeter.StatsFragment

/**
 * Created by Terence Zafindratafa on 11/09/2018
 */
class PagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getItem(p0: Int): Fragment {
        when(p0){
            0 -> return StatsFragment.newInstance()
            1 -> return ListFragment.newInstance()
            2 -> return ServicesFragment.newInstance()
        }

        throw Exception("This item is not possible !")
    }

    override fun getCount(): Int = 3
}