package com.example.plaintext

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.plaintext.dao.PasswordDAO
import com.example.plaintext.model.Password
import com.google.android.material.textfield.TextInputEditText

class EditActivity : AppCompatActivity() {

    private lateinit var editTextName: TextInputEditText
    private lateinit var editTextLogin: TextInputEditText
    private lateinit var editTextPassword: TextInputEditText
    private lateinit var editTextNotes: TextInputEditText
    private lateinit var buttonSave: Button

    private var currentPasswordId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.edit_root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editTextName = findViewById(R.id.editTextName)
        editTextLogin = findViewById(R.id.editTextLogin)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextNotes = findViewById(R.id.editTextNotes)
        buttonSave = findViewById(R.id.buttonSave)

        currentPasswordId = intent.getIntExtra("PASSWORD_ID", -1)

        if (currentPasswordId != -1) {
            val password = PasswordDAO.get(currentPasswordId)
            password?.let {
                editTextName.setText(it.name)
                editTextLogin.setText(it.login)
                editTextPassword.setText(it.password)
                editTextNotes.setText(it.notes)
            }
        }

        buttonSave.setOnClickListener {
            savePassword()
        }
    }

    private fun savePassword() {
        val name = editTextName.text.toString()
        val login = editTextLogin.text.toString()
        val passwordText = editTextPassword.text.toString()
        val notes = editTextNotes.text.toString()

        if (name.isEmpty() || login.isEmpty() || passwordText.isEmpty()) {
            Toast.makeText(this, "Preencha os campos obrigatórios", Toast.LENGTH_SHORT).show()
            return
        }

        if (currentPasswordId == -1) {
            val newPassword = Password(name = name, login = login, password = passwordText, notes = notes)
            PasswordDAO.add(this, newPassword)
        } else {
            val updatedPassword = Password(id = currentPasswordId, name = name, login = login, password = passwordText, notes = notes)
            PasswordDAO.update(this, updatedPassword)
        }
        finish()
    }
}
