package app.tez.demeter.models

/**
 * Created by Terence Zafindratafa on 29/09/2018
 */
data class ActionItem(var recipient: Recipient,
                      var educatorName: String,
                      var type: String,
                      var action: String,
                      var date: String,
                      var comment: String? = null)