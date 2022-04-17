package train

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

internal class TrainPathTest {

    @Test
    fun soloTrainPathTest() {
        assertEquals(listOf("trunk 0".railSection), TrainPath.from(TrainNodeTest.soloChild, listOf()))
    }

    @Test
    fun leftChildOnlyTrainPathFailTest() {
        assertThrows<NullPointerException> { TrainPath.from(TrainNodeTest.leftChildNode, listOf()) }
    }

    @Test
    fun rightTrainPathTest() {
        assertEquals(listOf("trunk 0".railSection, "toward Toronto".switch), TrainPath.from(TrainNodeTest.rightChildNode, listOf()))
    }

    @Test
    fun romeVaticanTrainPathTest() {
        assertEquals(
            listOf(
                "trunk 0".railSection,
                "toward Rome".switch,
                "trunk 2".railSection,
                "toward Vatican".switch,
                "Vatican".station,
            ), TrainPath.from(TrainNodeTest.italyLine, listOf("toward Rome", "toward Vatican"))
        )
    }

    @Test
    fun romeVaticanWithErrorTrainPathTest() {
        assertEquals(
            listOf(
                "trunk 0".railSection,
                "toward Rome".switch,
                "trunk 2".railSection,
                "toward Garbage".switch,
                RailSection.invalid,
                "toward Vatican".switch,
                "Vatican".station,
            ), TrainPath.from(TrainNodeTest.italyLine, listOf("toward Rome", "toward Garbage", "toward Vatican"))
        )
    }
}
