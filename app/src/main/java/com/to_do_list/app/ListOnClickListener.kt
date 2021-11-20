package com.to_do_list.app

import com.to_do_list.app.common.entities.ToDoList

interface ListOnClickListener {
    fun onListClickListener(data: ToDoList)

    fun onListLongClickListener(data: ToDoList)
}