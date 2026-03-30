# Contributing

## Code Style

### Encapsulation

Make everything as private as possible. Default to `private`, step up to `internal` only when needed within the module, and expose `public` only at intentional API boundaries.

```kotlin
// Wrong — exposes internals
val connections = mutableListOf<Connection>()

// Right — hide the data, expose only behavior
private val connections = mutableListOf<Connection>()
```

Control instantiation through private constructors and companion objects, so the class itself decides what is valid:

```kotlin
internal class Unit {
    private constructor() { ... }
    private constructor(value: Int, unit: Unit) { ... }

    internal companion object {
        val teaspoon = Unit()
        val tablespoon = Unit(3, teaspoon)
    }
}
```

This seals the implementation: callers use named constants, the class itself decides what is valid.

### No Getters or Setters

Avoid `getSomething()` / `setSomething()` patterns. Use:
- A **property** (noun) when you want to expose a value
- A **method with a verb** when you want to express an action or transformation

```kotlin
// Wrong
fun getConnections(): List<Connection>
fun setCost(value: Double)

// Right
val connections: List<Connection>        // computed property
infix fun cost(amount: Number): ...      // action that sets up a builder
```

### No Duplication

When the same operation appears in multiple places, extract it. Extension functions and companion objects are both good homes for shared logic:

```kotlin
// Shared logic lives once, extension function provides ergonomic access
internal companion object {
    fun totalCost(connections: List<Connection>) = ...
}

internal fun List<Connection>.totalCost() = Connection.totalCost(this)
```

### Strategy Pattern Over Conditionals

Use function types and `typealias` to name variable behaviour rather than branching on type:

```kotlin
internal typealias CostStrategy = (Path) -> Number

private fun path(destination: Node, strategy: CostStrategy) =
    paths(destination).minByOrNull { strategy(it).toDouble() }
```

Callers pass `Path::hopCount` or `Path::cost` — the algorithm stays the same, only the criterion varies.

### Infix for Domain Language

When two objects interact in a way that reads naturally as a sentence, prefer `infix`:

```kotlin
B cost 5 to A          // graph setup
B canReach C           // query
it prepend this        // mutation
unit isCompatibleWith otherUnit
```

This is not cosmetic — it makes the code read like the domain, which reduces misuse and aids review.

### Builder Pattern for Chained Configuration

Return the receiver (or the next relevant object) to enable chaining without a dedicated builder class:

```kotlin
class ConnectionBuilder(private val cost: Double, private val connections: MutableList<Connection>) {
    infix fun to(node: Node): Node {
        connections.add(Connection(node, cost))
        return node   // enables: B cost 6 to C cost 1 to D
    }
}
```

### Inheritance Only for Real Subtypes

`RatioQuantity` extends `IntervalQuantity` because ratio quantities (volumes, distances) are a strict subtype: they support arithmetic, interval quantities (temperatures) do not.

```kotlin
// IntervalQuantity: equality and conversion only
// RatioQuantity: adds arithmetic operators
operator fun plus(other: RatioQuantity): RatioQuantity
operator fun minus(other: RatioQuantity) = this + -other
operator fun unaryMinus() = RatioQuantity(-amount, unit)
```

Do not use inheritance to share implementation — use extension functions or composition instead.

### Equals, HashCode, and Floating-Point Tolerance

When overriding `equals`, use a delta for floating-point comparisons, and always keep `hashCode` consistent:

```kotlin
override fun equals(other: Any?) =
    this === other ||
    other is IntervalQuantity && this.unit isCompatibleWith other.unit &&
    (convertedAmount(other) - this.amount).absoluteValue < delta

override fun hashCode() = unit.hashCode(this.amount)
```

The `hashCode` must normalise to a base unit so equal quantities in different units hash identically.

## What Is Done

- **No inline comments**: code is written to be self-explanatory through naming, infix functions, and expressive types. Comments are not used to describe what the code does.
- **No getters / setters**: properties and verb-named methods only.
- **Restricted visibility**: `private` by default, `internal` for package-level sharing, `public` only at the API surface.
- **Private constructors with companion objects**: controls instantiation, makes illegal states unrepresentable.
- **Extension functions over utility classes**: behavior added to types where it belongs.
- **Backtick test names**: tests read as specifications.
- **`internal` test functions**: tests are not part of the public API.

## What Is Not Done

- **No `@Before` / setUp methods in tests**: test data shared across tests lives in a `companion object` with an `init {}` block. This makes the graph structure or shared state explicit and avoids hidden setup.
- **No assertion libraries**: standard JUnit 5 assertions only (`assertEquals`, `assertTrue`, `assertThrows`, etc.).
- **No mocks**: tests exercise real objects end-to-end.
- **No documentation comments (KDoc)**: the code itself is the documentation.

## Testing Standards

Tests live in the mirror package under `src/test/kotlin/`. Each test class has `internal` visibility.

Test names use backticks to describe behaviour:

```kotlin
@Test internal fun `Same quantity on different metrics should be equal`() { ... }
@Test internal fun `Cannot reach not connected nodes`() { ... }
```

Shared fixtures belong in a `companion object`:

```kotlin
companion object {
    val A = Node()
    val B = Node()

    init {
        B cost 5 to A
        B cost 6 to C cost 1 to D
    }
}
```

Cover edge cases explicitly: unreachable nodes, empty collections, type incompatibility, negative values, and floating-point boundaries.


## Building Incrementally

A package rarely arrives at its final shape in one commit. The expected pattern is:

1. **Start with the core model**: one class, minimal behaviour, private state.
2. **Drive development with failing tests**: add the next test before writing the implementation.
3. **Extract classes when responsibilities diverge**: do not split prematurely — wait until duplication or multiple concerns are visible.
4. **Add the DSL layer last**: infix functions and extension properties are convenience built on a stable model, not a starting point.
5. **Rename toward the domain freely**: the compiler catches renames; do it early rather than living with a wrong name.
6. **Tighten visibility progressively**: once you know what needs to be public, lock everything else down. It is easier to open later than to restrict.
