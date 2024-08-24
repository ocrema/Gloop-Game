
import java.awt.Graphics;

public class Ground extends Tile{
	
	public Ground(int x, int y) {
		super(x, y, true, false);
	}
	
	public void draw(Graphics g) {
		
		
		g.drawImage(Main.images.get("grass"), Main.display.getDisplayX(getX()) - Main.tileSize/2, Main.display.getDisplayY(getY()) - Main.tileSize/2, Main.tileSize, Main.tileSize, null);
		
	
		
	}
	
	@Override
	public boolean takeGloop(byte type, double amount, byte direction) {
		
		return false;
	}
	
	
}
