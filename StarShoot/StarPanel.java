import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class StarPanel extends JPanel {
	final static int XDIM=400;
	final static int YDIM=400;
	final static int DEFAULT_DELAY=90;
	final static int COMET_DELAY=20;
	final static int STAR_MAX=100;
	final static float [] DECREMENTS=new float []{0.02f,-0.02f,0.03f,0.01f,0.04f};
	final static Random random = new Random ();
	
	int gx, gy; //coordinates of the greeting
	private ArrayList <Star> stars;
	private ArrayList <Comet> comets;
	Timer startimer, comettimer; //star timer is for twinkling, comet timer is for comet movement
	Font hbfont = new Font ("Harrington",Font.ITALIC,18);
	
	private String greeting = "Happy Birthday!";
	
	public StarPanel (){
		setPreferredSize(new Dimension(XDIM,YDIM));
		
		gx=-1000;
		gy=-1000;
		
		startimer = new Timer (DEFAULT_DELAY,new StarTimerListener());
		comettimer = new Timer (COMET_DELAY,new CometTimerListener());
		stars = new ArrayList <Star>();
		comets = new ArrayList <Comet>();
		for (int i=0;i<STAR_MAX;i++){
			stars.add(new Star (random.nextInt(XDIM),random.nextInt(YDIM),Color.WHITE,DECREMENTS[random.nextInt(DECREMENTS.length)],random.nextInt(4)+1,random.nextInt(4)+1,1));
		}
		startimer.start();
		comettimer.start();
		
		addMouseMotionListener(new GreetListener());
		addMouseListener(new CometListener());

		setBackground(Color.BLACK);
	}
	//execute the twinkle() function for all stars in array
	public void twinkle (){
		for (int i=0;i<stars.size();i++){
			stars.get(i).twinkle();
		}
	}
	//move all comets and remove comets that have left the screen
	public void trail (){
		for (int i=comets.size()-1;i>=0;i--){
			comets.get(i).trail();
			if (comets.get(i).notInFrame(XDIM, YDIM)){
				comets.remove(i);
			}
		}
	}
	//randomly remove and add a star (maybe according to a count?)
	public void magicStar (){
		if (random.nextDouble()<0.001){
			stars.remove(random.nextInt(stars.size()));
			stars.add(new Star (random.nextInt(XDIM),random.nextInt(YDIM),Color.WHITE,DECREMENTS[random.nextInt(DECREMENTS.length)],random.nextInt(4)+1,random.nextInt(4)+1,1));
		}
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for (int i=0;i<stars.size();i++){
			stars.get(i).draw(g);
		}
		for (int i=0;i<comets.size();i++){
			comets.get(i).draw(g);
		}
		g.setColor(Color.WHITE);
		g.setFont(hbfont);
		g.drawString(greeting, gx, gy);
	}
//	public void mouseClicked(MouseEvent arg0) {
//	}
//	public void mouseEntered(MouseEvent e) {
//		System.out.println("dd");
//		Point p = e.getLocationOnScreen();
//		gx=p.x;
//		gy=p.y;
//	}
//	public void mouseExited(MouseEvent arg0) {
//	}
//	public void mousePressed(MouseEvent arg0) {
//	}
//	public void mouseReleased(MouseEvent arg0) {
//	}
	private class StarTimerListener implements ActionListener {
		public void actionPerformed (ActionEvent event){
			magicStar();
			twinkle();
			repaint();
		}
	}
	private class CometTimerListener implements ActionListener {
		public void actionPerformed (ActionEvent event){
			trail();
			repaint();
		}
	}
	private class GreetListener implements MouseMotionListener {

		public void mouseDragged(MouseEvent arg0) {
		}
		public void mouseMoved(MouseEvent e) {
//			System.out.println("dd");
			Point p = e.getLocationOnScreen();
			gx=p.x;
			gy=p.y;
		}
		
	}
	private class CometListener implements MouseListener {

		public void mouseClicked(MouseEvent arg0) {
			
		}
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent arg0) {
		}
		public void mousePressed(MouseEvent arg0) {
		}
		public void mouseReleased(MouseEvent e) {
			Point p=e.getLocationOnScreen();
			for (int i=0;i<random.nextInt(5)+1;i++){
				comets.add(new Comet(p.x,p.y,p.x,p.y,random.nextInt(13)-7,random.nextInt(4)+1,random.nextInt(6)+10,random.nextInt(3)+1,random.nextInt(31)+15));	
			}
		}
		
	}
}
