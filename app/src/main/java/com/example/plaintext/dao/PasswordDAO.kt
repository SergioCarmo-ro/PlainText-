package com.example.plaintext.dao

import android.content.ContentValues
import android.content.Context
import android.widget.Toast
import com.example.plaintext.model.Password

class PasswordDAO(private val context: Context) {
    private val db = Database(context)

    fun getList(): ArrayList<Password> {
        val list = ArrayList<Password>()
        val readableDb = db.readableDatabase
        val cursor = readableDb.rawQuery("SELECT * FROM passwords", null)
        if (cursor.moveToFirst()) {
            do {
                list.add(Password(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
                ))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    fun add(password: Password): Boolean {
        val writableDb = db.writableDatabase
        val values = ContentValues().apply {
            put("name", password.name)
            put("login", password.login)
            put("password", password.password)
            put("notes", password.notes)
        }
        val id = writableDb.insert("passwords", null, values)
        if (id != -1L) {
            Toast.makeText(context, "Senha salva!", Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }

    fun update(password: Password): Boolean {
        val writableDb = db.writableDatabase
        val values = ContentValues().apply {
            put("name", password.name)
            put("login", password.login)
            put("password", password.password)
            put("notes", password.notes)
        }
        val rows = writableDb.update("passwords", values, "id = ?", arrayOf(password.id.toString()))
        if (rows > 0) {
            Toast.makeText(context, "Senha atualizada!", Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }

    fun get(id: Int): Password? {
        val readableDb = db.readableDatabase
        val cursor = readableDb.rawQuery("SELECT * FROM passwords WHERE id = ?", arrayOf(id.toString()))
        var password: Password? = null
        if (cursor.moveToFirst()) {
            password = Password(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4)
            )
        }
        cursor.close()
        return password
    }
}
