# References

This project uses some concept that will be detailed here.

## Polygon

There is no square.
A rectangle is a polygon (quadrilateral with a length and a width).

> If you add points, coordinates then it is a cartesianRectangle.

Those simple rectangle can't really be compare, however you can compare there area or perimeter.

## Probability

For not conditional probabilities.

### And
Standard would be:

	P(A and B) = P(A) * P(B)
	
### Or
Standard would be:

	P(A or B) = P(A) + P(B) - P(A and B)
	
Morgan's law would be to look at neither A and B to get A or B:

	P(A or B) = not P(not A) and P(not B)
	
## Quantity

Quantity - Analysis pattern by Martin Fowler

### Unit

Type of Unit

- Nominal (distance, volume)
- Ordinal (scale-like representing a rank)
- IntervalQuantity (dates, temperature)
- Ratio (days)