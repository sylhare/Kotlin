package exercises

// Understand that a polygon with 4 sides and have a right angle
class Rectangle(private val length: Long, private val width: Long) {

//class Rectangle(length: Number, width: Number) {
//        private val width = width.toDouble()
//        private val length = length.toDouble()

    init{
        if (length <= 0 || width <= 0) {
            throw IllegalArgumentException("Needs to be length > 0 or width > 0")
        }
    }

    fun perimeter(): Long = (length + width) * 2

    fun area(): Long = length * width

    fun hasRightAngle(): Boolean = true
    fun scale(ratio: Int): Rectangle = Rectangle(length * ratio, width * ratio)
}