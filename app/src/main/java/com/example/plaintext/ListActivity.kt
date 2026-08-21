package com.example.plaintext

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plaintext.dao.PasswordDAO
import com.example.plaintext.model.Password
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PasswordsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        recyclerView = findViewById(R.id.list_recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inicializa o adaptador passando o contexto
        adapter = PasswordsAdapter(this)
        recyclerView.adapter = adapter

        findViewById<FloatingActionButton>(R.id.fabAddPassword).setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.update()
        adapter.notifyDataSetChanged()
    }

    // Adaptador com as características solicitadas
    class PasswordsAdapter(private val context: Context) : RecyclerView.Adapter<PasswordsViewHolder>() {
        private var passwords = ArrayList<Password>()
        private val passwordDAO = PasswordDAO()

        init {
            update()
        }

        fun update() {
            passwords = PasswordDAO.getList()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasswordsViewHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false) as ConstraintLayout
            return PasswordsViewHolder(v, context)
        }

        override fun onBindViewHolder(holder: PasswordsViewHolder, position: Int) {
            val password = passwords[position]
            holder.name.text = password.name
            holder.login.text = password.login
            holder.id = password.id
        }

        override fun getItemCount(): Int {
            return passwords.size
        }
    }

    // Holder com as características solicitadas
    class PasswordsViewHolder(v: View, private val context: Context) : 
        RecyclerView.ViewHolder(v), View.OnClickListener {
        
        val name: TextView = v.findViewById(R.id.itemName)
        val login: TextView = v.findViewById(R.id.itemLogin)
        var id: Int = -1

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val intent = Intent(context, EditActivity::class.java)
            intent.putExtra("PASSWORD_ID", id)
            context.startActivity(intent)
        }
    }
}
