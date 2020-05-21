package com.example.eventhunt.utils

/*
*
* Class responsible for generating quotes to engage customers while loading UI
* */
object Thoughts {

    fun displayThoughts():String{
        val list = arrayListOf<String>()
        list.apply {
            add("We don't remember days....But rather the Moments")
            add("Book it before you regret.....")
            add("Lifes' too short to be BORED....")
            add("We add the 'special' to your next event....")
            add("Plan up for your next Event....")
        }
        list.shuffle()
        return list.get(0)
    }

}