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

    @Test
    internal fun `handle Joker`() {
        val deck = Deck()
        deck.addJoker(2)
        repeat(52) { deck.draw() }
        assertEquals(deck.draw()::class, Joker::class)
        assertEquals(deck.draw()::class, Joker::class)
        assertThrows<EmptyDeck> { deck.draw() }
    }

    @Test
    internal fun `handle Jokers and shuffle`() {
        val deck = Deck().addJoker(52).shuffle()
        repeat(52) { deck.draw() }
        repeat(52) { deck.draw() }
        assertThrows<EmptyDeck> { deck.draw() }
    }
}
