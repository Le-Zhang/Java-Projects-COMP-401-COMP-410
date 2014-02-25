package a2;

class PolygonImpl implements Polygon {
	private Point[] points;

	PolygonImpl(Point[] points) { // Constructor for Polygon
		this.points = points.clone();
	}

	@Override
	public Point[] getPoints() {
		return points.clone();
	}

	@Override
	public int getNumSides() {
		return points.length;
	}

	@Override
	public Point getVertexAverage() {
		double sum_x = 0;
		double sum_y = 0;

		for (int i = 0; i < points.length; i++) { // sum of all x value and sum
													// of all y value
			sum_x += points[i].getX();
			sum_y += points[i].getY();
		}

		double vx = sum_x / points.length;
		double vy = sum_y / points.length;

		Point va = new Point(vx, vy);
		return va;
	}

	@Override
	public Rectangle getBoundingBox() {
		double max_x = points[0].getX();
		double min_x = points[0].getX();
		double max_y = points[0].getY();
		double min_y = points[0].getY();

		for (int i = 0; i < points.length; i++) {
			if (points[i].getX() > max_x) {
				max_x = points[i].getX();
			} else if (points[i].getX() < min_x) {
				min_x = points[i].getX();
			}
			if (points[i].getY() > max_y) {
				max_y = points[i].getY();
			} else if (points[i].getY() < min_y) {
				min_y = points[i].getY();
			}
		}

		return new RectangleImpl(new Point(min_x, min_y), new Point(max_x,
				max_y));
	}

	@Override
	public Point getCentroid() {
		double cx = 0;
		double cy = 0;
		for (int i = 0; i < points.length; i++) {
			cx += (points[i].getX() + points[(i + 1) % points.length].getX())
					* (points[i].getX()
							* points[(i + 1) % points.length].getY() - points[(i + 1)
							% points.length].getX()
							* points[i].getY());
			cy += (points[i].getY() + points[(i + 1) % points.length].getY())
					* (points[i].getX()
							* points[(i + 1) % points.length].getY() - points[(i + 1)
							% points.length].getX()
							* points[i].getY());
		}

		return new Point(cx / (6 * this.getDoubleA()), cy
				/ (6 * this.getDoubleA()));
	}

	/*
	 * Get the value of area with positive and negative sign
	 * For the use of calculating centroid
	 */

	public double getDoubleA() {
		double a = 0;
		for (int i = 0; i < points.length; i++) {
			a += (points[i].getX() * points[(i + 1) % points.length].getY() - points[(i + 1)
					% points.length].getX()
					* points[i].getY());
		}
		return a / 2;
	}

	@Override
	public double getArea() {
		return Math.abs(this.getDoubleA());
	}

	@Override
	public void move(double dx, double dy) {
		for (int i = 0; i < points.length; i++) {
			points[i] = points[i].translate(dx, dy);
		}
	}

	@Override
	public void move(Point c) {
		double dx = c.getX() - this.getCentroid().getX();
		double dy = c.getY() - this.getCentroid().getY();

		this.move(dx, dy);
	}

	@Override
	public void scale(double factor) {
		Point c = this.getCentroid();
		this.move(new Point(0, 0));

		for (int i = 0; i < points.length; i++) {
			points[i] = new Point(points[i].getX() * factor, points[i].getY()
					* factor);
		}

		this.move(c);
	}

}
