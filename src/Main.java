import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class Main {

    public static KeyManager keyManager;
    public static Display display;
    public static Clock clock;

    public static final int tileSize = 160;
    
    public static final byte east = 0;
    public static final byte south = 1;
    public static final byte west = 2;
    public static final byte north = 3;
    
    public static final byte yellow = 0;
    public static final byte orange = 1;
    public static final byte red = 2;
    public static final byte purple = 3;
    public static final byte blue = 4;
    public static final byte green = 5;
    public static final byte rainbow = 6;
    


    public static Player player;
    public static Hotbar hotbar;
   
    public static Tile highlightedTile;
    public static TileHighlight tileHighlight;
    
    public static HashMap<String, BufferedImage> images;


    public static ArrayList<Renderable> renderList = new ArrayList<>();
  
    public static Tile[][] tileList;
    
    public static boolean triggerEnd = false;
    public static boolean endTriggered = false;

    public static void main(String[] args) {

        keyManager = new KeyManager();
        display = new Display(1920, 1080, keyManager);
        clock = new Clock(144);

        player = new Player(0,0, 160, 160);
        hotbar = new Hotbar();
       
        tileHighlight = new TileHighlight();

        loadImages();
        fillMap();
        
    
        
       
        while (true){
        	
        	if (triggerEnd && !endTriggered) {
            	endTriggered = true;
            	new EndScreen();
        	}
            
            
            pumpsPull();
            
            if (!hotbar.menuOpen()) {
            	highlightedTile = getTile(display.getX(keyManager.getMouseX()), display.getY(keyManager.getMouseY()));
            	player.move();
            	if (keyManager.getKeyClicked(-6)) {
            		hotbar.slide();
            	}
            	if (keyManager.getKeyClicked(-7)) {
            		hotbar.reverseSlide();
            	}
            
            	if (keyManager.getKeyClicked(-1)) {
            		hotbar.interact(highlightedTile);
            	}
            }
            else {
            	hotbar.useMenu();
            }
            
            
            

           
            if (keyManager.getKeyDown(KeyEvent.VK_ESCAPE)){
                break;
            }
            keyManager.update();
            
            display.updatePos();
            display.render();

            clock.waitUntilGameTick();
            
            
            
            
        }
        display.close();


    }

    public static Tile getTile(double x, double y){

        try{
            return tileList[(int)Math.round((x/tileSize) + tileList.length/2)][(int)Math.round((y/tileSize) + tileList[0].length/2)];
        }
        catch(Exception e){
            return null;
        }

    }
    
    public static Tile getTile(double x, double y, byte direction){
    	
    	int xMove;
    	int yMove;
    	
    	if (direction % 2 == 0) {
    		xMove = (direction == 0) ? 1 : -1;
    		yMove = 0;
    	}
    	else {	
    		xMove = 0;
    		yMove = (direction == 1) ? 1 : -1;
    	}

        try{
            return tileList[(int)Math.round((x/tileSize) + tileList.length/2) + xMove][(int)Math.round((y/tileSize) + tileList[0].length/2) + yMove];
        }
        catch(Exception e){
            return null;
        }

    }
    
    public static void loadImages() {
    	
    	images = new HashMap<>();
    	//System.out.println(System.getProperty("user.dir"));
    	
    	try {
    		
    		//player
    		BufferedImage playerImage = ImageIO.read((Main.class.getClassLoader().getResource("mage.png")));
    		for (int i = 0; i < 8; i++) {
    			images.put("mage" + i + "0", playerImage.getSubimage(0, 32 * i, 32, 32));
    			images.put("mage" + i + "1", playerImage.getSubimage(32, 32 * i, 32, 32));
    		}
    		
    		
    		
    		//grass
    		images.put("grass", ImageIO.read((Main.class.getClassLoader().getResource("grass.png"))));
    		
    		//canals
    		BufferedImage canal = ImageIO.read((Main.class.getClassLoader().getResource("canal.png")));
    		for (int i = 0; i < 6; i++) {
    			images.put("canal" + i, canal.getSubimage(40*i, 0, 40, 40));
    			
    		}
    		
    		//gloop
    		BufferedImage gloop = ImageIO.read((Main.class.getClassLoader().getResource("gloop.png")));
    		for (int i = 0; i < 6; i++) {
    			images.put("gloop" + i + "0", gloop.getSubimage(0, 40 * i, 40, 40));
    			images.put("gloop" + i + "1", gloop.getSubimage(40, 40 * i, 40, 40));
    		}
    		for (int i = 0; i < 6; i++) {
    			for (int i2 = 0; i2 < 3; i2++) {
    				images.put("canal_gloop" + i + "0" + i2, images.get("gloop" + i + "0").getSubimage((int)Math.ceil((40*i2)/3.0), 0, (int)((40)/3.0), (int)((40)/3.0)));
    				images.put("canal_gloop" + i + "1" + i2, images.get("gloop" + i + "1").getSubimage((int)Math.ceil((40*i2)/3.0), 0, (int)((40)/3.0), (int)((40)/3.0)));
    			}
    		}
    		
    		//tools
    		BufferedImage tools = ImageIO.read((Main.class.getClassLoader().getResource("tools.png")));
    		for (int i = 0; i < 3; i++) {
    			images.put("hotbar" + i, tools.getSubimage(20*i, 0, 20, 20));
    			
    		}
    		
    		//materials
    		BufferedImage material = ImageIO.read((Main.class.getClassLoader().getResource("material.png")));
    		for (int i = 0; i < 3; i++) {
    			images.put("material" + i, material.getSubimage(20*i, 0, 20, 20));
    			
    		}
    		
    		//deposits
    		BufferedImage deposit = ImageIO.read((Main.class.getClassLoader().getResource("deposit.png")));
    		for (int i = 0; i < 3; i++) {
    			images.put("deposit" + i, deposit.getSubimage(40*i, 0, 40, 40));
    		}
    		
    		//pump
    		BufferedImage pump = ImageIO.read((Main.class.getClassLoader().getResource("pump.png")));
    		for (int i = 0; i < 4; i++) {
    			images.put("pump" + i + "0", pump.getSubimage(0, 40 * i, 40, 40));
    			images.put("pump" + i + "1", pump.getSubimage(40, 40 * i, 40, 40));
    		}
    		images.put("hotbar3", pump.getSubimage(0, 40 * 4, 40, 40));
    		
    		
    		//forge
    		BufferedImage forge = ImageIO.read((Main.class.getClassLoader().getResource("forge.png")));
    		for (int i = 0; i < 3; i++) {
    			for (int i2 = 0; i2 < 7; i2++) {
    				images.put("forge" + i + "" + i2, forge.getSubimage(40*i2, 40 * i, 40, 40));
    			}
    		}
    		images.put("forge30", forge.getSubimage(40*0, 40 * 3, 40, 40));
    		images.put("forge31", forge.getSubimage(40*1, 40 * 3, 40, 40));
    		images.put("forge32", forge.getSubimage(40*2, 40 * 3, 40, 40));
    		
    		images.put("hotbar4", images.get("forge31"));
    		images.put("hotbar5", images.get("forge05"));
    		images.put("hotbar6", images.get("forge15"));
    		images.put("hotbar7", images.get("forge25"));
    		
    		//mixer
    		BufferedImage mixer = ImageIO.read((Main.class.getClassLoader().getResource("mixer.png")));
    		for (int i = 0; i < 3; i++) {
    			for (int i2 = 0; i2 < 4; i2++) {
    				images.put("mixer" + i + "" + i2, mixer.getSubimage(40*i2, 40 * i, 40, 40));
    			}
    		}
    		images.put("hotbar8", images.get("mixer02"));
    		images.put("hotbar9", images.get("mixer12"));
    		images.put("hotbar10", images.get("mixer22"));
    		
    		//rainbow_mixer
    		BufferedImage rainbowMixer = ImageIO.read((Main.class.getClassLoader().getResource("rainbow_mixer.png")));
    		for (int i = 0; i < 3; i++) {
    			for (int i2 = 0; i2 < 24; i2++) {
    				images.put("rainbow_mixer" + i + "" + i2, rainbowMixer.getSubimage(40*i, 40 * i2, 40, 40));
    			}
    		}
    		for (int i = 6; i < 12; i++) {
    			images.put("hotbar11" + (i - 6), images.get("rainbow_mixer2" + (i)));
    		}
    		
    		//thingamabob
    		BufferedImage thingamabob = ImageIO.read((Main.class.getClassLoader().getResource("thingamabob.png")));
    		for (int i = 0; i < 6; i++) {
    			images.put("thingamabob" + i + "0", thingamabob.getSubimage(0, 40*i, 40, 40));
    			images.put("thingamabob" + i + "1", thingamabob.getSubimage(40, 40*i, 40, 40));
    		}
    		for (int i =0; i < 6; i++) {
    			images.put("hotbar12" + (i), images.get("thingamabob" + (i) + "0"));
    		}
    		
    		//button
    		BufferedImage button = ImageIO.read((Main.class.getClassLoader().getResource("button.png")));
    		for (int i = 0; i < 3; i++) {
    			images.put("button" + i + "0", button.getSubimage(32*i, 0, 32, 32));
    			images.put("button" + i + "1", button.getSubimage(32*i, 32, 32, 32));
    		}
    		
    		
    	}
    	catch(Exception e) {
    		//System.out.println(e);
    		e.printStackTrace();
    	}
    }
    
    public static void fillMap() {
    	
    	tileList = new Tile[100][100];
    	
    	for (int x = -tileList.length/2; x < tileList.length/2; x++) {
    		for (int y = -tileList[0].length/2; y < tileList[0].length/2; y++) {
    			new Gloop(x, y, (byte)0);
    		}
    	}
    	
    	for (int x = -tileList.length/2; x < tileList.length/2; x++) {
    		for (int y = -tileList[0].length/2; y < tileList[0].length/2; y++) {
    			if (40 > Math.sqrt(x*x + y*y)) {
    				new Ground(x, y);
    			}
    		}
    	}
    	
    	
    	fillCircle(20, 13, 4, (byte)2);
    	fillCircle(20, -23, 2, (byte)4);
    	fillCircle(-2, 5, 2, (byte)2);
    	fillCircle(-24, -12, 3, (byte)4);
    	fillCircle(14, -12, 3, (byte)2);
    	fillCircle(-14, 0, 5, (byte)4);
    	fillCircle(7, 2, 2, (byte)2);
    	fillCircle(-5, -24, 4, (byte)2);
    	fillCircle(20, -4, 4, (byte)4);
    	fillCircle(4, -6, 3, (byte)4);
    	fillCircle(14, 26, 3, (byte)2);
    	fillCircle(-12, 19, 5, (byte)4);
    	fillCircle(-27, 19, 2, (byte)2);
    	
    	new Pump(4, 2);
    	new Canal(3, 2);
    	new Canal(2, 2);
    	new Forge(1, 2, (byte)3);
    	
    	
    	
    	int randX;
    	int randY;
    	
    	//copper
    	for (int num = 0; num < 70; num++) {
    		do {
        		randX = (int)(Math.random() * tileList.length);
        		randY = (int)(Math.random() * tileList[0].length);
        	}while(!(tileList[randX][randY] instanceof Ground));
        	new Deposit(randX - tileList.length/2, randY - tileList.length/2, (byte)0);
    	}
    	
    	//iron
    	for (int num = 0; num < 30; num++) {
    		do {
        		randX = (int)(Math.random() * tileList.length);
        		randY = (int)(Math.random() * tileList[0].length);
        	}while(!(tileList[randX][randY] instanceof Ground));
        	new Deposit(randX - tileList.length/2, randY - tileList.length/2, (byte)1);
    	}
    	
    	//space metal
    	for (int num = 0; num < 30; num++) {
    		do {
        		randX = (int)(Math.random() * tileList.length);
        		randY = (int)(Math.random() * tileList[0].length);
        	}while(!(tileList[randX][randY] instanceof Ground));
        	new Deposit(randX - tileList.length/2, randY - tileList.length/2, (byte)2);
    	}
    	
    	
    
    	
    }
    
    public static void fillCircle(int centerX, int centerY, int rad, byte gloopType) {
    	for (int x = -tileList.length/2; x < tileList.length/2; x++) {
    		for (int y = -tileList[0].length/2; y < tileList[0].length/2; y++) {
    			if (rad >= Math.sqrt((x-centerX)*(x-centerX) + (y-centerY)*(y-centerY))) {
    				new Gloop(x, y, gloopType);
    			}
    		}
    	}
    }
    
    public static void pumpsPull() {
    	for (Tile[] i : tileList) {
    		for (Tile tile : i) {
    			if (tile instanceof Pump) {
    				((Pump)tile).pullFromSource();
    			}
    		}
    	}
    }
    
    public static double myAtan(double x, double y){
        if (x > 0 && y > 0){
            return Math.atan(Math.abs(y/x));
        }
        else if (x < 0 && y > 0){
            return Math.atan(Math.abs(x/y)) + Math.PI/2;
        }
        else if (x < 0 && y < 0){
            return Math.atan(Math.abs(y/x)) + Math.PI;
        }
        else if (x > 0 && y < 0){
            return Math.atan(Math.abs(x/y)) + Math.PI/2*3;
        }
        else if (x == 0 && y != 0){
            return Math.PI/2 + ((y < 0) ? Math.PI : 0);
        }
        else if (x != 0 && y == 0){
            return ((x < 0) ? Math.PI : 0);
        }
        return 0;
    }
}
