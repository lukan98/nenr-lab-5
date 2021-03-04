package hr.fer.zemris.neural;

public class NeuralNetwork {
	
	public NeuralLayer layers[];
	private TrainingData data[];
	
	public NeuralNetwork(int ...architecture) {
//		if (architecture[0] != 2*Util.M || architecture[architecture.length-1] != 5)
//			throw new IllegalArgumentException("Ulazni sloj mreže mora imati "+2*Util.M+" neurona, a izlazni mora imati 5 neurona!");
		
		this.layers = new NeuralLayer[architecture.length];
	
		for(int i=0; i<architecture.length; i++) {
			if (i==0) continue;
			
			layers[i] = new NeuralLayer(architecture[i-1], architecture[i]);
		}
		
	}
	
	private void forwardPropagation(double inputs[]) {
		
		this.layers[0] = new NeuralLayer(inputs);
		
		for (int i=1; i<layers.length; i++) {
			for(int j=0; j<layers[i].getNeurons().length; j++) {
				double sum = 0;
				for (int k=0; k<layers[i-1].getNeurons().length; k++) {
					sum += layers[i-1].getNeuron(k).getValue()*layers[i].getNeuron(j).getWeights()[k];
				}
				sum += layers[i].getNeuron(j).getBias();
				layers[i].getNeuron(j).setValue(Util.sigmoid(sum));
			}
		}
		
	}
	
	private void backpropagation(double learningRate, TrainingData data, int iteration, int batchSize) {
		
		int noOfLayers = this.layers.length;
		int out = noOfLayers - 1;
		
		// determining the error of the output layer
		for (int i=0; i<layers[out].getNeurons().length; i++) {
			double output = layers[out].getNeuron(i).getValue();
			double expected = data.expectedOutputs[i];
			double delta = output*(1-output)*(expected-output);
			
			layers[out].getNeuron(i).setDelta(delta);
			
			for (int j=0; j<layers[out].getNeuron(i).getWeights().length; j++) {
				double previousY = layers[out-1].getNeuron(j).getValue();
				double error = delta*previousY;

				correctCachedWeight(layers[out].getNeuron(i), j, learningRate, error);
			}
		}
		
		
		// determining the error of the hidden layers
		for (int k=out-1; k>0; k--) {
			for (int i=0; i<layers[k].getNeurons().length; i++) {
				double y = layers[k].getNeuron(i).getValue();
				double deltaSum = gradientSum(k+1, i);
				double delta = y*(1-y)*deltaSum;
				
				for(int j=0; j<layers[k].getNeuron(i).getWeights().length; j++) {
//					System.out.println(k+" "+i+" "+j+" "+layers[k-1].getNeurons().length+" "+layers[k].getNeuron(i).getWeights().length);
					double previousY = layers[k-1].getNeuron(j).getValue();
					double error = delta*previousY;
					
					correctCachedWeight(layers[k].getNeuron(i), j, learningRate, error);
				}
			}
		}
		
		if (iteration%batchSize == 0) {
//			System.out.println("Ažuriram mrežu...Iteracija: "+(iteration)+", veličina batcha: "+batchSize);
			for (int i=0; i<layers.length; i++) {
				for (int j=0; j<layers[i].getNeurons().length; j++) {
					layers[i].getNeuron(j).updateWeight();
					layers[i].getNeuron(j).updateBias();
				}
			}
		}
	}
	
	public double getError(TrainingData data[]) {
		double totalError = 0;
		
		for (int i=0; i<data.length; i++) {
			double inputs[] = data[i].inputs;
			double expectedOutputs[] = data[i].expectedOutputs;
			double outputs[] = this.getNetworkOutput(inputs);
			
			for(int j=0; j<inputs.length; j++) {
				double difference = Math.pow(expectedOutputs[j]-outputs[j], 2);
				totalError += difference;
			}
		}
		
		return totalError/(double)(data.length*2);
	}
	
	private void correctCachedWeight(Neuron n, int weightIndex, double learningRate, double error) {
		n.getCachedWeights()[weightIndex] += learningRate*error;
		n.increaseBiasCache(learningRate*error);
	}
	
	public double[] getNetworkOutput(double input[]) {
		forwardPropagation(input);
		int out = layers.length-1;
		
		double output[] = new double[layers[out].getNeurons().length];
		
		for(int i=0; i<output.length; i++) {
			output[i] = layers[out].getNeuron(i).getValue();
		}
		
		return output;
	}
	
	// batch size stavite 1 ako želite stohastički spust, a stavite ga na veličinu skupa za učenje ako želite grupni spust
	
	public void train(int epochs, double learningRate, int batchSize, boolean verbose) {
		if (batchSize > this.data.length) throw new IllegalArgumentException("Batch size mora biti manji od skupa za učenje!");
		
		for (int i=0; i<epochs; i++) {
			for (int j=0; j<data.length; j++) {
				forwardPropagation(data[j].inputs);
				backpropagation(learningRate, data[j], i*data.length+(j+1), batchSize);
				if (verbose)
					System.out.println("Greška modela: "+this.getError(this.data));	
			}
		}
	}
	
	public void setData(TrainingData[] data) {
		this.data = data;
	}

	private double gradientSum(int lIndex, int nIndex) {
		double gradientSum = 0;
		NeuralLayer currentLayer = layers[lIndex];
		
		for (int i=0; i<currentLayer.getNeurons().length; i++) {
			Neuron currentNeuron = currentLayer.getNeuron(i);
			gradientSum += currentNeuron.getWeights()[nIndex]*currentNeuron.getDelta();
		}
		
		return gradientSum;
		
	}

}
