package exercises

val Number.teaspoons get() = Quantity(this, Unit.teaspoon)
val Number.tablespoons get() = Quantity(this, Unit.tablespoon)
val Number.ounces get() = Quantity(this, Unit.ounce)
val Number.cups get() = Quantity(this, Unit.cup)
val Number.pints get() = Quantity(this, Unit.pint)
val Number.quarts get() = Quantity(this, Unit.quart)
val Number.gallons get() = Quantity(this, Unit.gallon)

val Number.inches get() = Quantity(this, Unit.inch)
val Number.feet get() = Quantity(this, Unit.foot)
val Number.yards get() = Quantity(this, Unit.yard)
val Number.chains get() = Quantity(this, Unit.chain)
val Number.furlongs get() = Quantity(this, Unit.furlong)
val Number.miles get() = Quantity(this, Unit.mile)

val Number.celsius get() = Quantity(this, Unit.celsius)
val Number.fahrenheits get() = Quantity(this, Unit.fahrenheit)
val Number.kelvins get() = Quantity(this, Unit.kelvin)
val Number.gasmarks get() = Quantity(this, Unit.gasmark)