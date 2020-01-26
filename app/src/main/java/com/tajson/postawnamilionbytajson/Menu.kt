package com.tajson.postawnamilionbytajson

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_menu.*
import kotlin.system.exitProcess

class Menu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        imageView_uptade.setOnClickListener(){
            try {
                val download: Download = Download(this)
                Toast.makeText(this,"Baza zaktualizowana", Toast.LENGTH_SHORT).show()
            }
            catch(ex:Exception){
                Toast.makeText(this,"Włącz internet, aby zaktualizować bazę pytań", Toast.LENGTH_SHORT).show()
                if(!getDatabasePath("PostawNaMilion.db").exists())
                {
                    exitProcess(1)
                }

            }
        }

        button_new_game.setOnClickListener(){
            val intent = Intent(this, PostawNaMilion::class.java)

            ContextCompat.startActivity(this, intent, null)
        }
    }
}
