import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Hotbar extends Renderable{

	private int pos;
	private int[] amount;
	private int[] materials;
	private CraftingMenu craftingMenu;
	
	public Hotbar() {
		super(5.0);
		pos = 0;
		amount = new int[13];
		amount[0] = -1;
		amount[1] = -1;
		amount[2] = -1;
		/*
		amount[3] = 100;
		
		amount[4] = 100;
		amount[5] = 100;
		amount[6] = 100;
		amount[7] = 100;
		
		amount[8] = 100;
		amount[9] = 100;
		amount[10] = 100;
		
		amount[11] = 100;
		amount[12] = 100;
		*/
		
		materials = new int[3];
	}
	
	public void slide() {
		do {
			pos++;
			if (pos == amount.length) {
				pos = 0;
			}
		}while (amount[pos] == 0);
	}
	
	public void reverseSlide() {
		do {
			pos--;
			if (pos == -1) {
				pos = amount.length-1;
			}
		}while (amount[pos] == 0);
	}
	
	public void draw(Graphics g) {
	
		
		g.setFont(new Font("Comic Sans", Font.BOLD, 50));
		g.setColor(Color.white);
		
		g.drawImage(Main.images.get("material0"), 150, Main.display.getHeight() - 160, 160, 160, null);
		
		g.drawString(Integer.toString(materials[0]), 320,  Main.display.getHeight() - 60);
		
		g.drawImage(Main.images.get("material1"), 400, Main.display.getHeight() - 160, 160, 160, null);
		
		g.drawString(Integer.toString(materials[1]), 570,  Main.display.getHeight() - 60);
		
		g.drawImage(Main.images.get("material2"), 650, Main.display.getHeight() - 160, 160, 160, null);
		
		g.drawString(Integer.toString(materials[2]), 830,  Main.display.getHeight() - 60);
		
		g.setFont(new Font("Comic Sans", Font.BOLD, 70));
		
		if (pos != 11 && pos != 12) {
			g.drawImage(Main.images.get("hotbar" + pos), Main.display.getWidth() - 400, Main.display.getHeight() - 260, 260, 260, null);
		}
		else {
			g.drawImage(Main.images.get("hotbar" + pos + "" + ((System.currentTimeMillis() / 500) % 6)), Main.display.getWidth() - 400, Main.display.getHeight() - 260, 260, 260, null);
		}
		
		if (amount[pos] != -1) {
			g.drawString(Integer.toString(amount[pos]), Main.display.getWidth() - 170,  Main.display.getHeight() - 60);
		}
		
		
		
	}
	
	public void interact(Tile tile) {
		
		switch(pos) {
		
		case 0:
			//wrench
			tile.interact();
			if (tile instanceof Forge && ((Forge)tile).isFull()) {
				craftingMenu = new CraftingMenu(((Forge)tile).getType());
			}
			
			break;
			
		case 1:
			//hammer
			if (!(tile instanceof Ground) && !(tile instanceof Gloop)) {
				if (tile instanceof Deposit) {
					materials[((Deposit)tile).getMaterialType()]++;
				}
				else if (tile instanceof Pump) {
					amount[3]++;
				}
				else if (tile instanceof Forge) {
					if (((Forge)tile).getType() != 3) {
						amount[5 + ((Forge)tile).getType()]++;
					}
					else {
						amount[4]++;
					}
				}
				else if (tile instanceof Mixer) {
					amount[8 + ((Mixer)tile).getType()]++;
				}
				else if (tile instanceof RainbowMixer) {
					Tile tile2 = null;
					Tile tile3 = null;
					switch(((RainbowMixer)tile).getType()) {
			
					case 0:
						tile2 = Main.getTile(tile.getX(), tile.getY(), Main.south);
						tile3 = Main.getTile(tile2.getX(), tile2.getY(), Main.south);
						break;
					case 1:
						tile2 = Main.getTile(tile.getX(), tile.getY(), Main.north);
						tile3 = Main.getTile(tile.getX(), tile.getY(), Main.south);
						break;
					case 2:
						tile2 = Main.getTile(tile.getX(), tile.getY(), Main.north);
						tile3 = Main.getTile(tile2.getX(), tile2.getY(), Main.north);
						break;
					}
					new Ground(tile2.getX() / Main.tileSize, tile2.getY() / Main.tileSize);
					new Ground(tile3.getX() / Main.tileSize, tile3.getY() / Main.tileSize);
					amount[11]++;
				
				}
				else if (tile instanceof Thingamabob) {
					amount[12]++;
				}
				new Ground(tile.getX() / Main.tileSize, tile.getY() / Main.tileSize);
			}
			break;
			
			
			
		case 2:
			//digger
			if (tile instanceof Ground) {
				new Canal(tile.getX() / Main.tileSize, tile.getY() / Main.tileSize);
			}
			break;
			
		case 3:
			//pump
			if (tile instanceof Ground && Main.getTile(Main.player.getX(), Main.player.getY()) != tile) {
				new Pump(tile.getX() / Main.tileSize, tile.getY() / Main.tileSize);
				amount[pos]--;
			}
			break;
			
		case 4:
			//stone forge
			if (tile instanceof Ground && Main.getTile(Main.player.getX(), Main.player.getY()) != tile) {
				new Forge(tile.getX() / Main.tileSize, tile.getY() / Main.tileSize, (byte)3);
				amount[pos]--;
			}
			break;
			
		case 5:
			//copper forge
			if (tile instanceof Ground && Main.getTile(Main.player.getX(), Main.player.getY()) != tile) {
				new Forge(tile.getX() / Main.tileSize, tile.getY() / Main.tileSize, (byte)0);
				amount[pos]--;
			}
			break;
			
		case 6:
			//iron forge
			if (tile instanceof Ground && Main.getTile(Main.player.getX(), Main.player.getY()) != tile) {
				new Forge(tile.getX() / Main.tileSize, tile.getY() / Main.tileSize, (byte)1);
				amount[pos]--;
			}
			break;
			
		case 7:
			//space metal forge
			if (tile instanceof Ground && Main.getTile(Main.player.getX(), Main.player.getY()) != tile) {
				new Forge(tile.getX() / Main.tileSize, tile.getY() / Main.tileSize, (byte)2);
				amount[pos]--;
			}
			break;
			
		case 8:
			//copper mixer
			if (tile instanceof Ground && Main.getTile(Main.player.getX(), Main.player.getY()) != tile) {
				new Mixer(tile.getX() / Main.tileSize, tile.getY() / Main.tileSize, (byte)0);
				amount[pos]--;
			}
			break;
			
		case 9:
			//iron mixer
			if (tile instanceof Ground && Main.getTile(Main.player.getX(), Main.player.getY()) != tile) {
				new Mixer(tile.getX() / Main.tileSize, tile.getY() / Main.tileSize, (byte)1);
				amount[pos]--;
			}
			break;
			
		case 10:
			//space metal mixer
			if (tile instanceof Ground && Main.getTile(Main.player.getX(), Main.player.getY()) != tile) {
				new Mixer(tile.getX() / Main.tileSize, tile.getY() / Main.tileSize, (byte)2);
				amount[pos]--;
			}
			break;
			
		case 11:
			//rainbow mixer
			Tile tile2 = Main.getTile(tile.getX(), tile.getY(), Main.north);
			Tile tile3 = Main.getTile(tile2.getX(), tile2.getY(), Main.north);
			if ((tile instanceof Ground && Main.getTile(Main.player.getX(), Main.player.getY()) != tile) &&
					(tile2 instanceof Ground && Main.getTile(Main.player.getX(), Main.player.getY()) != tile2) &&
					(tile3 instanceof Ground && Main.getTile(Main.player.getX(), Main.player.getY()) != tile3)) {
				
				new RainbowMixer(tile.getX() / Main.tileSize, tile.getY() / Main.tileSize, (byte)2);
				new RainbowMixer(tile2.getX() / Main.tileSize, tile2.getY() / Main.tileSize, (byte)1);
				new RainbowMixer(tile3.getX() / Main.tileSize, tile3.getY() / Main.tileSize, (byte)0);
				amount[pos]--;
			}
			break;
			
		case 12:
			//thingamabob
			if (tile instanceof Ground && Main.getTile(Main.player.getX(), Main.player.getY()) != tile) {
				new Thingamabob(tile.getX() / Main.tileSize, tile.getY() / Main.tileSize);
				amount[pos]--;
			}
			break;
		
		}
		
		if (amount[pos] == 0) {
			reverseSlide();
		}
	}
	
	public boolean menuOpen() {
		return craftingMenu != null;
	}
	
	public void closeMenu() {
		craftingMenu.dispose(); 
		craftingMenu = null;
	}
	
	public void useMenu() {
		craftingMenu.use();
	}
	
	public boolean useMaterial(byte mat, int amnt) {
		if (materials[mat] >= amnt) {
			materials[mat] -= amnt;
			return true;
		}
		return false;
	}
	
	public void addItem(int i) {
		amount[i]++;
	}
}
