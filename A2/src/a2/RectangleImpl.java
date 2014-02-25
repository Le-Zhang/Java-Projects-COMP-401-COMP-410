package a2;

public class RectangleImpl extends PolygonImpl implements Rectangle, Polygon {

	private Point LowerLeft;
	private Point LowerRight;
	private Point UpperRight;
	private Point UpperLeft;

	RectangleImpl(Point p1, Point p2) {
		super(new Point[] { p1, new Point(p2.getX(), p1.getY()), p2,
				new Point(p1.getX(), p2.getY()) });

		double min_x = Math.min(p1.getX(), p2.getX());
		double max_x = Math.max(p1.getX(), p2.getX());
		double min_y = Math.min(p1.getY(), p2.getY());
		double max_y = Math.max(p1.getY(), p2.getY());

		LowerLeft = new Point(min_x, min_y);
		LowerRight = new Point(max_x, min_y);
		UpperRight = new Point(max_x, max_y);
		UpperLeft = new Point(min_x, max_y);

	}

	/*
	 * Rectangle Implementations
	 */

	@Override
	public Point getLowerLeft() {
		return LowerLeft;
	}

	@Override
	public Point getLowerRight() {
		return LowerRight;
	}

	@Override
	public Point getUpperRight() {
		return UpperRight;
	}

	@Override
	public Point getUpperLeft() {
		return UpperLeft;
	}

	@Override
	public boolean isSquare() {
		if (LowerLeft.distanceTo(LowerRight) == LowerRight
				.distanceTo(UpperRight))
			return true;
		else
			return false;
	}

	/*
	 * Polygon Implementation
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
