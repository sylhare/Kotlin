package com.demo.todo

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput

class TodoRobot(private val rule: ComposeTestRule) {

    fun add(text: String) = apply {
        rule.onNodeWithTag("todo_input").performTextInput(text)
        rule.onNodeWithTag("add_button").performClick()
        rule.waitForIdle()
    }

    fun toggle(text: String) = apply {
        rule.onNodeWithText(text).performClick()
        rule.waitForIdle()
    }

    fun delete() = apply {
        rule.onNodeWithText("Delete").performClick()
        rule.waitForIdle()
    }

    fun assertVisible(text: String) = apply { rule.onNodeWithText(text).assertIsDisplayed() }

    fun assertGone(text: String) = apply { rule.onNodeWithText(text).assertDoesNotExist() }

    fun assertDone(text: String) = apply { rule.onNodeWithText(text).assertIsOn() }

    fun assertNotDone(text: String) = apply { rule.onNodeWithText(text).assertIsOff() }
}
