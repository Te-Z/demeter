package app.tez.demeter.educators

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.tez.demeter.Fake
import app.tez.demeter.R
import kotlinx.android.synthetic.main.activity_validation.*

class ValidationActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ValidationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_validation)
        this.configureToolbar()
        this.configureRecyclerView()
    }

    // -----------------
    // CONFIGURATION
    // -----------------

    private fun configureToolbar(){
        setSupportActionBar(activity_validation_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val upArrow = ContextCompat.getDrawable(this, R.drawable.ic_back)
        upArrow?.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite), PorterDuff.Mode.SRC_ATOP)
        supportActionBar?.setHomeAsUpIndicator(upArrow)
    }

    private fun configureRecyclerView(){
        this.adapter = ValidationAdapter(Fake.educatorList())
        this.recyclerView = this.activity_validation_rv
        this.recyclerView.adapter = this.adapter
        this.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
