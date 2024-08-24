import java.awt.Graphics;

import java.util.Arrays;

public class Canal extends Tile{
	
	private byte orientation;
	private byte type;
	private double[] saturation;
	
	public Canal(int x, int y) {
		super(x, y, true, true);
		orientation = 0;
		type = -1;
		saturation = new double[3];
	}
	
	public void draw(Graphics g) {
		
		g.drawImage(Main.images.get("canal" + orientation), Main.display.getDisplayX(getX()) - Main.tileSize/2, Main.display.getDisplayY(getY()) - Main.tileSize/2, Main.tileSize, Main.tileSize, null);
		
		int xOffset = 1;
		int yOffset = 1;
		
		
		byte[] faces = getFaces();
		if (saturation[0] > 0) {
			
			if (faces[0] % 2 == 0) {
	    		xOffset += (faces[0] == 0) ? 1 : -1;
	    	
	    	}
	    	else {	
	    		yOffset += (faces[0] == 1) ? 1 : -1;
	    	}
			
			if (type != Main.rainbow) {
				g.drawImage(Main.images.get("canal_gloop" + type + (((System.currentTimeMillis() / 1000) % 2 == 0)? 1 : 0) + "0"), 
						Main.display.getDisplayX(getX()) - Main.tileSize/2 + (xOffset*Main.tileSize)/3, 
						Main.display.getDisplayY(getY()) - Main.tileSize/2 + (yOffset*Main.tileSize)/3, 
						(int)Math.ceil(Main.tileSize/3.0), (int)Math.ceil(Main.tileSize/3.0), null);
			}
			else {
				g.drawImage(Main.images.get("canal_gloop" + ((System.currentTimeMillis()/500)%6) + (((System.currentTimeMillis() / 1000) % 2 == 0)? 1 : 0) + "0"), 
						Main.display.getDisplayX(getX()) - Main.tileSize/2 + (xOffset*Main.tileSize)/3, 
						Main.display.getDisplayY(getY()) - Main.tileSize/2 + (yOffset*Main.tileSize)/3, 
						(int)Math.ceil(Main.tileSize/3.0), (int)Math.ceil(Main.tileSize/3.0), null);
			}
			
			
			xOffset = 1;
			yOffset = 1;
		}
		
		if (saturation[1] > 0) {
			if (type != Main.rainbow) {
				g.drawImage(Main.images.get("canal_gloop" + type + (((System.currentTimeMillis() / 1000) % 2 == 0)? 1 : 0) + "1"), 
					Main.display.getDisplayX(getX()) - Main.tileSize/2 + (xOffset*Main.tileSize)/3, 
					Main.display.getDisplayY(getY()) - Main.tileSize/2 + (yOffset*Main.tileSize)/3, 
					(int)Math.ceil(Main.tileSize/3.0), (int)Math.ceil(Main.tileSize/3.0), null);
			}
			else {
				g.drawImage(Main.images.get("canal_gloop" + ((System.currentTimeMillis()/500)%6) + (((System.currentTimeMillis() / 1000) % 2 == 0)? 1 : 0) + "1"), 
						Main.display.getDisplayX(getX()) - Main.tileSize/2 + (xOffset*Main.tileSize)/3, 
						Main.display.getDisplayY(getY()) - Main.tileSize/2 + (yOffset*Main.tileSize)/3, 
						(int)Math.ceil(Main.tileSize/3.0), (int)Math.ceil(Main.tileSize/3.0), null);
			}
		}
		
		if (saturation[2] > 0) {
			
			if (faces[1] % 2 == 0) {
	    		xOffset += (faces[1] == 0) ? 1 : -1;
	    	
	    	}
	    	else {	
	    		yOffset += (faces[1] == 1) ? 1 : -1;
	    	}
			
			if (type != Main.rainbow) {
				g.drawImage(Main.images.get("canal_gloop" + type + (((System.currentTimeMillis() / 1000) % 2 == 0)? 1 : 0) + "2"), 
					Main.display.getDisplayX(getX()) - Main.tileSize/2 + (xOffset*Main.tileSize)/3, 
					Main.display.getDisplayY(getY()) - Main.tileSize/2 + (yOffset*Main.tileSize)/3, 
					(int)Math.ceil(Main.tileSize/3.0), (int)Math.ceil(Main.tileSize/3.0), null);
			}
			else {
				g.drawImage(Main.images.get("canal_gloop" + ((System.currentTimeMillis()/500)%6) + (((System.currentTimeMillis() / 1000) % 2 == 0)? 1 : 0) + "2"), 
						Main.display.getDisplayX(getX()) - Main.tileSize/2 + (xOffset*Main.tileSize)/3, 
						Main.display.getDisplayY(getY()) - Main.tileSize/2 + (yOffset*Main.tileSize)/3, 
						(int)Math.ceil(Main.tileSize/3.0), (int)Math.ceil(Main.tileSize/3.0), null);
			}
			
			xOffset = 1;
			yOffset = 1;
		}
	}
	
	public void interact() {
		if (saturation[0] == 0 && saturation[1] == 0 && saturation[2] == 0) {
			orientation++;
			if (orientation == 6) {
				orientation = 0;
			}
		}
	}
	
	@Override
	public boolean takeGloop(byte type, double amount, byte direction) {
		
		byte inputDir = (byte)((direction + 2) % 4);
		
		boolean temp = false;
		for (byte i : getFaces()) {
			if (i == inputDir) {
				temp = true;
			}
		}
		
		if (temp) {
			if (this.type == type || this.type == -1) {
				this.type = type;
				
				boolean filled = true;
				
				if (inputDir == getFaces()[0]) {
					for (int i = 0; i < 3; i++) {
						if (saturation[i] < 1) {
							saturation[i] += amount;
							filled = false;
							break;
						}
					}
				}
				else {
					for (int i = 2; i >= 0; i--) {
						if (saturation[i] < 1) {
							saturation[i] += amount;
							filled = false;
							break;
						}
					}
				}
				
				if (filled) {
					
					byte outputDir = (getFaces()[0] == inputDir) ? getFaces()[1] : getFaces()[0];
					Tile output = Main.getTile(getX(), getY(), outputDir);
					return output.takeGloop(type, amount, outputDir);
				}
				else {
					return true;
				}
				
				
			}
			else {
				//blow up
				return false;
			}
		}
		return false;
		
	}
	
	public byte[] getFaces() {
		
		switch(orientation) {
		case 0:
			return new byte[]{0, 2};
		case 1:
			return new byte[]{1, 3};
		case 2:
			return new byte[]{0, 1};
		case 3:
			return new byte[]{1, 2};
		case 4:
			return new byte[]{2, 3};
		case 5:
			return new byte[]{0, 3};
		}
		return null;
		
		
	}

}
