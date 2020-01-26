package com.tajson.postawnamilionbytajson

class Question constructor(components: ArrayList<Components>, var content:String="",
                           val ans1:String,val ans2:String,val ans3:String,val ans4:String) {

   // private
    var answer: ArrayList<Answer> = arrayListOf<Answer>()
    init {
        answer.add(Answer(ans1,components[0]))
        answer.add(Answer(ans2,components[1]))
        answer.add(Answer(ans3,components[2]))
        answer.add(Answer(ans4,components[3]))
    }
}