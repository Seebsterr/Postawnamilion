package com.tajson.postawnamilionbytajson

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.net.URL

class Download(val context: Context) {
    init{

        download("http://alphabyte.pl/postawnamilion/questions.db",context.getDatabasePath("PostawNaMilion.db").toString())
    }


   private fun download(link: String, path: String) {
        val input = URL(link).openStream()
        val output = FileOutputStream(File(path))
        input.use { _ ->
            output.use { _ ->
                input.copyTo(output)
            }
        }
    }
}