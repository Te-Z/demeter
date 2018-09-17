package app.tez.demeter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.Menu
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
