package com.demo.todo

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test

class TodoE2ETest {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    private val robot get() = TodoRobot(rule)

    @Test
    fun addTodos_showsThemInList() {
        robot
            .add("buy milk")
            .add("walk dog")
            .assertVisible("buy milk")
            .assertVisible("walk dog")
    }

    @Test
    fun toggleTodo_marksItDone() {
        robot
            .add("buy milk")
            .assertNotDone("buy milk")
            .toggle("buy milk")
            .assertDone("buy milk")
    }

    @Test
    fun deleteTodo_removesItFromList() {
        robot
            .add("buy milk")
            .assertVisible("buy milk")
            .delete()
            .assertGone("buy milk")
    }
}
