package games

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class DeckTest {

    @Test
    internal fun `draw a card`() {
        assertEquals(Deck().draw()::class, Card::class)
    }

    @Test
    internal fun `drawn cards are different`() {
        val deck = Deck();
        assertNotEquals(deck.draw(), deck.draw())
    }
}
