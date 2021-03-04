package hr.fer.zemris.GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import hr.fer.zemris.neural.NeuralNetwork;
import hr.fer.zemris.neural.Point;
import hr.fer.zemris.neural.TrainingData;
import hr.fer.zemris.neural.Util;

public class Classifier extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NeuralNetwork ANN;
	private int batchSize, epochs;
	private double learningRate;
	
	private Container c;
	private Board b;
	
	private JButton classify;
	
	public Classifier(String title) throws IOException {
		TrainingData data[] = TrainingData.readFromFile("/Users/lukanamacinski/FER-workspace/NENR-workspace/lab5/examples.txt");
		this.batchSize = 96;
		this.epochs = 100000;
		this.learningRate = 0.1;
		
		createAndTrain(data, 2*Util.M, 45, 5);
		
		this.setTitle(title);
		
		c = this.getContentPane();
		b = new Board();
		
		c.add(b);
		
		classify = new JButton("Classify!");
		
		classify.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(getOutput(b.getPoints()));
				b.resetBoard();
				b.repaint();
			}
		});
		
		JPanel buttons = new JPanel();
		buttons.add(classify);
		
		c.add(buttons, BorderLayout.SOUTH);

	}
	
	private String getOutput(List<Point> points) {
		List<Point> representativePoints = Point.getRepresentativePoints(points);
		
		double inputs[] = new double[2*representativePoints.size()];
		ArrayList<Double> temp = new ArrayList<>();
		
		for(Point p : representativePoints) {
			temp.add(p.getX());
			temp.add(p.getY());
		}
		
		for (int i=0; i<temp.size(); i++) {
			inputs[i] = temp.get(i);
		}
		
		double output[] = this.ANN.getNetworkOutput(inputs);
		
//		for(double o : output) {
//			System.out.print(o+" ");
//		}
//		System.out.println();
		
		int pivot = 0;
		
		for (int i=0; i<output.length; i++) {
			if (output[i] > output[pivot]) pivot = i;
		}
		
		switch(pivot) {
		case 0:
			return "Alpha";
		case 1:
			return "Beta";
		case 2:
			return "Gamma";
		case 3:
			return "Delta";
		case 4:
			return "Epsilon";
		}
		
		return null;
	}
	
	private void createAndTrain(TrainingData data[], int ...architecture) {
		this.ANN = new NeuralNetwork(architecture);
		this.ANN.setData(data);
		System.out.println("Training my brain...");
		this.ANN.train(epochs, learningRate, batchSize, false);
		System.out.println("All done!");
	}
	
	public static void main(String[] args) throws IOException {
		Classifier frame = new Classifier("Let's classify!");
		frame.setVisible(true);
		frame.setSize(800, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
