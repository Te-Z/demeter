package app.tez.demeter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import app.tez.demeter.addRecipient.AddRecipientActivity
import app.tez.demeter.list.ListFragment
import app.tez.demeter.services.ServicesFragment
import app.tez.demeter.statistics.StatsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.configureAppBar()
        this.configureBottomNavigationView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.dmt_toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.menu_search -> Toast.makeText(this, "onOptionsItemSelected: search", Toast.LENGTH_SHORT).show()
            R.id.menu_add -> startActivity(Intent(this, AddRecipientActivity::class.java))
        }
        return true
    }

    // ---------------
    // CONFIGURATION
    // ---------------

    private fun configureAppBar(){
        this.setSupportActionBar(app_bar)
    }

    // -----------
    // UI
    // -----------

    private fun configureBottomNavigationView(){
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.activity_main_bottom_navigation_view)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.bottom_nav_stats -> replaceFragment(StatsFragment.newInstance())
                R.id.bottom_nav_list -> replaceFragment(ListFragment.newInstance())
                R.id.bottom_nav_services -> replaceFragment(ServicesFragment.newInstance())
            }
            true
        }
        bottomNavigationView.selectedItemId = R.id.bottom_nav_stats
    }

    private fun replaceFragment(fragment: Fragment){
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.activity_main_frame_layout, fragment)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        ft.commit()
    }
}
