package com.example.plaintext.dao

import android.content.Context
import android.widget.Toast
import com.example.plaintext.model.Password

class PasswordDAO {
    companion object {
        private val passwordsList = ArrayList<Password>()

        fun getList(): ArrayList<Password> {
            if (passwordsList.size == 0) {
                passwordsList.add(Password(0, "Facebook", "dovahkiin@gmail.com", "FusRoDah123", ""))
                passwordsList.add(Password(1, "GMail", "dovahkiin", "Teste123", ""))
                passwordsList.add(Password(2, "IComp", "dfrd@icomp.ufam.edu.br", "Java4242", "Mudar a senha!"))
                passwordsList.add(Password(3, "Steam", "dovahkiin", "FusRoDah123", "Conta do Brasil"))
            }
            return passwordsList
        }

        fun add(context: Context, password: Password): Boolean {
            password.id = passwordsList.size
            passwordsList.add(password)
            Toast.makeText(context, "Senha salva!", Toast.LENGTH_SHORT).show()
            return true
        }

        fun update(context: Context, password: Password): Boolean {
            passwordsList[password.id] = password
            Toast.makeText(context, "Senha atualizada!", Toast.LENGTH_SHORT).show()
            return true
        }

        fun get(id: Int): Password {
            return passwordsList[id]
        }
    }
}
