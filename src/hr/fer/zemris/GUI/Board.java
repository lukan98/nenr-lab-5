package hr.fer.zemris.GUI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import hr.fer.zemris.neural.Point;

public class Board extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Point> points;
	
	public Board() {
		this.setBackground(Color.WHITE);
		
		this.addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e) {
				points = new ArrayList<>();
				points.add(new Point(e.getX(), e.getY()));
			}
			
//			public void mouseReleased(MouseEvent e) {
//				List<Point> transformedPoints = Point.getRepresentativePoints(points, 25);
//				points = transformedPoints;
//				repaint();
//			}
			
		});
		
		this.addMouseMotionListener(new MouseMotionAdapter() {
			
			public void mouseDragged(MouseEvent e) {
				points.add(new Point(e.getX(), e.getY()));
				repaint();
			}
			
		});
		
	}
	
	public void resetBoard() {
		this.points = null;
	}
	
	public List<Point> getPoints() {
		return this.points;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		if(points!=null) {
			Point p0 = points.get(0);
			
			for (int i=1; i<points.size(); i++) {
				Point p1 = points.get(i);
				
				g.drawLine((int) p0.getX(), (int) p0.getY(), (int) p1.getX(), (int) p1.getY());
				
				p0 = p1;
			}
		}
		
	}
	
	
}
