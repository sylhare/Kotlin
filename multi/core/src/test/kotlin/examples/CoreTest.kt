package examples

import examples.Core.Companion.world
import examples.mocks.CoreMock.Companion.mockHello
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CoreTest {

  @Test
  internal fun coreMethodTest() {
    assertEquals("world", world())
  }

  @Test
  internal fun coreMethodMockTest() {
    assertEquals("hello world", "${mockHello()} ${world()}")
  }
}