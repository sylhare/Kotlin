package com.demo.todo

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TodoViewModelTest {

    private val vm = TodoViewModel()

    @Test
    fun add_appendsTrimmedTodo() = runTest {
        vm.add("  buy milk  ")
        val todos = vm.todos.first()
        assertEquals(1, todos.size)
        assertEquals("buy milk", todos[0].text)
    }

    @Test
    fun add_ignoresBlank() = runTest {
        vm.add("   ")
        assertTrue(vm.todos.first().isEmpty())
    }

    @Test
    fun toggle_flipsDone() = runTest {
        vm.add("task")
        val id = vm.todos.first()[0].id
        vm.toggle(id)
        assertTrue(vm.todos.first()[0].done)
        vm.toggle(id)
        assertTrue(!vm.todos.first()[0].done)
    }

    @Test
    fun remove_deletesById() = runTest {
        vm.add("task")
        val id = vm.todos.first()[0].id
        vm.remove(id)
        assertTrue(vm.todos.first().isEmpty())
    }
}
