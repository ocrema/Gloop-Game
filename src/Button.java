import java.awt.Graphics;

public class Button extends Renderable{
	
	private int x;
	private int y;
	private int width;
	private int height;
	private byte type;
	
	private long pressTime;

	public Button(int x, int y, byte type) {
		super(177);
		this.x = x;
		this.y = y;
		this.width = 128;
		this.height = 128;
		this.type = type;
		
		
	}

	@Override
	public void draw(Graphics g) {
		
			
		if (pressTime > System.currentTimeMillis()) {
			g.drawImage(Main.images.get("button2" + type), x - width/2, y - width/2, width, height, null);
		}
		else if (isHovered()) {
			g.drawImage(Main.images.get("button1" + type), x - width/2, y - width/2, width, height, null);
		}
		else {
			g.drawImage(Main.images.get("button0" + type), x - width/2, y - width/2, width, height, null);
		}
		
		
		
	
	}
	
	public boolean isClicked() {
		
		if (Main.keyManager.getKeyClicked(-1) && isHovered()) {
			pressTime = System.currentTimeMillis() + 400;
			return true;
		}
		return false;
	}
	
	public boolean isHovered() {
		return (Main.keyManager.getMouseX() > x - width/2 && Main.keyManager.getMouseX() < x + width/2 &&
				Main.keyManager.getMouseY() > y - height/2 && Main.keyManager.getMouseY() < y + height/2);
	}
	
	

}
