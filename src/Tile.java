import java.awt.*;

public abstract class Tile extends Renderable {

	private int x;
	private int y;
	private boolean walkable;
	private boolean machine;
	
    public Tile(int x, int y, boolean walkable, boolean machine){
        super(-10);
        this.x = x * Main.tileSize;
        this.y = y * Main.tileSize;
        this.walkable = walkable;
        this.machine = machine;
        
        if (Main.tileList[x + Main.tileList.length/2][y + Main.tileList[0].length/2] != null) {
        	Main.tileList[x + Main.tileList.length/2][y + Main.tileList[0].length/2].dispose();
        }
        Main.tileList[x + Main.tileList.length/2][y + Main.tileList[0].length/2] = this;

    }

    public abstract void draw(Graphics g);

    public int getX() {
    	return x;
    }
    
    public int getY() {
    	return y;
    }
    
    public boolean isWalkable() {
    	return walkable;
    }
    
    public void interact() {
    	
    }
    
    public boolean takeGloop(byte type, double amount, byte direction) {
    	return false;
    }
}
