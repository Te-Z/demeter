package app.tez.demeter.exceptions

import java.lang.Exception

/**
 * Created by Terence Zafindratafa on 25/09/2018
 */
const val EDIT_TEXT_EMPTY = "Edit Text empty"
class ViewException(message: String): Exception(message)