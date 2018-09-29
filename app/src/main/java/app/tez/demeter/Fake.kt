package app.tez.demeter

import android.content.Context
import app.tez.demeter.models.ActionItem
import app.tez.demeter.models.DonationItem
import app.tez.demeter.models.Educator
import app.tez.demeter.models.Recipient

/**
 * Created by Terence Zafindratafa on 17/09/2018
 */
class Fake {

    companion object {
        fun recipientList(testList: MutableList<Recipient>){
            val recipient1 = Recipient("Tez", "Zet", "1990-01-14", "M", 65, null, "métro république", false, null, "1990-01-14", "Français")
            val recipient2 = Recipient("Baz", "Zab", "1990-01-04", "M", 15, null, "circuit de Lesquin", true, null, "1992-11-14", "Français")
            val recipient3 = Recipient("Piou", "Ouip", "1992-11-14", "F", 85, null, "Pont de bois", false, "Transpole", "1850-01-14", "Français")
            val recipient4 = Recipient("Bbg", "Gbb", "1993-05-14", "F", 25, null, "Valenciennes", false, "SNCF", "1850-01-14", "Français")
            val recipient5 = Recipient("Gez", "Zeg", "1950-01-25", "F", 45, null, "Rue de la clé à Lille", false, null, "1992-11-14", "Français")
            val recipient6 = Recipient("Chaz", "Zach", "1970-01-14", "M", 95, null, "Internet", false, "C3PO", "1993-05-14", "Français")
            val recipient7 = Recipient("Milo", "Olim", "2014-01-14", "A", 15, null, "10 rue des Tours Lille", true, "Autre", "1992-11-14", "Français")
            val recipient8 = Recipient("Leia", "Tez", "2015-08-31", "A", 5, null, "Carrefour rue de Douai Lille", false, "Autre", "1993-05-14", "Français")
            val recipient9= Recipient("Fury", "Gan", "1850-01-14", "M", 50, null, "Place de la république", false, "Transpole", "1993-05-14", "Français")
            val recipient0 = Recipient("Sam", "Ouraï", "1900-01-14", "F", 70, null, "Roubaix", true, null, "1850-01-14", "Français")

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

        fun donationItemList(testList: MutableList<DonationItem>, context: Context){
            val item1 = DonationItem(context.getString(R.string.dnts_belt), (1..50).shuffled().first())
            val item2 = DonationItem(context.getString(R.string.dnts_cap), (1..50).shuffled().first())
            val item3 = DonationItem(context.getString(R.string.dnts_coat), (1..50).shuffled().first())
            val item4 = DonationItem(context.getString(R.string.dnts_dental_kit), (1..50).shuffled().first())
            val item5 = DonationItem(context.getString(R.string.dnts_gloves), (1..50).shuffled().first())
            val item6 = DonationItem(context.getString(R.string.dnts_pet_shop), (1..50).shuffled().first())
            val item7 = DonationItem(context.getString(R.string.dnts_pullover), (1..50).shuffled().first())
            val item8 = DonationItem(context.getString(R.string.dnts_scarf), (1..50).shuffled().first())
            val item9 = DonationItem(context.getString(R.string.dnts_shaving_kit), (1..50).shuffled().first())
            val item10 = DonationItem(context.getString(R.string.dnts_shoes), (1..50).shuffled().first())
            val item11 = DonationItem(context.getString(R.string.dnts_socks), (1..50).shuffled().first())
            val item12 = DonationItem(context.getString(R.string.dnts_suitcase), (1..50).shuffled().first())
            val item13 = DonationItem(context.getString(R.string.dnts_trousers), (1..50).shuffled().first())
            val item14 = DonationItem(context.getString(R.string.dnts_tshirt), (1..50).shuffled().first())
            val item15 = DonationItem(context.getString(R.string.dnts_underpants), (1..50).shuffled().first())

            testList.add(item1)
            testList.add(item2)
            testList.add(item3)
            testList.add(item4)
            testList.add(item5)
            testList.add(item6)
            testList.add(item7)
            testList.add(item8)
            testList.add(item9)
            testList.add(item10)
            testList.add(item11)
            testList.add(item12)
            testList.add(item13)
            testList.add(item14)
            testList.add(item15)


        }

        fun educator(): Educator = Educator("Tez")

        fun ActionItemList(): List<ActionItem>{
            val recipient1 = Recipient("Tez", "Zet", "1990-01-14", "M", 65, null, "métro république", false, null, "1990-01-14", "Français")
            val recipient2 = Recipient("Baz", "Zab", "1990-01-04", "M", 15, null, "circuit de Lesquin", true, null, "1992-11-14", "Français")
            val recipient3 = Recipient("Piou", "Ouip", "1992-11-14", "F", 85, null, "Pont de bois", false, "Transpole", "1850-01-14", "Français")

            val action1 = ActionItem(recipient1, educator().username, "Entretien", "Prise de contact", "14/01/2018", "coucou")
            val action2 = ActionItem(recipient1, educator().username, "Entretien", "Prise de contact", "15/03/2018", "coucou")
            val action3 = ActionItem(recipient1, educator().username, "Entretien", "Prise de contact", "12/08/2018","coucou")
            var action4 = ActionItem(recipient1, educator().username, "Entretien", "Prise de contact", "15/03/2018", "coucou")

            return listOf(action1, action2, action3, action4)
        }
    }
}