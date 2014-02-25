package a2;

public class RectangleImpl_v2 implements Rectangle, Polygon {
	private Point p1;
	private Point p2;
	private Point[] points;
	
	RectangleImpl_v2(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
		points = new Point[] {p1,this.getLowerRight(),p2,this.getUpperLeft()};
	}
	
	RectangleImpl_v2(Point[] points){
		this.points = points.clone();
	}

	@Override
	public Point getLowerLeft() {
		return p1;
	}

	@Override
	public Point getLowerRight() {
		return new Point(p2.getX(), p1.getY());
	}

	@Override
	public Point getUpperRight() {
		return p2;
	}

	@Override
	public Point getUpperLeft() {
		return new Point(p1.getX(), p2.getY());
	}

	@Override
	public boolean isSquare() {
		Point p3 = new Point(p2.getX(), p1.getY());
		if (p1.distanceTo(p3) == p2.distanceTo(p3))
			return true;
		else
			return false;
	}

	@Override
	public Point[] getPoints() {
		/*Point p1 = this.getLowerLeft();
		Point p2 = this.getLowerRight();
		Point p3 = this.getUpperRight();
		Point p4 = this.getUpperLeft();

		Point[] rectPoints = new Point[] { p1, p2, p3, p4 };

		return rectPoints;*/
		return points.clone();
	}

	@Override
	public int getNumSides() {
		return 4;
	}

	@Override
	public Point getVertexAverage() {
		Point va = new Point((this.getLowerLeft().getX() + this.getLowerRight()
				.getX()) / 2, (this.getLowerLeft().getY() + this.getUpperLeft()
				.getY()) / 2);
		return va;
	}

	@Override
	public Rectangle getBoundingBox() {
		return this;
	}

	@Override
	public Point getCentroid() {
		return this.getVertexAverage();
	}

	@Override
	public double getArea() {
		return this.getLowerLeft().distanceTo(this.getLowerRight())
				* this.getLowerLeft().distanceTo(this.getUpperLeft());
	}

	@Override
	public void move(double dx, double dy) {
		p1 = this.getLowerLeft().translate(dx, dy);
		p2 = this.getUpperRight().translate(dx, dy);

	}

	@Override
	public void move(Point c) {
		double dx = c.getX() - this.getCentroid().getX();
		double dy = c.getY() - this.getCentroid().getY();

		this.move(dx, dy);
	}

	@Override
	public void scale(double factor) {
		Point c = new Point(this.getCentroid().getX(), this.getCentroid()
				.getY());

		this.move(new Point(0, 0));

		p1 = new Point(p1.getX() * factor, p1.getY() * factor);
		p2 = new Point(p2.getX() * factor, p2.getY() * factor);

		this.move(c);

	}

}

