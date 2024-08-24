
import java.awt.Graphics;

public class RainbowMixer extends Tile{
	
	private byte type;
	private double[] gloop;
	private double maxGloop;
	
	
	public RainbowMixer(int x, int y, byte type) {
		super(x, y, false, true);
		this.type = type;
		if (type == 2) {
			gloop = new double[6];
		}
		
		maxGloop = 1;
		
	}
	
	public void draw(Graphics g) {
		
		int temp = (int)((((System.currentTimeMillis()/500)%6) + 12 * ((System.currentTimeMillis()/100)%2) + (isFilled()?6:0)));
		
		g.drawImage(Main.images.get("rainbow_mixer" + type + "" + temp), 
				Main.display.getDisplayX(getX()) - Main.tileSize/2, Main.display.getDisplayY(getY()) - Main.tileSize/2, Main.tileSize, Main.tileSize, null);
	}
	
	
	
	
	@Override
	public boolean takeGloop(byte type, double amount, byte direction) {
		
		switch(this.type) {
		case 0:
			
			if ((direction == Main.west && type == Main.yellow) ||
					(direction == Main.east && type == Main.purple)) {
				Main.getTile(getX(), getY(), Main.south).takeGloop(type, amount, Main.south);
			}
			
			
			break;
		case 1:
			
			if ((direction == Main.west && type == Main.orange) ||
					(direction == Main.east && type == Main.blue ||
					direction == Main.south)) {
				Main.getTile(getX(), getY(), Main.south).takeGloop(type, amount, Main.south);
			}
			
			
			break;
		case 2:
			
			if ((direction == Main.west && type == Main.red) ||
					(direction == Main.east && type == Main.green ||
					direction == Main.south)) {
				gloop[type] += amount;
				
				double min = Double.MAX_VALUE;
				for (double i : gloop) {
					if (i < min) {
						min = i;
					}
				}
				
				if (min > maxGloop/2) {
					for (int i = 0; i < 6; i++) {
						gloop[i] -= min - maxGloop/2;
					}
					
					Main.getTile(getX(), getY(), Main.south).takeGloop(Main.rainbow, min - maxGloop/2, Main.south);
				}
			}
			break;
		}
		
		return false;
		
		
		
		
		/*
		//fuel
		if ((Main.east + 2) % 4 == direction) {
			
			if ((type == Main.red && this.type == 0) ||
					(type == Main.red && this.type == 1) ||
					(type == Main.blue && this.type == 2)) {
				if (gloop1 < maxGloop) {
					gloop1 += amount;
				}
				if (gloop1 > maxGloop/2 && gloop2 > maxGloop/2) {
					double amnt = Math.min(gloop1, gloop2);
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
					double amnt = Math.min(gloop1, gloop2);
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
		*/
	}

	public byte getType() {
		
		return type;
	}
	
	private boolean isFilled() {
		
		if (type != 2) {
			return ((RainbowMixer)Main.getTile(getX(), getY(), Main.south)).isFilled();
		}
		
		
		for (int i = 0; i < 6; i++) {
			if (gloop[i] < maxGloop/2) {
				return false;
			}
		}
		return true;
	}
	
	

}
