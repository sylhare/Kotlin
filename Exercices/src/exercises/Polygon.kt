package exercises

// Exercise : There is no square
// Understand that a polygon with 4 sides and have a right angle
class Polygon internal constructor(length: Number, width: Number) {

    private val width = width.toDouble()
    private val length = length.toDouble()

    init {
        if (length.toDouble() <= 0 || width.toDouble() <= 0) {
            throw IllegalArgumentException("Needs to be length > 0 or width > 0")
        }
    }

    companion object {
        fun square(side: Number) = Polygon(side, side)
        fun rectangle(length: Number, width: Number) = Polygon(length, width)
    }

    fun perimeter(): Number = (length + width) * 2
    fun area(): Number = length * width
    fun scale(ratio: Int): Polygon = Polygon(length * ratio, width * ratio)
    fun hasRightAngle(): Boolean = true
}