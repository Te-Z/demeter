package app.tez.demeter

import app.tez.demeter.models.Recipient

/**
 * Created by Terence Zafindratafa on 17/09/2018
 */
class FakeUsers {

    companion object {
        fun setTestList(testList: MutableList<Recipient>){
            val recipient1 = Recipient("Tez", "Zet", "1990-01-14", 65, null)
            val recipient2 = Recipient("Baz", "Zab", "1990-01-04", 15, null)
            val recipient3 = Recipient("Piou", "Ouip", "1992-11-14", 85, null)
            val recipient4 = Recipient("Bbg", "Gbb", "1993-05-14", 25, null)
            val recipient5 = Recipient("Gez", "Zeg", "1950-01-25", 45, null)
            val recipient6 = Recipient("Chaz", "Zach", "1970-01-14", 95, null)
            val recipient7 = Recipient("Milo", "Olim", "2014-01-14", 15, null)
            val recipient8 = Recipient("Leia", "Tez", "2015-08-31", 5, null)
            val recipient9= Recipient("Fury", "Gan", "1850-01-14", 50, null)
            val recipient0 = Recipient("Sam", "Ouraï", "1900-01-14", 70, null)

            testList.add(recipient0)
            testList.add(recipient1)
            testList.add(recipient2)
            testList.add(recipient3)
            testList.add(recipient4)
            testList.add(recipient5)
            testList.add(recipient6)
            testList.add(recipient7)
            testList.add(recipient8)
            testList.add(recipient9)
        }
    }
}