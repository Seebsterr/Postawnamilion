package com.tajson.postawnamilionbytajson

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class QuestionsDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
       // db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }


    fun readAllUsers(components: ArrayList<Components>): ArrayList<Question> {
        val users = ArrayList<Question>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM Questions ORDER BY RANDOM() LIMIT 10", null)
        } catch (e: SQLiteException) {
            return ArrayList()
        }

        var ID: Int
        var content: String
        var ans1: String
        var ans2: String
        var ans3: String
        var ans4: String
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                ID = cursor.getInt(cursor.getColumnIndex("ID"))
                content = cursor.getString(cursor.getColumnIndex("Content"))
                ans1 = cursor.getString(cursor.getColumnIndex("ans1"))
                ans2 = cursor.getString(cursor.getColumnIndex("ans2"))
                ans3 = cursor.getString(cursor.getColumnIndex("ans3"))
                ans4 = cursor.getString(cursor.getColumnIndex("ans4"))


                users.add(Question(components.shuffled() as ArrayList<Components>,content,ans1, ans2,ans3,ans4))
                cursor.moveToNext()
            }
        }

        return users
    }

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "PostawNaMilion.db"
    }

}
