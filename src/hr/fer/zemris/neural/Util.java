package hr.fer.zemris.neural;

public class Util {

	public static final int M = 30;
	
	public static double sigmoid(double x) {
		return 1/(double)(1+Math.exp(-x));
	}
	
//	public static double sigmoidDerivative(double x) {
//		return sigmoid(x)*(1-sigmoid(x));
//	}
}
