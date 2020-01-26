package com.tajson.postawnamilionbytajson

import android.content.Context
import android.graphics.Color
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity




class Game(val moneyToSart:Int, val arrays: ListOfElements,val context:Context) {
    var money: Int = moneyToSart
        private set
    var round: Int = 0
        private set
    var step = 100
    enum class State{
        PLAYING,WAITING_TO_NEXT_ROUND,WAITING_TO_NEW_GAME
    }
    var state:State = State.PLAYING
    private var questions: ArrayList<Question> = arrayListOf<Question>()
     var actQuestion:Question
    private  var questionsDBHelper: QuestionsDBHelper
    init{
        this.round = 1
        arrays.textView_round.text=round.toString()
        questionsDBHelper = QuestionsDBHelper(context)
        for(components in arrays.components)
        {
            components.progressBar.max = money
            components.progressBar.progress=0
        }
        questions=questionsDBHelper.readAllUsers(arrays.components)
        actQuestion=questions[round-1]
        arrays.textView_account_balance.text=money.toString()+'$'
        refresh()
    }

    fun game() {

        //ImageButtons
            for (i in 0..3) {
                actQuestion.answer[i].components.imageButtonPlus.setOnClickListener() {
                    if (state == State.PLAYING)
                        if (money - step >= 0) {
                            money -= step
                            actQuestion.answer[i].cash += step

                            refresh()
                        }

                }
        for (answer in actQuestion.answer) {
            answer.components.imageButtonMinus.setOnClickListener() {
                if (state == State.PLAYING)
                if (answer.cash - step >= 0) {
                    money += step
                    answer.cash -= step //potem bedzie to iterowanie po rundzie

                    refresh()
                }
            }
        }
    }

       //OK
        arrays.button_OK.setOnClickListener() {
            if (state == State.PLAYING && money == 0) {
                nextRound()
            } else if (state == State.WAITING_TO_NEXT_ROUND) {
                state = State.PLAYING
                arrays.button_OK.text = "OK"
                go()
            } else if (state == State.WAITING_TO_NEW_GAME) {
                newGame()
            }
        }
    }

    fun refresh()
    {
        arrays.textView_money.text=money.toString()+'$'
        arrays.textView_question.text=actQuestion.content

        for(i in 0..3)
        {
            actQuestion.answer[i].components.progressBar.setProgress(actQuestion.answer[i].cash)
            actQuestion.answer[i].components.textView_answer.text=actQuestion.answer[i].content
            actQuestion.answer[i].components.textViewMoneyForAnAnswer.text =actQuestion.answer[i].cash.toString()+'$'
        }
    }

    fun nextRound(){
        round++
        if (round<=10) {
            validation()
            state = State.WAITING_TO_NEXT_ROUND
            actQuestion = questions[round - 1]
            money = questions[round - 2].answer[0].cash

            if (money == 0) lose()
            game()
        }
        else{
            win()
        }
    }

    fun go(){
            arrays.textView_round.text = round.toString()
        for(i in 0..3)
        {
            actQuestion.answer[i].components.textView_answer.setBackgroundColor(context.getColor(R.color.dark))

        }
            refresh()
    }

    fun validation(){
        actQuestion.answer[0].components.textView_answer.setBackgroundColor(Color.GREEN)
        for(i in 1..3)
        {
            actQuestion.answer[i].components.textView_answer.setBackgroundColor(Color.RED)
        }
    }

    fun win(){
        arrays.textView_question.text="Wygrales $money$"
        state = State.WAITING_TO_NEW_GAME
    }

    fun lose()
    {
        arrays.textView_question.text="Looser"

        arrays.button_OK.text = "New game"
        state = State.WAITING_TO_NEW_GAME
    }

    fun newGame(){
        val intent = Intent(context, Menu::class.java)

        startActivity(context,intent,null)
    }

}