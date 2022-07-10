package com.example.tbctask9

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tbctask9.databinding.CardLayoutBinding

class RecyclerAdapter(private val listener: AdapterEventListener) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    private var personList = emptyList<Person>()

    class MyViewHolder(val binding: CardLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CardLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return personList.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder.binding) {
            val person = personList[position]
            fullName.text = person.getFullName()
            email.text = person.email
            editBtn.setOnClickListener {
                listener.onEdit(person)
            }
            deleteBtn.setOnClickListener{
                listener.onRemove(person)
            }
        }
    }


    fun setData(newPersonList: List<Person>) {
        personList = newPersonList
        notifyDataSetChanged()
    }

    fun updateItem(pos: Int){
        notifyItemChanged(pos)
    }

}

interface AdapterEventListener {
    fun onRemove(person: Person)
    fun onEdit(person: Person)
}
