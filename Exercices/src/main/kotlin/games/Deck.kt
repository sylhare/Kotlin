package games

class Deck {
    private var cards: MutableList<PlayingCard> =
        Suit.values().flatMap { suit -> Value.values().map { value -> Card(value, suit) } }.toMutableList()

    fun draw(): PlayingCard = try {
        cards.removeFirst()
    } catch (e: NoSuchElementException) {
        throw EmptyDeck()
    }

    fun shuffle(): Deck = this.also { this.cards.shuffle() }
    fun pickup(): Deck = Deck().shuffle()
}
