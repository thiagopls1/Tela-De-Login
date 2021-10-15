package com.example.loginscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import com.example.loginscreen.databinding.ActivityRegisterBinding
import java.lang.Exception

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,"app-database"
        ).allowMainThreadQueries().build()

        binding.buttonConfirm.setOnClickListener {

            val username = binding.textUsername.text.toString()
            val email = binding.textEmail.text.toString()
            val password = binding.textPassword.text.toString()
            val confirmPassword = binding.textConfirmPassword.text.toString()

            val users = db.userDao().getAllUsers()
            val emails = db.userDao().getAllEmails()

            if( username == "" || email == "" || password == "" || confirmPassword == "" ){
                showMessage("Preencha os campos corretamente!")
            } else {
                if (users.contains(username)) {
                    showMessage("Nome de usuário já existente")
                } else {
                    if (emails.contains(email)) {
                        showMessage("Email já existente")
                    } else {
                        if (password == confirmPassword) {
                            try {
                                db.userDao().insertAll(
                                    User(
                                        userId = 0,
                                        username = username,
                                        email = email,
                                        password = password
                                    )
                                )
                                showMessage("Dados cadastrados com sucesso")
                            } catch (e: Exception) {
                                showMessage(e.toString())
                            }
                            binding.textUsername.setText("")
                            binding.textEmail.setText("")
                            binding.textPassword.setText("")
                            binding.textConfirmPassword.setText("")
                        } else {
                            showMessage("Os campos 'senha' e 'confirmar senha' não coincidem")
                        }
                    }
                }
            }
        }

        binding.buttonReturn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
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