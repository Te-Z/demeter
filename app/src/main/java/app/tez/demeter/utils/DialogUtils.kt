package app.tez.demeter.utils

import android.app.DatePickerDialog
import android.content.Context
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import app.tez.demeter.R
import java.util.*

/**
 * Created by Terence Zafindratafa on 27/09/2018
 */
class DialogUtils {

    companion object {
        fun configureDatePicker(button: Button, context: Context?){
            button.setOnClickListener {
                val calendar = Calendar.getInstance()
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val month = calendar.get(Calendar.MONTH)
                val year =  calendar.get(Calendar.YEAR)

                context?.let {
                    val picker = DatePickerDialog(it, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                        var sMonth = if(month < 10){
                            "0${month + 1}"
                        } else {
                            (month + 1).toString()
                        }

                        var sDayOfMonth = if(dayOfMonth < 10){
                            "0$dayOfMonth"
                        } else {
                            dayOfMonth.toString()
                        }
                        val dateString = "$sDayOfMonth/$sMonth/$year"
                        button.text = dateString
                    }, year, month, day)
                    picker.show()
                }
            }
        }

        fun openDateAlertDialog(context: Context?, message: Int){
            context?.let {
                val builder = AlertDialog.Builder(it)
                builder.setMessage(it.getString(message))
                        .setTitle(it.getString(R.string.error_title))
                        .setPositiveButton(it.getString(R.string.ok)) { dialog,_ ->
                            dialog.dismiss()
                        }
                val dialog = builder.create()
                dialog.show()
            }
        }
    }
}