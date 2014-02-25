package a2;

import java.math.BigDecimal;

public class TriangleImpl extends PolygonImpl implements Triangle, Polygon {
	private Point a;
	private Point b;
	private Point c;

	TriangleImpl(Point a, Point b, Point c) {
		super(new Point[] { a, b, c });

		this.a = a;
		this.b = b;
		this.c = c;
	}

	/*
	 * Triangle Implementations
	 */
	@Override
	public Category getCategory() {
		double side_a = new BigDecimal(a.distanceTo(b)).setScale(2,
				BigDecimal.ROUND_HALF_UP).doubleValue();
		double side_b = new BigDecimal(b.distanceTo(c)).setScale(2,
				BigDecimal.ROUND_HALF_UP).doubleValue();
		double side_c = new BigDecimal(c.distanceTo(a)).setScale(2,
				BigDecimal.ROUND_HALF_UP).doubleValue();

		if (side_a == side_c && side_a == side_b)
			return Category.EQUILATERAL;
		if (side_a == side_c || side_a == side_b || side_b == side_c)
			return Category.ISOSCELES;
		else
			return Category.SCALENE;
	}

	@Override
	public boolean isRight() {
		if (Math.sqrt(a.distanceTo(b) * a.distanceTo(b)) == Math.sqrt(a
				.distanceTo(c)
				* a.distanceTo(c)
				+ b.distanceTo(c)
				* b.distanceTo(c))
				|| Math.sqrt(a.distanceTo(c) * a.distanceTo(c)) == Math.sqrt(a
						.distanceTo(b)
						* a.distanceTo(b)
						+ b.distanceTo(c)
						* b.distanceTo(c))
				|| Math.sqrt(b.distanceTo(c) * b.distanceTo(c)) == Math.sqrt(a
						.distanceTo(b)
						* a.distanceTo(b)
						+ a.distanceTo(c)
						* a.distanceTo(c)))

			return true;
		else
			return false;
	}

	@Override
	public Point getA() {
		return a;
	}

	@Override
	public Point getB() {
		return b;
	}

	@Override
	public Point getC() {
		return c;
	}

	/*
	 * Polygon Implementations
	 */

	@Override
	public Point[] getPoints() {
		return super.getPoints();
	}

	@Override
	public int getNumSides() {
		return super.getNumSides();
	}

	@Override
	public Point getVertexAverage() {
		return super.getVertexAverage();
	}

	@Override
	public Rectangle getBoundingBox() {
		return super.getBoundingBox();
	}

	@Override
	public Point getCentroid() {
		return super.getCentroid();
	}

	@Override
	public double getArea() {
		return super.getArea();
	}

	@Override
	public void move(double dx, double dy) {
		super.move(dx, dy);

	}

	@Override
	public void move(Point c) {
		super.move(c);
	}

	@Override
	public void scale(double factor) {
		super.scale(factor);
	}

}
