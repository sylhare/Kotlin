package com.demo.todo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class Todo(val id: Long, val text: String, val done: Boolean = false)

class TodoViewModel : ViewModel() {

    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos.asStateFlow()

    private var nextId = 0L

    fun add(text: String) {
        val trimmed = text.trim()
        if (trimmed.isEmpty()) return
        _todos.update { it + Todo(nextId++, trimmed) }
    }

    fun toggle(id: Long) = _todos.update { list ->
        list.map { if (it.id == id) it.copy(done = !it.done) else it }
    }

    fun remove(id: Long) = _todos.update { list -> list.filterNot { it.id == id } }
}
