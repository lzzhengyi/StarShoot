import java.awt.*;
import javax.swing.*;



public class StarFrame extends JFrame {

	public StarFrame (){
		setLayout(new BorderLayout());
		
		BotPanel bp = new BotPanel();
		StarPanel sp=new StarPanel();
//		sp.setPreferredSize(new Dimension(400,500));
//		bp.setPreferredSize(preferredSize)
		add(sp, BorderLayout.CENTER);
		add(bp, BorderLayout.SOUTH);
	}
	private class BotPanel extends JPanel{
		public BotPanel (){
			setPreferredSize(new Dimension(400,100));
		}
		public void paintComponent (Graphics page){
			super.paintComponent(page);
			Graphics2D g2d=(Graphics2D) page;
			int w=getWidth();
			int h=getHeight();
			
			GradientPaint gp = new GradientPaint (
					0,0,Color.DARK_GRAY,
					0,h,Color.WHITE);
			g2d.setPaint (gp);
			g2d.fillRect(0, 0, w, h);
		}
		
	}

}
