package com.to_do_list.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class AddToDO : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_todo_screen)


        val button: Button = findViewById(R.id.btnApply) as Button

        button.setOnClickListener {

            val description : EditText = findViewById(R.id.etDescription)
            val completionState : EditText = findViewById(R.id.etCompletionState)
            val people : EditText = findViewById(R.id.etPeople)

            if(description.text.toString().equals("") || completionState.text.toString().equals("") || people.text.toString().equals("")){
                if(description.text.toString().equals("")){
                    description.setError("This field can not be blank")
                }
                if(completionState.text.toString().equals("")){
                    description.setError("This field can not be blank")
                }
                if(people.text.toString().equals("")){
                    people.setError("This field can not be blank")
                }
            }else{
                Intent(this, ListOfToDos::class.java).also {
                    it.putExtra("EXTRA_DESCRIPTION", description.text.toString())
                    it.putExtra("EXTRA_COMPLETIONSTATE", completionState.text.toString().toInt())
                    it.putExtra("EXTRA_PEOPLE", people.text.toString())
                    setResult(RESULT_OK, it)
                    startActivity(it)
                    finish()
                }
            }
        }
    }
}