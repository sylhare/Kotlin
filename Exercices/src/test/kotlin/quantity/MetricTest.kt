package quantity


import quantity.Metric.Metric
import quantity.Metric.Metrics.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MetricTest {

    @Test fun `Equals`() {
        assertEquals(Metric(1, TABLESPOON), Metric(3, TEASPOON))
        assertEquals(Metric(1, OUNCE), Metric(2, TABLESPOON))
        assertEquals(Metric(1, CUP), Metric(8, OUNCE))
        assertEquals(Metric(1, PINT), Metric(2, CUP))
        assertEquals(Metric(1, QUART), Metric(2, PINT))
        assertEquals(Metric(1, GALLON), Metric(4, QUART))
        assertEquals(Metric(1, CUP), Metric(16, TABLESPOON))
        assertEquals(Metric(0.5, CUP), Metric(4, OUNCE))
        assertEquals(Metric(4, TABLESPOON), Metric(0.25, CUP))
    }

    @Test fun `hasCode`() {
        assertTrue(hashSetOf(Metric(0.75, TEASPOON)).contains(Metric(0.75, TEASPOON)))
        assertTrue(hashSetOf(Metric(4, TABLESPOON)).contains(Metric(0.25, CUP)))
    }

    @Test fun `Add`() {
        assertEquals(Metric(1, CUP), Metric(0.5, CUP) + (Metric(0.5, CUP)))
        assertEquals(Metric(0.75, PINT), Metric(1, CUP) + (Metric(4, OUNCE)))
    }
}