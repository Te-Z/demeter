package app.tez.demeter.exceptions

import java.lang.Exception

/**
 * Created by Terence Zafindratafa on 27/09/2018
 */

const val DATE_OF_BIRTH_MUST_NOT_BE_EMPTY = "DATE_OF_BIRTH_MUST_NOT_BE_EMPTY"

class RecipientException(message: String): Exception(message)