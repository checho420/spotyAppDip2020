package com.checho.spotyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.checho.spotyapp.models.UserModel
import com.checho.spotyapp.repository.UserRepository
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        button.setOnClickListener {
            val userModel = UserModel("Sergio", "checho", "chechsk8@gmail.com", "420")

            createThreadToCreateUser(userModel)
        }
    }

    private fun createThreadToCreateUser(userModel:UserModel) {
        val thread = Thread(Runnable {
            createUserFromRepository(userModel)



        })
        thread.start()
    }

    private fun createUserFromRepository(userModel: UserModel) {
        try {
            val repository = UserRepository()
            val result = repository.createUser(userModel)
            showUser(userModel)
        } catch (e: Exception) {
            runOnUiThread {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun showUser(usermodel: UserModel) {
        runOnUiThread {
            Toast.makeText(this, "${usermodel.name} ${usermodel._id}", Toast.LENGTH_LONG).show()
        }
    }


}
