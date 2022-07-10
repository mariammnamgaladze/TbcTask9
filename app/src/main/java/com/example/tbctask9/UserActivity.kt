package com.example.tbctask9

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import com.example.tbctask9.MainActivity.Companion.EDIT_CODE
import com.example.tbctask9.MainActivity.Companion.PERSON_ID
import com.example.tbctask9.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val person = intent.getParcelableExtra<Person>(PERSON_ID)!!
        val editedPerson = person.copy()

        with(binding) {
            updatePersonName.setText(person.name)
            updatePersonSurname.setText(person.surname)
            updateBtn.setOnClickListener {
                editedPerson.name = updatePersonName.text.toString()
                editedPerson.surname = updatePersonSurname.text.toString()
                val intent = Intent()
                intent.putExtra(PERSON_ID, editedPerson)

                if (person != editedPerson) {
                    setResult(Activity.RESULT_OK, intent)
                    onBackPressed()
                }
            }
        }
    }

}
