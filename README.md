# Kotlin
[![Build Status](https://travis-ci.org/sylhare/Kotlin.svg?branch=master)](https://travis-ci.org/sylhare/Kotlin) [![Maintainability](https://api.codeclimate.com/v1/badges/94ef0be8c89785a0db0b/maintainability)](https://codeclimate.com/github/sylhare/Kotlin/maintainability) 
[![codecov](https://codecov.io/gh/sylhare/Kotlin/branch/master/graph/badge.svg)](https://codecov.io/gh/sylhare/Kotlin)

## Introduction
Kotlin is made by JetBrains (the same as that did IntelliJ the IDE).

See their website here:
  - [Kotlin](https://kotlinlang.org/)
  - [Getting started](https://kotlinlang.org/docs/tutorials/getting-started.html)
  - [Kotlin 101](https://sylhare.github.io/2019/12/02/Kotlin-101.html)

## Get started

Compiled with Java11.

To use with IntelliJ, you can select your project SDK, if you have multiple ones.
Use `File | Project Structure... | Project Settings | Projects` to choose `Java 11`.

### With kotlinc

If you are not using intellij, you can run with:

```bash
kotlinc hello.kt
```

### With gradle

You need to create the gradle file `build.gradle` (support [groovy or kotlin](https://kotlinlang.org/docs/reference/using-gradle.html)) then add dependencies and configurations.

For gradle to pick up the source and test files, you will need to organise your folders like:

```bash
└── src
    ├── main
    │   └── kotlin
    │       └── package
    └── test
        └── kotlin
            └── package

```

You can now try to run the tests (using the gradle wrapper `./gradlew` instead of `gradle` to
use the gradle version with which the project has been built with):

```bash
./gradlew test
```

And the complete one with coverage:

```bash
./gradlew clean build test jacocoTestReport
```

## Object-oriented

Some principles

- _Inheritance_ : kill off duplicate codes 
	- Implement with a Mother class implemented by a daughter class
- _Polymorphism_ : abstraction of implementation
	- Implement using interface and generic functions 
- _Encapsulation_ : hiding the changes, protects data
	- Implement by using restrictive access to data or contructers

Objects comparison:

- `equals`: should not throw an exception 
- `hashCode`: to compare (find) the object in a list

Encapsulation problems:

- Don't use `setSomething` and `getSomething` if you need _getters / setters_ but rather:
	- A Method with a verb to change something
	- A property with a noun to have the value of something	 
- Control the data flow
 	- You want to control how the value of your object is handled
	- You don't want anyone using the data without knowing how to
	- Make everything as private as possible
- Data object, feature envy
- Classes finishing by `-er` or `-or`
	- Should be a job of the class, or implemented as a pattern

## Tips

- Use ``` ` ``` (back tick) to define method's name with space:

```kotlin
@Test
fun `valid parameters` { ... }
```

- Overload Objects to generate new ones easily:

```kotlin
val Number.teaspoons get() = Quantity(this, Unit.teaspoon)

Quantity(1.5, Unit.teasponn) 
// Can now be written
1.5.teaspoons
```

- Before a recursion, ask yourself these questions:
	- What is the original question?
	- What is the recursive question?
	- What are the Terminal condition? (when the recursive stops)

### Handling null

- The `?` is used in Kotlin to handle null:

```kotlin
// The Rectangle? makes the function returns Rectangle or null
fun mayReturnRectangleOrNull() : Rectangle? { ... }

// With "?." the .rectangleMethod() will only occur when mayReturnRectangleOrNull() returns a rectangle
mayReturnRectangleOrNull()?.rectangleMethod() 

// Elvis ?: to have another value if the preceeding one return null
val somethingNotNull = emptyRectangle()
return mayReturnRectangleOrNull() ?: somethingNotNull 
```

- The `!!` to force the function to operate eventhough it can be null:

```kotlin
return if (list.isEmpty) -1 else list.min()!! //list can be null, without !! it would complain, but can still return null
```

- Using the `this@Class` inside of a `list.apply { this.prepend(this@Class) }`, you are calling the `this` of the class (so `this@Class` to differentiate from the other `this` (element of the list that you apply to).

### Work with recursive

When you have to create a recursive function, you first answer this three questions:
  - What is the original question: What you are actually trying to do?
  - What is the recursive question: What will you base your recursion on?
  - What are the terminal conditions: When should the recursive question stopped and return an answer?

## Keywords

- `Any` for any type
- `init {}` to check the values inputed at the creation of the class
- `override` at the beginning of the function to override it:

```kotlin
override fun equals(other: Any?) { ... }
```
- `operator` to override an operator function. Here an example with the comparison operator (`>`, `<`, ...)

```kotlin
operator fun compareTo(other: Probability) { ... }
```
- `infix` function are function where you can omit the `.`:

```kotlin
infix fun isCompatibleWith(other: Unit): Boolean { ... }

// calling the function using the infix notation
unit isCompatibleWith otherUnit

// is the same as
unit.isCompatibleWith(otherUnit)
```

- `typealias` is used to define a name for a function signature (hence alias) usually used to simplify simple functions into a type:
```kotlin
internal typealias CostStrategy = (Double) -> Double
```

### Operation on lists

- `flatMap` transforms a list of list of stuff into a simple list of stuff
```kotlin
list.flatMap { List<List<Path>> } // returns List<Path>
```

- `onEach` is the same as `list.map{ this.apply { ... } }`

- `sumByDouble` is the same as  `list.map{}.sum{}`
