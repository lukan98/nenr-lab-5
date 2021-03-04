package hr.fer.zemris.neural;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Point {
	private double x, y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	private static double calculateDistance(Point A, Point B) {
		double sum = Math.pow(B.x - A.x, 2) + Math.pow(B.y - A.y, 2);
		return Math.sqrt(sum);
	}
	
	private static List<Point> removeDuplicates(List<Point> points) {
		return points.stream().distinct().collect(Collectors.toList());
	}
	
	private static Point calculateAverage(List<Point> points) {
		double sumX = 0;
		double sumY = 0;
		
		for (Point p : points) {
			sumX += p.x;
			sumY += p.y;
		}
		
		int n = points.size();
		
		return new Point(sumX/n, sumY/n);
	}

	private static double calculateLength(List<Point> points) {
		double length = 0;
		
		Point p0 = points.get(0);
		for (int i=1; i<points.size(); i++) {
			Point p1 = points.get(i);
			
			length += calculateDistance(p0, p1);
			
			p0 = p1;
		}
		
		return length;
	}
	
	private static List<Point> transformAndScale(List<Point> points) {
		List<Point> transformedPoints = removeDuplicates(points);
		Point tC = calculateAverage(transformedPoints);
		
		double mX = 0;
		double mY = 0;
		for (Point p : transformedPoints) {
			p.x -= tC.x;
			p.y -= tC.y;
			
			if (Math.abs(p.x) > mX) mX = Math.abs(p.x);
			if (Math.abs(p.y) > mY) mY = Math.abs(p.y);
		}
		
		double m = Math.max(mX, mY);
		
		for (Point p : transformedPoints) {
			p.x /= m;
			p.y /= m;
		}
		
		return transformedPoints;
		
	}

	public static List<Point> getRepresentativePoints(List<Point> points) {
		List<Point> transformedPoints = transformAndScale(points);
		
		double approximationDistance = calculateLength(transformedPoints)/(Util.M-1);
		
		ArrayList<Point> representativePoints = new ArrayList<>();
		
		Point p0 = transformedPoints.get(0);
		int k = 0;
		double distanceSum = 0;
		
		
		for (int i=0; i<transformedPoints.size(); i++) {
			if (k==Util.M) break;
			
			Point p1 = transformedPoints.get(i);
			distanceSum += calculateDistance(p0, p1);
			
			if(distanceSum >= k*approximationDistance) {
				representativePoints.add(p1);
				k++;
			}
			
			p0 = p1;
		}
		
		return representativePoints;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public String toString() {
		return "x=" + x + ", y=" + y;
	}
	
	public static void printPoints(List<Point> points) {
		for (Point p : points)
			System.out.println(p);
	}
}
