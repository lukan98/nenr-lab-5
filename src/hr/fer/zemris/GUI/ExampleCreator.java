package hr.fer.zemris.GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.*;
import hr.fer.zemris.neural.Point;

public class ExampleCreator extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Container c;
	private Board b;
	private JButton alpha, beta, gamma, delta, epsilon;
	
	public ExampleCreator(String title) {
		this.setTitle(title);
		
		c = this.getContentPane();
		b = new Board();
		
		c.add(b);
		
		alpha = new JButton("Export as alpha");
		beta = new JButton("Export as beta");
		gamma = new JButton("Export as gamma");
		delta = new JButton("Export as delta");
		epsilon = new JButton("Export as epsilon");
		
		alpha.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exportToFile(b.getPoints(), "alpha");
			}
		});
		
		beta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exportToFile(b.getPoints(), "beta");
			}
		});
		
		gamma.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exportToFile(b.getPoints(), "gamma");
			}
		});
		
		delta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exportToFile(b.getPoints(), "delta");
			}
		});
		
		epsilon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				exportToFile(b.getPoints(), "epsilon");
			}
		});
		
		JPanel buttons = new JPanel();
		buttons.add(alpha);
		buttons.add(beta);
		buttons.add(gamma);
		buttons.add(delta);
		buttons.add(epsilon);
		
		c.add(buttons, BorderLayout.NORTH);
		
	}
	
	private void exportToFile(List<Point> points, String letter) {
		List<Point> representativePoints = Point.getRepresentativePoints(points);
		try {
			FileWriter fileWriter = new FileWriter("/Users/lukanamacinski/FER-workspace/NENR-workspace/lab5/examples.txt", true);
			
			StringBuilder builder = new StringBuilder();
			
			for(int i=0; i<representativePoints.size(); i++) {
				Point p = representativePoints.get(i);
				builder.append(p.getX()+","+p.getY());
				if (i!=representativePoints.size()-1)
					builder.append(",");
			}
			
			builder.append(" : ");
			switch(letter) {
			case "alpha":
				builder.append("1,0,0,0,0");
				break;
			case "beta":
				builder.append("0,1,0,0,0");
				break;
			case "gamma":
				builder.append("0,0,1,0,0");
				break;
			case "delta":
				builder.append("0,0,0,1,0");
				break;
			case "epsilon":
				builder.append("0,0,0,0,1");
				break;
			}
			builder.append("\n");
			
			fileWriter.append(builder.toString());
			
			fileWriter.close();
			
		} catch (IOException e) {
		    System.err.println("Could not write to file!");
		    e.printStackTrace();
		}
		b.resetBoard();
		b.repaint();
	}
	
	public static void main(String[] args) {
		ExampleCreator frame = new ExampleCreator("Draw some examples!");
		frame.setVisible(true);
		frame.setSize(800, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}
