package com.to_do_list.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class TodoAddUpdateDeleteActivity : AppCompatActivity(), View.OnClickListener {


    private var todoLastInsertedId: Int = 0
    private lateinit var deleteButton: Button
    private lateinit var updateButton: Button
    private lateinit var saveButton: Button
    private lateinit var etPeople: EditText
    private lateinit var etTodoText: EditText
    private lateinit var todoModel: ToDo
    var todoID = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_add_update_delete)

        todoID = intent.getIntExtra("toDoId", 0)

        if (intent.hasExtra("todoModel"))
            todoModel = intent.getParcelableExtra<ToDo>("todoModel")!!

        if (intent.hasExtra("todoLastInsertedId"))
            todoLastInsertedId = intent.getIntExtra("todoLastInsertedId", 0)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        toolbar.setTitle("Add Update Todo")

        etTodoText = findViewById(R.id.etTodoText) as EditText
        etPeople = findViewById(R.id.etPeople) as EditText
        saveButton = findViewById(R.id.btnSave)
        saveButton.setOnClickListener(this)
        updateButton = findViewById(R.id.btnUpdate)
        updateButton.setOnClickListener(this)
        deleteButton = findViewById(R.id.btnDelete)
        deleteButton.setOnClickListener(this)

        if (intent.hasExtra("todoModel")) {
            etTodoText.setText(todoModel.description)
            etPeople.setText(todoModel.assignedPeople)
            saveButton.isClickable = false
            saveButton.isEnabled = false
        } else {
            updateButton.isClickable = false
            updateButton.isEnabled = false
            deleteButton.isClickable = false
            deleteButton.isEnabled = false
        }
    }

    //Method for handling clicks on add new List view

    override fun onClick(p0: View?) {
        if (p0!!.id == R.id.btnUpdate) {

            //ToDOs Descritpiton
            val description = etTodoText.text.toString()
            val people = etPeople.text.toString()
            var completionState = 0
            val listId = intent.getIntExtra("ListID", - 1 )

            val radioGroup: RadioGroup = findViewById(R.id.ad_radioGROUP)

            val radioId = radioGroup.checkedRadioButtonId

            if (radioGroup.checkedRadioButtonId == -1) {
                Toast.makeText(
                    this,
                    "Please select a option from the buttons displayed",
                    Toast.LENGTH_SHORT
                ).show();
            } else {
                val radioButton: RadioButton = findViewById(radioId)
                if (radioButton.text.equals("Urgent")) {
                    completionState = 2
                } else if (radioButton.text.equals("Soon")) {
                    completionState = 1
                } else if (radioButton.text.equals("Completeted")) {
                    completionState = 0
                }
                intent.putExtra("update_ID", todoID)
                intent.putExtra("update_Decsription", description)
                intent.putExtra("update_People", people)
                intent.putExtra("update_CompleitonState", completionState)
                intent.putExtra("updatelistReference", listId)
                intent.putExtra("UPDATEtodo", "UPDATE")
                setResult(RESULT_OK, intent)
            }
        }
        if (p0.id == R.id.btnDelete) {

            val description = etTodoText.text.toString()
            val people = etPeople.text.toString()
            var completionState = 0
            val listId = intent.getIntExtra("ListID", - 1 )

            intent.putExtra("delete_ID", todoID)
            intent.putExtra("delete_Decsription", description)
            intent.putExtra("delete_People", people)
            intent.putExtra("delete_CompleitonState", completionState)
            intent.putExtra("updatelistReference", listId)
            intent.putExtra("todoModelDelete", "DELETE")
            setResult(RESULT_OK, intent)
        }
        finish()

    }
}