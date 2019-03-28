![Kotlin](https://upload.wikimedia.org/wikipedia/commons/thumb/7/74/Kotlin-logo.svg/240px-Kotlin-logo.svg.png)

## Introduction
Kotlin is made by IntelliJ (the same as the IDE).

See their website here:
  - [Kotlin](https://kotlinlang.org/)
  - [Getting started](https://kotlinlang.org/docs/tutorials/getting-started.html)

## Get started
If you are not using intellij, you can run with:

```bash
kotlinc hello.kt
```

## Object oriented

Some principles

- Inheritance : kill off duplicate codes
- Polymorphism : abstraction of implementation
- Encapsulation : hiding the changes, protects data

Objects comparison:

- `equals`: should not throw an exception 
- `hashCode`: to compare (find) the object in a list

Encapsulation problems:

- getters / setters
	- Either with a verb to change something
	- With a noun to have the value of something	 
- publicly available data
	- You don't want anyone using the data without knowing how to. 
- Data object, feature envy
- Classes finishing by `-er` or `-or`
	- Should be a job of the class, or implemented as a pattern

## Tips

- Use ``` ` ``` (back tick) to define method's name with space:

```kotlin
@Test
fun `valid parameters` { ... }
```

- Overload Objects to generates new ones easily:
```kotlin
val Number.teaspoons get() = Quantity(this, Unit.teaspoon)

Quantity(1.5, Unit.teasponn) 
// Can now be written
1.5.teaspoons

```

## Keywords

- `Any?` for any type
- `init {}` to check the values inputed at the creation of the class
- `override` at the beginning of the function to override it:

```kotlin
override fun equals(other: Any?) { ... }
```
- `operator` to override an operator function. Here an example with the comparison operator (`>`, `<`, ...)

```kotlin
operator fun compareTo(other: Probability) { ... }
```