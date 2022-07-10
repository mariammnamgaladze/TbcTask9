package com.example.tbctask9

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tbctask9.MainActivity.Companion.PERSON_ID
import com.example.tbctask9.databinding.ActivityUsersBinding

class UsersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)


        with(binding){
            addUserBtn.setOnClickListener {
                if(registerPersonName.text.toString().isEmpty())
                    return@setOnClickListener
                if(registerPersonSurname.text.toString().isEmpty())
                    return@setOnClickListener
                if(registerEmail.text.toString().isEmpty())
                    return@setOnClickListener

                val person = Person(
                    name = registerPersonName.text.toString(),
                    surname = registerPersonSurname.text.toString(),
                    email = registerEmail.text.toString()
                )
                val intent = Intent()
                intent.putExtra(PERSON_ID,person)

                setResult(Activity.RESULT_OK,intent)
                onBackPressed()
            }
        }

    }

    }