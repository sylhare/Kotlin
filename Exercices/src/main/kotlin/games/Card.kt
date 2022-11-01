package games

interface PlayingCard
data class Card(val value: Value, val suit: Suit): PlayingCard
class Joker: PlayingCard

class EmptyDeck: Exception("There are no more cards to draw")

enum class Suit {
    Diamond,
    Spade,
    Club,
    Heart
}

enum class Value {
    Ace,
    Two,
    Three,
    Four,
    Five,
    Six,
    Seven,
    Eight,
    Nine,
    Ten,
    Jack,
    Queen,
    King,
}
