import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;


public class Comet {
	final static float [] DIST = {0.0f,1.0f};
	
	Point2D center; //center of the comet head's location
	int length, xtail,ytail, xtail0,ytail0, xtail1,ytail1; //length is the length of the comet tail, xtail/ytail the coordinate of its ending
	float radius, xmov, ymov, xloc, yloc;
	double angle;
	//radius is the radius of the comet head, xmov/ymov the speed of the object, xloc/yloc its current location
	
	public Comet (int x, int y, int xt, int yt, float xm, float ym,int l, int r,int a){
		angle = a * Math.PI/180;
		xloc=x;
		yloc=y;
		xmov=xm+r;
		if (xmov==0){
			xmov=-8;
		}
		ymov=ym+r;
		length=l;
		radius=r;
		xtail=xt;
		ytail=yt;
		xtail0=(int) (radius*Math.cos(angle)+xloc);
		ytail0=(int) (radius*Math.sin(angle)+yloc);
		xtail1=(int) (radius*Math.cos(-angle)+xloc);
		ytail1=(int) (radius*Math.sin(-angle)+yloc);
		center = new Point2D.Float(xloc-radius,yloc-radius);
	}
	public void trail (){
		xloc+=xmov;
		yloc+=ymov;
		if (calcDist()>length){
			ytail+=ymov;
			xtail+=xmov;
			ytail0+=ymov;
			xtail0+=xmov;
			ytail1+=ymov;
			xtail1+=xmov;
		}
		center = new Point2D.Float(xloc-radius,yloc-radius);
	}
	/*
	 * return if the comet is within the specific bounds marked by x and y distance
	 */
	public boolean notInFrame (int x, int y){
		return (xloc<0 || yloc <0 || xloc>x || yloc > y);
	}
	public float calcDist (){
		double xdist=xloc-xtail;
		double ydist=yloc-ytail;
		return (float) Math.sqrt(xdist*xdist+ydist*ydist);
	}
	public void draw (Graphics g){
		Graphics2D g2d=(Graphics2D) g;
		Ellipse2D e = new Ellipse2D.Double(xloc, yloc, radius, radius);
		RadialGradientPaint p = new RadialGradientPaint (center, radius, DIST, new Color []{Color.ORANGE,Color.WHITE});
		g2d.setPaint(p);
		g2d.fill(e);
		g.setColor(Color.CYAN);
		g.drawLine((int)xloc, (int)yloc, xtail, ytail);
		g.drawLine((int)xloc, (int)yloc, xtail0, ytail0);
		g.drawLine((int)xloc, (int)yloc, xtail1, ytail1);
	}
}
