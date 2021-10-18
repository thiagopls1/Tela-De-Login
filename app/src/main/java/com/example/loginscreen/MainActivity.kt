package com.example.loginscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import com.example.loginscreen.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,"app-database"
        ).allowMainThreadQueries().build()

        binding.buttonLogin.setOnClickListener {

            val usernameInput = binding.textUsername.text.toString()
            val passwordInput = binding.textPassword.text.toString()

            val userAuthenticated = db.userDao().authUser(usernameInput, passwordInput)

            if(!userAuthenticated){
                showMessage("Nome de Usuário ou Senha inválidos")
            } else{
                startActivity(Intent(this, UserAuthenticatedActivity::class.java))
            }
        }

        binding.buttonSignUp.setOnClickListener {
         startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun showMessage(str: String){
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Aviso")
        dialog.setMessage(str)
        dialog.setNeutralButton("Ok", null)
        dialog.show()
    }
}