import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.*;
import java.lang.*;
/*
 * A star fades in and out
 */

public class Star {
	final static int MAXRAD=4;
	final static int MINRAD=1;
	final static float [] DIST = {0.0f,1.0f};
	
	int xloc, yloc, minrad,maxrad;
	Color color;
	Point2D center;
	float radius,dec;
	
	//x and y are location, color is the color of the star
	//d is the amount the radius decreases each tick
	//r is the radius
	public Star (int x, int y, Color c, float d, float r,int max,int min){
		color=c;
		xloc=x;
		yloc=y;
		dec=d;
		radius=r;
		center = new Point2D.Float(xloc,yloc);
		minrad=min;
		maxrad=max;
	}
	public void twinkle (){
//		System.out.println(xloc+" "+yloc+" "+radius+" "+dec);
		radius+=dec;
		if (radius<minrad){
			dec*=-1;
			radius=minrad;
		}
		if (radius>maxrad){
			dec*=-1;
			radius=maxrad;
		}
	}
	public void draw (Graphics g){
		Graphics2D g2d=(Graphics2D) g;
		Ellipse2D e = new Ellipse2D.Double(xloc, yloc, radius, radius);
		RadialGradientPaint p = new RadialGradientPaint (center, radius, DIST, new Color []{color,Color.BLACK});
		g2d.setPaint(p);
		g2d.fill(e);
	}
}
