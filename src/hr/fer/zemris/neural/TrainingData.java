package hr.fer.zemris.neural;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TrainingData {

	double inputs[];
	double expectedOutputs[];
	
	public TrainingData(double[] inputs, double[] expectedOutput) {
        this.inputs = inputs;
        this.expectedOutputs = expectedOutput;
    }
	
	public TrainingData(String line) {
		String words[] = line.split(" : ");
		String inputStrings[] = words[0].split(",");
		String outputStrings[] = words[1].split(",");
			
		this.inputs = new double[inputStrings.length];
		this.expectedOutputs = new double[outputStrings.length];
		
		for (int i=0; i<inputStrings.length; i++) {
			inputs[i] = Double.parseDouble(inputStrings[i]);
		}
		for (int i=0; i<outputStrings.length; i++) {
			expectedOutputs[i] = Double.parseDouble(outputStrings[i]);
		}
	}
	
	public static TrainingData[] readFromFile(String sourcePath) throws IOException {
		File file = new File(sourcePath); 
		BufferedReader br = new BufferedReader(new FileReader(file));
		ArrayList<TrainingData> data = new ArrayList<>();
	
		String line;
		while ((line = br.readLine()) != null) {
			data.add(new TrainingData(line));
		}
		
		br.close();
		
		
		
		TrainingData[] trainingData = new TrainingData[data.size()];
		
		for (int i=0; i<data.size(); i++) {
			trainingData[i] = data.get(i);
		}
		
		return trainingData;
	}
	
}
