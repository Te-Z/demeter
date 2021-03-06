package app.tez.demeter.addRecipient

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import app.tez.demeter.R
import kotlinx.android.synthetic.main.activity_add_recipient.*

private const val TAG = "AddRecipientActivity"

class AddRecipientActivity : AppCompatActivity() {

    private var addRecipientFragment: AddRecipientFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipient)
        this.configureToolbar()
        this.configureAndShowAddRecipientFragment()
    }

    // --------------
    // CONFIGURATION
    // --------------

    private fun configureToolbar(){
        setSupportActionBar(add_recipient_activity_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val upArrow = ContextCompat.getDrawable(this, R.drawable.ic_back)
        upArrow?.setColorFilter(ContextCompat.getColor(this, R.color.colorWhite), PorterDuff.Mode.SRC_ATOP)
        supportActionBar?.setHomeAsUpIndicator(upArrow)
    }

    private fun configureAndShowAddRecipientFragment(){
        addRecipientFragment = supportFragmentManager.findFragmentById(R.id.add_recipient_activity_frame) as AddRecipientFragment?

        if(addRecipientFragment == null){
            val fragment = AddRecipientFragment.newInstance()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.add_recipient_activity_frame, fragment)
                    .commit()
        }
    }
}
