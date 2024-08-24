import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Renderable{

	private double x;
	private double y;
	private int width;
	private int height;
   
    private double speed = 3;
    private byte dir;
    
  

    @Override
    public void draw(Graphics g) {
        //g.setColor(Color.RED);
        //g.fillOval(Main.display.getDisplayX(getX()) - getWidth()/2, Main.display.getDisplayY(getY())- getHeight()/2, getWidth(), getHeight());
    	int temp = ((System.currentTimeMillis() / 500) % 2 == 0) ? 1 : 0;
    	g.drawImage(Main.images.get("mage" + dir + temp), Main.display.getDisplayX(x) - width/2, Main.display.getDisplayY(y) - height, width, height, null);
    	

    }

    public Player(double x, double y, int width, int height){
        super(0);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        dir = 0;
    }

    public void move(){
        if (Main.keyManager.getKeyDown(-3)){
            double xDis = (int)Main.display.getX(Main.keyManager.getMouseX()) - x;
            double yDis = (int)Main.display.getY(Main.keyManager.getMouseY()) - y;
            
            double tempX = xDis / Math.sqrt(xDis*xDis + yDis*yDis);
            double tempY = yDis / Math.sqrt(xDis*xDis + yDis*yDis);
            
            Tile tile = Main.getTile(x + tempX*speed, y + tempY*speed);
            
            if (tile != null && tile.isWalkable()) {
            	x += tempX*speed;
            	y += tempY*speed;
            }
            
            setDir(tempX, tempY);
        }
        else {
        	setDir(Main.display.getX(Main.keyManager.getMouseX()) - x, Main.display.getY(Main.keyManager.getMouseY()) - y);
        }
        /*
        if (moveToX != Integer.MAX_VALUE){
        	

        	double xDis = -(x-moveToX);
            double yDis = -(y-moveToY);

        	double dis = Math.sqrt(xDis*xDis + yDis*yDis);
            
            if (dis <= speed){
                x = (moveToX);
                y = (moveToY);
                moveToX = Integer.MAX_VALUE;
                moveToY = Integer.MAX_VALUE;

            }
            else{
                x += (xDis/dis)*speed;
                y += (yDis/dis)*speed;

            }
            
            setDir(xDis, yDis);

        }
        else {
        	setDir(Main.display.getX(Main.keyManager.getMouseX()) - x, Main.display.getY(Main.keyManager.getMouseY()) - y);
        }
        */
        //System.out.println(getX() + " " + getY());
    }
    
    public void setDir(double xDis, double yDis) {
    	
    	double angle = Main.myAtan(xDis, yDis);
    	angle -= Math.toRadians(67.5);
    	if (angle < 0) {
    		angle += 2*Math.PI;
    	}
    	
    	dir = (byte)(angle/(Math.PI/4));
    	
    	
    }
    
    public double getX() {
    	return x;
    }
    
    public double getY() {
    	return y;
    }

   
}
