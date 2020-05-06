package examples

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CoreTest {

  @Test
  internal fun coreMethodTest() {
    assertEquals("world", world())
  }
}