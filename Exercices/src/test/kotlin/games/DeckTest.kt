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

    @Test
    internal fun `can only draw 52 cards`() {
        val deck = Deck()
        assertThrows<EmptyDeck> { repeat(53) { deck.draw() } }
    }

    @Test
    internal fun `shuffle cards`() {
        assertNotEquals(Deck().shuffle().draw(), Deck().shuffle().draw())
    }

    @Test
    internal fun `pickup the cards drawn`() {
        val deck = Deck()
        repeat(13) { deck.draw() }
        val pickedUpDeck = deck.pickup()
        assertNotEquals(deck, pickedUpDeck)
        repeat(40) { pickedUpDeck.draw() }
    }

    @Test
    internal fun `find a card`() {
        val deck = Deck()
        val aceOfSpead = Card(Value.Ace, Suit.Spade)
        var card: PlayingCard
        do {
            card = deck.draw()
        } while (card != aceOfSpead)
        assertEquals(card, aceOfSpead)
    }
}
