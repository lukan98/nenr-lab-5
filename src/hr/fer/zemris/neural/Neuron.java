package hr.fer.zemris.neural;

public class Neuron {
	
	private double weights[];
	private double cachedWeights[];
	private double delta;
	
	private double bias;
	private double biasCache;
	private double value;
	
	public Neuron(double[] weights, double bias) {
		this.weights = weights;
		this.bias = bias;
		this.biasCache = bias;
		this.cachedWeights = weights;
		this.delta = 0;
	}
	
	public Neuron(double value) {
		this.weights = null;
		this.cachedWeights = null;
		this.bias = -1;
		this.biasCache = 0;
		this.delta = -1;
		this.value = value;
	}
	
	public double[] getWeights() {
		return weights;
	}

	public void setWeights(double[] weights) {
		this.weights = weights;
	}

	public double[] getCachedWeights() {
		return cachedWeights;
	}

	public void setCachedWeights(double[] cachedWeights) {
		this.cachedWeights = cachedWeights;
	}

	public double getDelta() {
		return delta;
	}
	
	public void addDelta(double delta) {
		this.delta += delta;
	}

	public void setDelta(double delta) {
		this.delta = delta;
	}

	public double getBias() {
		return bias;
	}

	public void setBias(double bias) {
		this.bias = bias;
	}
	
	public double getBiasCache() {
		return biasCache;
	}

	public void setBiasCache(double bias) {
		this.biasCache = bias;
	}
	
	public void increaseBiasCache(double bias) {
		this.biasCache += bias;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public void updateWeight() {
		this.weights = this.cachedWeights;
	}
	
	public void updateBias() {
		this.bias = this.biasCache;
	}

}
