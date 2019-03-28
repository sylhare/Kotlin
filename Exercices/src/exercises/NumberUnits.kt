package exercises

val Number.teaspoons get() = RatioQuantity(this, Unit.teaspoon)
val Number.tablespoons get() = RatioQuantity(this, Unit.tablespoon)
val Number.ounces get() = RatioQuantity(this, Unit.ounce)
val Number.cups get() = RatioQuantity(this, Unit.cup)
val Number.pints get() = RatioQuantity(this, Unit.pint)
val Number.quarts get() = RatioQuantity(this, Unit.quart)
val Number.gallons get() = RatioQuantity(this, Unit.gallon)

val Number.inches get() = RatioQuantity(this, Unit.inch)
val Number.feet get() = RatioQuantity(this, Unit.foot)
val Number.yards get() = RatioQuantity(this, Unit.yard)
val Number.chains get() = RatioQuantity(this, Unit.chain)
val Number.furlongs get() = RatioQuantity(this, Unit.furlong)
val Number.miles get() = RatioQuantity(this, Unit.mile)

val Number.celsius get() = IntervalQuantity(this, Unit.celsius)
val Number.fahrenheits get() = IntervalQuantity(this, Unit.fahrenheit)
val Number.kelvins get() = IntervalQuantity(this, Unit.kelvin)
val Number.gasmarks get() = IntervalQuantity(this, Unit.gasmark)