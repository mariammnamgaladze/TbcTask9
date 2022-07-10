package com.example.tbctask9

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tbctask9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AdapterEventListener {
    private lateinit var binding: ActivityMainBinding

    private val adapter by lazy { RecyclerAdapter(this) }

    private val personList = mutableListOf<Person>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        personList.addAll(
            listOf(
                Person("Mariam", "Namgaladze", "mariamnamgaladze7@gmail.com"),
                Person(
                    "Ana", "Namgaladze", "ananamgaladze21@gmail.com"
                )
            )
        )

        adapter.setData(personList)

        binding.addUserBtn1.setOnClickListener {
            val intent = Intent(this, UsersActivity::class.java)
            startActivityForResult(intent,PERSON_CREATE_CODE)
        }
    }

    override fun onRemove(person: Person) {
        personList.remove(person)
        adapter.setData(personList)
    }

    override fun onEdit(person: Person) {
        val intent = Intent(this, UserActivity::class.java)
        intent.putExtra(PERSON_ID,person)
        startActivityForResult(intent, EDIT_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                EDIT_CODE ->{
                    val updatedPerson = data!!.getParcelableExtra<Person>(PERSON_ID)!!
                    var personIndex:Int = -1
                    personList.forEachIndexed { index, person ->
                        if (person.id == updatedPerson.id) {
                            personIndex = index
                        }
                    }
                    personList.removeAt(personIndex)
                    personList.add(personIndex,updatedPerson)
                    adapter.updateItem(personIndex)
                }
                PERSON_CREATE_CODE ->{
                    val person = data!!.getParcelableExtra<Person>(PERSON_ID)!!
                    personList.add(0,person)
                    adapter.updateItem(0)
                }
            }
        }

    }
    companion object {
        const val PERSON_ID = "person"
        const val EDIT_CODE = 99
        const val PERSON_CREATE_CODE = 88
    }
}