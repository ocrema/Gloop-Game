
import java.awt.Graphics;

public class Thingamabob extends Tile{
	
	boolean charged;
	
	
	public Thingamabob(int x, int y) {
		super(x, y, false, true);
		charged = false;
		
		
	}
	
	public void draw(Graphics g) {
		
		
		if (charged) {
			g.drawImage(Main.images.get("thingamabob" + ((System.currentTimeMillis()/100)%6) + "1"), 
					Main.display.getDisplayX(getX()) - Main.tileSize/2, Main.display.getDisplayY(getY()) - Main.tileSize/2, Main.tileSize, Main.tileSize, null);
		}
		else {
			g.drawImage(Main.images.get("thingamabob" + ((System.currentTimeMillis()/500)%6) + "0"), 
					Main.display.getDisplayX(getX()) - Main.tileSize/2, Main.display.getDisplayY(getY()) - Main.tileSize/2, Main.tileSize, Main.tileSize, null);
		}
		
	}
	
	
	
	
	@Override
	public boolean takeGloop(byte type, double amount, byte direction) {
		
		if (type == Main.rainbow && direction == Main.west) {
			charged = true;
			Main.triggerEnd = true;
		}
		return false;
	}

	
	

}
