import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class EndScreen extends Renderable{
	
	private long startTime;
	private long pause;
	private long transitionTime;
	private long duration;
	
	public EndScreen() {
		super(1000);
		startTime = System.currentTimeMillis();
		pause = 1000 * 2;
		transitionTime = 1000 * 4;
		duration = 1000 * 4;
	}
	
	public void draw(Graphics g) {
		
		double time = System.currentTimeMillis() - startTime;
		
		if (time < pause) {
			
			return;
		}
		time -= pause;
		if (time < transitionTime) {
			g.setColor(new Color(0, 0, 0, (int)((time/transitionTime) *256)));
			g.fillRect(0, 0, Main.display.getWidth(), Main.display.getHeight());

			g.setColor(new Color(255, 255, 255, (int)((time/transitionTime) *256)));
			g.setFont(new Font("Comic Sans", Font.BOLD, 100));
			g.drawString("dub", 500, 500);
			return;
		}
		time -= transitionTime;
		if (time < duration) {
			g.setColor(new Color(0, 0, 0));
			g.fillRect(0, 0, Main.display.getWidth(), Main.display.getHeight());

			g.setColor(new Color(255, 255, 255));
			g.setFont(new Font("Comic Sans", Font.BOLD, 100));
			g.drawString("dub", 500, 500);
			return;
		}
		time -= duration;
		if (time < transitionTime) {
			g.setColor(new Color(0, 0, 0, (int)((time/transitionTime) *256 * -1 + 255)));
			g.fillRect(0, 0, Main.display.getWidth(), Main.display.getHeight());

			g.setColor(new Color(255, 255, 255, (int)((time/transitionTime) *256 * -1 + 255)));
			g.setFont(new Font("Comic Sans", Font.BOLD, 100));
			g.drawString("dub", 500, 500);
			return;
		}
		dispose();
		
	}

}
