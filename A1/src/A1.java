import java.util.Scanner;


public class A1 {

	static double triArea(int x1, int y1, int x2, int y2, int x3, int y3){
		double triArea;
		triArea = Math.abs(x1*(y2-y3)+ x2*(y3-y1)+ x3*(y1-y2))/2.0;
		return triArea;
	}
	
	static double triPeri(int x1, int y1, int x2, int y2, int x3, int y3){
		double triPeri;
		triPeri = (double)Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)) + (double)Math.sqrt((x1-x3)*(x1-x3) + (y1-y3)*(y1-y3)) + (double)Math.sqrt((x2-x3)*(x2-x3) + (y2-y3)*(y2-y3));
		return triPeri;
		
	}
	
	static double rectArea(int x1, int y1, int x2, int y2){
		double rectArea;
		rectArea = (double)Math.abs((x2-x1)*(y2-y1));
		return rectArea;
	}
	
	static double rectPeri(int x1, int y1, int x2, int y2){
		double rectPeri;
		rectPeri = (double)(Math.abs(x2-x1) + Math.abs(y2-y1))*2;
		return rectPeri;
	}
	
	static double cirArea(int x1, int y1, double r){
		double cirArea;
		cirArea = Math.PI * r * r;
		return cirArea;
	}
	
	static double cirPeri(int x1, int y1, double r){
		double cirPeri;
		cirPeri = Math.PI * r * 2;
		return cirPeri;
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		String shape;
		int x1;
		int y1;
		int x2;
		int y2;
		int x3;
		int y3;
	 
		double r;
		double maxArea = 0;
		double minArea = 9999;
		double maxPeri = 0;
		double minPeri = 9999;
		
		
		
		do{
			
			Scanner sc = new Scanner(System.in);
			shape = sc.next();
			
			if(shape.equals("triangle") ){
				x1 = sc.nextInt();
				y1 = sc.nextInt();
				x2 = sc.nextInt();
				y2 = sc.nextInt();
				x3 = sc.nextInt();
				y3 = sc.nextInt();
				
				double triArea = triArea(x1,y1,x2,y2,x3,y3);
				double triPeri = triPeri(x1,y1,x2,y2,x3,y3);
				
				if(triArea > maxArea) maxArea = triArea;
				if(triArea < minArea) minArea = triArea;
				if(triPeri > maxPeri) maxPeri = triPeri;
				if(triPeri < minPeri) minPeri = triPeri;
				
			}
			
			if(shape.equals("circle") ){
				x1 = sc.nextInt();
				y1 = sc.nextInt();
				r = sc.nextDouble();
				
				double cirArea = cirArea(x1, y1, r);
				double cirPeri = cirPeri(x1, y1, r);
				
				if(cirArea > maxArea) maxArea = cirArea;
				if(cirArea < minArea) minArea = cirArea;
				if(cirPeri > maxPeri) maxPeri = cirPeri;
				if(cirPeri < minPeri) minPeri = cirPeri;
				
			}
			
			if(shape.equals("rectangle")){
				x1 = sc.nextInt();
				y1 = sc.nextInt();
				x2 = sc.nextInt();
				y2 = sc.nextInt();
				
				double rectArea = rectArea(x1, y1, x2, y2);
				double rectPeri = rectPeri(x1, y1, x2, y2);
				
				if(rectArea > maxArea) maxArea = rectArea;
				if(rectArea < minArea) minArea = rectArea;
				if(rectPeri > maxPeri) maxPeri = rectPeri;
				if(rectPeri < minPeri) minPeri = rectPeri;
			}
			 
			if(shape.equals("end")) break;
			
			} while(true);
		
		System.out.println("The smallest perimeter is " + minPeri);
		System.out.println("The smallest area is " + minArea);
		System.out.println("The largest perimeter is " + maxPeri);
		System.out.println("The largest area is " + maxArea);
		
		

		
	}

}
