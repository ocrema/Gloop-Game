import java.awt.Graphics;

public class Deposit extends Tile{
	
	private byte materialType;

	public Deposit(int x, int y, byte materialType) {
		super(x, y, true, false);
		this.materialType = materialType;
	}
	
	public void draw(Graphics g) {
			
		g.drawImage(Main.images.get("deposit" + materialType), Main.display.getDisplayX(getX()) - Main.tileSize/2, Main.display.getDisplayY(getY()) - Main.tileSize/2, Main.tileSize, Main.tileSize, null);
		
	}
	
	public byte getMaterialType() {
		return materialType;
	}
}
