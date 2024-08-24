import java.awt.Graphics;

public class Gloop extends Tile{
	
	private byte gloopType;

	public Gloop(int x, int y, byte gloopType) {
		super(x, y, false, false);
		this.gloopType = gloopType;
	}
	
	public void draw(Graphics g) {
		
		
			
		boolean temp = false;
		if ((System.currentTimeMillis() / 1000) % 2 == 0)
			temp = !temp;
		if ((Math.abs(getX()) + Math.abs(getY()))/Main.tileSize % 2 == 0)
			temp = !temp;
		g.drawImage(Main.images.get("gloop" + gloopType + (temp? 1 : 0)), Main.display.getDisplayX(getX()) - Main.tileSize/2, Main.display.getDisplayY(getY()) - Main.tileSize/2, Main.tileSize, Main.tileSize, null);
			
		
		/*
		if (this == Main.highlightedTile) {
			g.fillRect(Main.display.getDisplayX(getX()) - Main.tileSize/2, Main.display.getDisplayY(getY()) - Main.tileSize/2, Main.tileSize, Main.tileSize);
		}
		*/
	}
	
	public byte getGloopType() {
		return gloopType;
	}
}
