
import java.awt.Graphics;

public class Mixer extends Tile{
	
	private byte type;
	private double gloop1;
	private double gloop2;
	private double maxGloop;
	
	
	public Mixer(int x, int y, byte type) {
		super(x, y, false, true);
		this.type = type;
		gloop1 = 0;
		gloop2 = 0;
		maxGloop = 1;
		
	}
	
	public void draw(Graphics g) {
		
		
		
		g.drawImage(Main.images.get("mixer" + type + "" + (((System.currentTimeMillis()/100)%2) + ((gloop1 > 0 && gloop2 > 0)?2:0))), 
				Main.display.getDisplayX(getX()) - Main.tileSize/2, Main.display.getDisplayY(getY()) - Main.tileSize/2, Main.tileSize, Main.tileSize, null);
	}
	
	
	
	
	@Override
	public boolean takeGloop(byte type, double amount, byte direction) {
		
		//fuel
		if ((Main.east + 2) % 4 == direction) {
			
			if ((type == Main.red && this.type == 0) ||
					(type == Main.red && this.type == 1) ||
					(type == Main.blue && this.type == 2)) {
				if (gloop1 < maxGloop) {
					gloop1 += amount;
				}
				if (gloop1 > maxGloop/2 && gloop2 > maxGloop/2) {
					double amnt = Math.min(gloop1, gloop2) - maxGloop/2;
					byte color = 0;
					if (this.type == 0) {
						color = Main.purple;
					}
					else if (this.type == 1) {
						color = Main.orange;
					}
					else if (this.type == 2) {
						color = Main.green;
					}
					gloop1 -= amnt;
					gloop2 -= amnt;
					return Main.getTile(getX(), getY(), Main.south).takeGloop(color, amnt, Main.south);
				}
				return false;
				
			}
			else {
				//explode
				return false;
			}
		}
		//coolent
		else if ((Main.west + 2) % 4 == direction) {
			if ((type == Main.blue && this.type == 0) ||
					(type == Main.yellow && this.type == 1) ||
					(type == Main.yellow && this.type == 2)) {
				if (gloop2 < maxGloop) {
					gloop2 += amount;
				}
				if (gloop1 > maxGloop/2 && gloop2 > maxGloop/2) {
					double amnt = Math.min(gloop1, gloop2) - maxGloop/2;
					byte color = 0;
					if (this.type == 0) {
						color = Main.purple;
					}
					else if (this.type == 1) {
						color = Main.orange;
					}
					else if (this.type == 2) {
						color = Main.green;
					}
					gloop1 -= amnt;
					gloop2 -= amnt;
					return Main.getTile(getX(), getY(), Main.south).takeGloop(color, amnt, Main.south);
				}
				return false;
				
			}
			else {
				//explode
				return false;
			}
		}
		return false;
	}

	public byte getType() {
		
		return type;
	}
	
	

}
