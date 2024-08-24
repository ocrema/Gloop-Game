import java.awt.Graphics;

public class Forge extends Tile{
	
	private byte type;
	private double fuel;
	private double coolent;
	private int maxFuel;
	private int maxCoolent;
	
	public Forge(int x, int y, byte type) {
		super(x, y, false, true);
		this.type = type;
		fuel = 0;
		coolent = 0;
		
		if (type == 3) {
			maxFuel = 2;
			maxCoolent = 1;
		}
		else {
			maxFuel = 2 + 1 * (type+1);
			maxCoolent = 1 + 1 * (type+1);
		}
	}
	
	public void draw(Graphics g) {
		
		int temp;
		
		if (fuel < maxFuel && coolent < maxCoolent) {
			temp = 0;
		}
		else {
			
			if (fuel < maxFuel) {
				temp = 3;
			}
			else if (coolent < maxCoolent) {
				temp = 1;
			}
			else {
				temp = 5;
			}
			
			temp += ((System.currentTimeMillis() / 1000) % 2 == 0) ? 1 : 0;
		}
		
		g.drawImage(Main.images.get("forge" + type + "" + temp), 
				Main.display.getDisplayX(getX()) - Main.tileSize/2, Main.display.getDisplayY(getY()) - Main.tileSize/2, Main.tileSize, Main.tileSize, null);
	}
	
	public void interact() {
		System.out.println("fuel: " + fuel + ", coolent: " + coolent);
	}
	
	
	
	@Override
	public boolean takeGloop(byte type, double amount, byte direction) {
		//System.out.println("0" + type + "" + direction);
		//fuel
		if ((Main.east + 2) % 4 == direction) {
			
			if ((type == Main.red && this.type == 0) ||
					(type == Main.orange && this.type == 1) ||
					(type == Main.orange && this.type == 2) ||
					(type == Main.red && this.type == 3)) {
				if (fuel < maxFuel) {
					fuel += amount;
					return true;
				}
				else {
					return false;
				}
				
			}
			else {
				//explode
				return false;
			}
		}
		//coolent
		else if ((Main.west + 2) % 4 == direction && this.type != 3) {
			//System.out.println("1" + type);
			if ((type == Main.blue && this.type == 0) ||
					(type == Main.blue && this.type == 1) ||
					(type == Main.purple && this.type == 2)) {
				if (coolent < maxCoolent) {
					coolent += amount;
					return true;
				}
				else {
					return false;
				}
				
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
	
	public boolean isFull() {
		
		if (type != 3) {
			return (fuel >= maxFuel && coolent >= maxCoolent);
		}
		else {
			return (fuel >= maxFuel);
		}
	}
	
	

}
