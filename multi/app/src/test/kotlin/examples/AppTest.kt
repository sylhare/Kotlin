package examples

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class AppTest {

  @Test
  internal fun appMethodTest() {
    assertEquals("examples.hello world", hello())
  }
}