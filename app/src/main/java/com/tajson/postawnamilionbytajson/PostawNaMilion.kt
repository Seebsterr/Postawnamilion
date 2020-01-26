package com.tajson.postawnamilionbytajson
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_answer.*
import android.os.StrictMode
import android.widget.Toast
import kotlin.system.exitProcess


class PostawNaMilion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        try {
            val download: Download = Download(this)
            Toast.makeText(this,"Baza zaktualizowana",Toast.LENGTH_SHORT).show()
        }
        catch(ex:Exception){
            Toast.makeText(this,"Włącz internet, aby zaktualizować bazę pytań",Toast.LENGTH_SHORT).show()
            if(!getDatabasePath("PostawNaMilion.db").exists())
            {
                exitProcess(1)
            }

        }

        val componentsMain:ArrayList<Components> = arrayListOf()
        componentsMain.add(Components(imageButton_plus_answerA,imageButton_minus_answerA,textView_answerA,progressBarA,textView_money_for_an_answerA))
        componentsMain.add(Components(imageButton_plus_answerB,imageButton_minus_answerB,textView_answerB,progressBarB,textView_money_for_an_answerB))
        componentsMain.add(Components(imageButton_plus_answerC,imageButton_minus_answerC,textView_answerC,progressBarC,textView_money_for_an_answerC))
        componentsMain.add(Components(imageButton_plus_answerD,imageButton_minus_answerD,textView_answerD,progressBarD,textView_money_for_an_answerD))

        val listOfElements: ListOfElements = ListOfElements(componentsMain,
        textView_account_balance,
        textView_question,
        button_OK, textView_money,textView_round)

        val game: Game = Game(1000,listOfElements,this)
        game.game()
    }
}
