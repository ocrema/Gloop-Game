import java.awt.Graphics;

public class Pump extends Tile{
	
	private byte orientation;
	
	public Pump(int x, int y) {
		super(x, y, false, true);
		orientation = 0;
	}
	
	public void draw(Graphics g) {
		
		g.drawImage(Main.images.get("pump" + orientation + (((System.currentTimeMillis()/100) % 2 == 0) ? 1 : 0)), Main.display.getDisplayX(getX()) - Main.tileSize/2, Main.display.getDisplayY(getY()) - Main.tileSize/2, Main.tileSize, Main.tileSize, null);
	}
	
	public void interact() {
		orientation++;
		if (orientation == 4) {
			orientation = 0;
		}
	}
	
	public void pullFromSource() {
		
		Tile input = Main.getTile(getX(),  getY(), getInputFace());
		Tile output = Main.getTile(getX(),  getY(), getOutputFace());
		
		if (input != null && output != null) {
			
			if (input instanceof Gloop) {
				output.takeGloop(((Gloop)input).getGloopType(), 3/144.0, getOutputFace());
			}
		}
	}
	
	@Override
	public boolean takeGloop(byte type, double amount, byte direction) {
		
		
		if ((getInputFace() + 2) % 4 == direction) {
			Tile output = Main.getTile(getX(),  getY(), getOutputFace());
			return output.takeGloop(type, amount, getOutputFace());
		}
		return false;
	}
	
	public byte getInputFace() {
		return orientation;
	}
	
	public byte getOutputFace() {
		return (byte)((orientation + 2) % 4);
	}

}
