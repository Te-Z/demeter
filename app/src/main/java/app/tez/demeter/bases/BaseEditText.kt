package app.tez.demeter.bases

import android.content.Context
import android.util.AttributeSet
import app.tez.demeter.R
import app.tez.demeter.exceptions.EDIT_TEXT_EMPTY
import app.tez.demeter.exceptions.ViewException
import com.google.android.material.textfield.TextInputEditText

/**
 * Created by Terence Zafindratafa on 25/09/2018
 */
class BaseEditText: TextInputEditText {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun checkIfEmpty(){
        if(this.length() == 0){
            this.error = context.getString(R.string.empty_field)
            throw ViewException(EDIT_TEXT_EMPTY)
        }
    }
}