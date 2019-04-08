package quantity.Metric

// Enum have no behaviours their data is only stolen away without control
// Breaks encapsulation

enum class Metrics(val value: Int) {
    TEASPOON(1),
    TABLESPOON(3),
    OUNCE(6),
    CUP(48),
    PINT(96),
    QUART(192),
    GALLON(768)

}