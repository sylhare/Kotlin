package examples

class Functional {

    var a: Int = 0; private set
    private val b: Int = 1
    var counter = 0; private set


    fun applyThis() = a.apply { this.inc() + this@apply.inc() + this@Functional.one() }
    fun letIt() = a.let { it.inc() + this.one() + this@Functional.b }
    fun alsoIt() = a.also { it.inc() + this.one() }
    fun runThis() = a.run { this.inc() + this@Functional.one() }
    fun withThis() = with(a) { this.inc() + this@Functional.one() }

    fun applyThisInstantiate() = a.apply { this@apply.inc() }
    fun letItInstantiate() = a.let { a = it.inc() }
    fun alsoItInstantiate() = a.also { a = it.inc() }
    fun runThisInstantiate() = a.run { this@Functional.a = a.inc() }

    fun applyThisReturn() = a.apply { return this.inc() + this@apply.inc() + this@Functional.one() }
    fun alsoItReturn() = a.also { return it.inc() + this.one() }

    private fun one(): Int {
        counter++
        return 1
    }
}