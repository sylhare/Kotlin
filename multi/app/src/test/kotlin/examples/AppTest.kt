package examples

import examples.App.Companion.hello
import org.junit.jupiter.api.Assertions.assertEquals
import examples.mocks.CoreMock.Companion.mockHello
import org.junit.jupiter.api.Test

internal class AppTest {

  @Test
  internal fun appMethodTest() {
    assertEquals("hello world", hello())
  }

  @Test
  internal fun mockTest() {
    assertEquals("hello", mockHello())
  }
}