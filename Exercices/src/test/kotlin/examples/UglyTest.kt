package examples

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class UglyTest {

    data class User(val id: String?, val number: Int) {
        fun isValid() = this.id?.length == 10 && this.number % 2 == 0
    }

    private fun validateUser(user: User): Boolean {
        if (user.id == null) {
            val invalidUser = false
            return invalidUser
        }

        if (user.number % 2 == 0) {
            println("$user is lucky")
            if (user.id.length == 10) {
                val hasValidName: (User) -> Boolean = {testUser -> testUser.id?.length == 10}
                val useWithValidName = true
                return hasValidName(user)
            } else {
                return false
            }
        } else {
            return false
        }
    }

    @TestFactory
    fun multiplyIntTest() = listOf(
        listOf(User(null, 1), false),
        listOf(User("id", 1), false),
        listOf(User("id", 2), false),
        listOf(User("0123456789", 2), true),
        listOf(User("0123456789", 1), false),
        listOf(User(null, 2), false),
    ).map { (user, expectedResult) ->
        DynamicTest.dynamicTest("Test:  ${user as User} is valid $expectedResult") {
            assertEquals(expectedResult, validateUser(user))
            assertEquals(expectedResult, user.isValid())
        }
    }
}
