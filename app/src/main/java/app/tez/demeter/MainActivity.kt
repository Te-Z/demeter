package app.tez.demeter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import app.tez.demeter.addRecipient.AddRecipientActivity
import app.tez.demeter.viewpager.PagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.configureAppBar()
        this.configureViewPager()
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

    private fun configureViewPager(){
        val pager = findViewById<ViewPager>(R.id.activity_main_viewpager)
        pager.adapter = PagerAdapter(supportFragmentManager)
        pager.currentItem = 1
    }
}
