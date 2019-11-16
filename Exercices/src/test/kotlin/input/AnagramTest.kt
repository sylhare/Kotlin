package input

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AnagramTest {

    @Test
    fun anagramsAreFilteredOut(){
        print(Anagram.filter(listOf("salut", "talus")))
        assertEquals(1, Anagram.filter(listOf("salut", "talus")).size)
        assertEquals(5, Anagram.filter(listOf("nanana", "nana", "panama", "bac", "ananas", "cab")).size)
        assertEquals(4, Anagram.filter(listOf("cat", "tac", "hello", "bonjour", "allo", "loal")).size)
    }
    @Test
    fun isAnagramTest(){
        assertTrue("cat".isAnagram("tac"))
        assertFalse("nanan".isAnagram("nana"))
    }
}