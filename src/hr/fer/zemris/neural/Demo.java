package hr.fer.zemris.neural;


public class Demo {

	public static void main(String[] args) {
        
        NeuralNetwork brain = new NeuralNetwork(1, 6, 1);
        
        TrainingData data[] = createData();
        
        brain.setData(data);
        
        brain.train(100000, 0.3, 1, true);
        
        double input1[] = new double[] {0.1};
        double input2[] = new double[] {0.3};
        double input3[] = new double[] {0.5};
        double input4[] = new double[] {0.7};
        double input5[] = new double[] {0.9};
        
        System.out.println(brain.getNetworkOutput(input1)[0]);
        System.out.println(brain.getNetworkOutput(input2)[0]);
        System.out.println(brain.getNetworkOutput(input3)[0]);
        System.out.println(brain.getNetworkOutput(input4)[0]);
        System.out.println(brain.getNetworkOutput(input5)[0]);
	}
	
	private static TrainingData[] createData() {
		double[] input1 = new double[] {-1};
        double[] input2 = new double[] {-0.8};
        double[] input3 = new double[] {-0.6};
        double[] input4 = new double[] {-0.4};
        double[] input5 = new double[] {-0.2};
        double[] input6 = new double[] {0};
        double[] input7 = new double[] {0.2};
        double[] input8 = new double[] {0.4};
        double[] input9 = new double[] {0.6};
        double[] input10 = new double[] {0.8};
        double[] input11 = new double[] {1};
       
        double[] expectedOutput1 = new double[] {1};
        double[] expectedOutput2 = new double[] {0.64};
        double[] expectedOutput3 = new double[] {0.36};
        double[] expectedOutput4 = new double[] {0.16};
        double[] expectedOutput5 = new double[] {0.04};
        double[] expectedOutput6 = new double[] {0};
        double[] expectedOutput7 = new double[] {0.04};
        double[] expectedOutput8 = new double[] {0.16};
        double[] expectedOutput9 = new double[] {0.36};
        double[] expectedOutput10 = new double[] {0.64};
        double[] expectedOutput11 = new double[] {1};
        
        TrainingData td1 = new TrainingData(input1, expectedOutput1);
        TrainingData td2 = new TrainingData(input2, expectedOutput2);
        TrainingData td3 = new TrainingData(input3, expectedOutput3);
        TrainingData td4 = new TrainingData(input4, expectedOutput4);
        TrainingData td5 = new TrainingData(input5, expectedOutput5);
        TrainingData td6 = new TrainingData(input6, expectedOutput6);
        TrainingData td7 = new TrainingData(input7, expectedOutput7);
        TrainingData td8 = new TrainingData(input8, expectedOutput8);
        TrainingData td9 = new TrainingData(input9, expectedOutput9);
        TrainingData td10 = new TrainingData(input10, expectedOutput10);
        TrainingData td11 = new TrainingData(input11, expectedOutput11);
        
        TrainingData data[] = new TrainingData[11];
        data[0] = td1;
        data[1] = td2;
        data[2] = td3;
        data[3] = td4;
        data[4] = td5;
        data[5] = td6;
        data[6] = td7;
        data[7] = td8;
        data[8] = td9;
        data[9] = td10;
        data[10] = td11;
        
        return data;
	}

}
