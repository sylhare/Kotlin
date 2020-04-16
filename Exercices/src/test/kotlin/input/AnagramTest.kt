package input

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class AnagramTest {

    @Test
    fun anagramsAreFilteredOut() {
        print(Anagram.filter(listOf("salut", "talus")))
        assertEquals(1, Anagram.filter(listOf("salut", "talus")).size)
        assertEquals(5, Anagram.filter(listOf("nanana", "nana", "panama", "bac", "ananas", "cab")).size)
        assertEquals(4, Anagram.filter(listOf("cat", "tac", "hello", "bonjour", "allo", "loal")).size)
    }

    @Test
    fun isAnagramTest() {
        assertTrue("cat".isAnagram("tac"))
        assertFalse("nanan".isAnagram("nana"))
    }

    @Test
    fun anagramsAreFilteredSameOutput() {
        var words = listOf("cat", "tac", "hello", "bonjour", "allo", "loal")
        assertEquals(listOf("hello", "bonjour"), words.removeAllAnagrams())
        assertEquals(listOf("tac", "hello", "bonjour", "loal"), words.removeAnagramDuplicates())
        assertEquals(listOf("cat", "hello", "bonjour", "allo"), words.filterAnagrams())
    }
}