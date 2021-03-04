package hr.fer.zemris.neural;

import java.util.Random;

public class NeuralLayer {
	
	private static Random r = new Random();
	private Neuron neurons[];
	
	public NeuralLayer(double inputs[]) {
		this.neurons = new Neuron[inputs.length];
		
		for (int i=0; i<inputs.length; i++) {
			this.neurons[i] = new Neuron(inputs[i]);
		}
	}
	
	public NeuralLayer(int prevNoOfNeurons, int noOfNeurons) {
		this.neurons = new Neuron[noOfNeurons];
		
		for (int i=0; i<noOfNeurons; i++) {
			double[] weights = new double[prevNoOfNeurons];
			for (int j=0; j<prevNoOfNeurons; j++) {
				weights[j] = -2.4/(double)prevNoOfNeurons + r.nextDouble()*(4.8/(double)prevNoOfNeurons);
			}
			neurons[i] = new Neuron(weights, -2.4/(double)prevNoOfNeurons + r.nextDouble()*(4.8/(double)prevNoOfNeurons));
		}
	}
	
	public Neuron[] getNeurons() {
		return this.neurons;
	}
	
	public Neuron getNeuron(int index) {
		return this.neurons[index];
	}
}
