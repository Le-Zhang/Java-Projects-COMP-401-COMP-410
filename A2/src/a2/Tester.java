package a2;

public class Tester {

	public static void main(String[] args) {
		Point a = new Point(-1, -2);
		Point b = new Point(-2.5, -3.5);
		Point c = new Point(-3.1, -1.4);
		Point d = new Point(-2, 1.3);
		Point e = new Point(1, 1);
		Point f = new Point(2, 3);
		Point g = new Point(3, 0.5);
		Point h = new Point(5, 2.5);
		Point i = new Point(4, -2);
		Point j = new Point(1.5, -1.5);
		
		Point[] points = new Point[] {a, b, c, d, e, f, g, h, i, j};
		
		points = sortPoints(points);
		
		for(Point p : points)
		{
			System.out.printf("(%.2f, %.2f)\n", p.getX(), p.getY());
		}
		
		PolygonImpl p = new PolygonImpl(points);
		
		System.out.printf("(%.2f, %.2f)\n", p.getCentroid().getX(), p.getCentroid().getY());
		System.out.printf("%.2f\n", p.getArea());
	}
	
	
	private static Point[] sortPoints(Point[] points) {
		int right_count = 0;
		int left_count = 0;
		Point va = new Point(0.79, -0.21);
		Point[] rightPoints = new Point[points.length];
		Point[] leftPoints = new Point[points.length];
		//double[] rightAngle = new double[points.length];
		//double[] leftAngle = new double[points.length];

		for (int i = 0; i < points.length; i++) {
			if (points[i].getX() >= va.getX()) {
				rightPoints[right_count] = points[i];
				right_count++;
			} else {
				leftPoints[left_count] = points[i];
				left_count++;
			}
		}

//		for (int i = 0; i < right_count; i++){
//			rightAngle[i] = Math.atan2(rightPoints[i].getY()-va.getY(), rightPoints[i].getX() - va.getX());
//		}
//		for (int i = 0; i< left_count; i++){
//			leftAngle[i] = Math.atan2(leftPoints[i].getY() - va.getY(), leftPoints[i].getX() - va.getX());
//		}
		
		
		for(int i = 0; i < right_count; i++){
			for(int j = i; j < right_count; j++){
				if((rightPoints[j].getY() - va.getY())/(rightPoints[j].getX() - va.getX()) 
						<= (rightPoints[i].getY() - va.getY())/(rightPoints[i].getX() - va.getX()))
				{
					Point temp = rightPoints[i];
					rightPoints[i] = rightPoints[j];
					rightPoints[j] = temp;
				}
			}
		}
		
		for(int i = 0; i < left_count; i++){
			for(int j = i; j < left_count; j++){
				if((leftPoints[j].getY() - va.getY())/(leftPoints[j].getX() - va.getX()) 
						<= (leftPoints[i].getY() - va.getY())/(leftPoints[i].getX() - va.getX()))
				{
					Point temp = leftPoints[i];
					leftPoints[i] = leftPoints[j];
					leftPoints[j] = temp;
				}
			}
		}
		
		int j = 0;
		for(int i = right_count; i < rightPoints.length; i++){
			rightPoints[i] = leftPoints[j];
			j++;
		}
		

			return rightPoints;

	}
	
	private static Point[] sort(Point[] points)
	{
		Point va = new Point(0.79, -0.21);
		Point[] lowPoints = new Point[points.length];
		Point[] highPoints = new Point[points.length];
		int lcount = 0;
		int hcount = 0;
		for(int i = 0; i < points.length; i ++)
		{
			if(points[i].getY() - va.getY() < 0)
					lowPoints[lcount++] = points[i];
			else if(points[i].getY() - va.getY() > 0)
					highPoints[hcount++] = points[i];
		}
		
		for(int i = 0; i < lcount ; i ++)
			for(int j = i; j < lcount; j ++)
				if(lowPoints[j].getX() < lowPoints[i].getX())
				{
					Point tmp = lowPoints[i];
					lowPoints[i] = lowPoints[j];
					lowPoints[j] = tmp;
				}
		
		for(int i = 0; i < hcount ; i ++)
			for(int j = i; j < hcount; j ++)
				if(highPoints[j].getX() > highPoints[i].getX())
				{
					Point tmp = highPoints[i];
					highPoints[i] = highPoints[j];
					highPoints[j] = tmp;
				}
		int j = 0;
		
		for(int i = lcount; i < lowPoints.length;i++)
		{
			lowPoints[i] = highPoints[j];
			j++;
		}
		
		return lowPoints;		
	}

}
