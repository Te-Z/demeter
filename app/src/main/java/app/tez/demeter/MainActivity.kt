package app.tez.demeter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import app.tez.demeter.addRecipient.AddRecipientActivity
import app.tez.demeter.educators.ValidationActivity
import app.tez.demeter.list.ListFragment
import app.tez.demeter.services.ServicesFragment
import app.tez.demeter.statistics.StatsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navigationView: NavigationView
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.configureAppBar()
        this.configureBottomNavigationView()
        this.configureNavigationView()
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.activity_main_menu_drawer_logout -> Toast.makeText(this, "Déconnexion", Toast.LENGTH_SHORT).show()
            R.id.activity_main_menu_drawer_validations -> startActivity(Intent(this, ValidationActivity::class.java))
        }
        this.drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }

    override fun onBackPressed() {
        // 5 - Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    // ---------------
    // CONFIGURATION
    // ---------------

    private fun configureAppBar(){
        val toolbar = app_bar
        this.setSupportActionBar(toolbar)
        this.drawerLayout = activity_main_drawer
        toolbar.setNavigationOnClickListener { drawerLayout.openDrawer(GravityCompat.START) }
    }

    private fun configureNavigationView(){
        this.navigationView = activity_main_nav_view
        navigationView.setNavigationItemSelectedListener(this)
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
