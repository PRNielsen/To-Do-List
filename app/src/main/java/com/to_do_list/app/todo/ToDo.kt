package com.to_do_list.app.todo

data class ToDo(
    val description: String?,
    val completionState: Int = 0,
    val assignedPeople: String?
)