import java.awt.*;

public class TileHighlight extends Renderable{

    public TileHighlight(){
        super(2);
    }

    public void draw(Graphics g){
        if (Main.highlightedTile != null){
        	
        	int thickness = 10;
        	
        	int x = Main.display.getDisplayX(Main.highlightedTile.getX());
        	int y = Main.display.getDisplayY(Main.highlightedTile.getY());
            g.setColor(Color.blue);
            
            g.fillRect(x-thickness-Main.tileSize/2, y-thickness-Main.tileSize/2, thickness, Main.tileSize + thickness * 2);
            
            g.fillRect(x+Main.tileSize/2, y-thickness-Main.tileSize/2, thickness, Main.tileSize + thickness * 2);
            
            g.fillRect(x-thickness-Main.tileSize/2, y-thickness-Main.tileSize/2, Main.tileSize + thickness * 2, thickness);
            
            g.fillRect(x-Main.tileSize/2, y+Main.tileSize/2, Main.tileSize, thickness);
        }
    }
}
