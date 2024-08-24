import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class CraftingMenu extends Renderable{
	
	private Button[] buttons;
	private byte[] material;
	private byte[] item;
	private int width;
	private int height;
	private int itemSize;
	private int materialSize;
	//private boolean active;
	private byte type;

	public CraftingMenu(byte type) {
		super(106);
		this.type = type;
		width = 900;
		height = 700;
		
		if (type == 3) {
			buttons = new Button[4];
			buttons[0] = new Button(Main.display.getWidth()/2 + 300, Main.display.getHeight()/2 - 300, (byte)1);
			buttons[1] = new Button(Main.display.getWidth()/2 - 300, Main.display.getHeight()/2 + 300, (byte)0);
			buttons[2] = new Button(Main.display.getWidth()/2, Main.display.getHeight()/2 + 300, (byte)0);
			buttons[3] = new Button(Main.display.getWidth()/2 + 300, Main.display.getHeight()/2 + 300, (byte)0);
		}
		else {
			buttons = new Button[3];
			buttons[0] = new Button(Main.display.getWidth()/2 + 300, Main.display.getHeight()/2 - 300, (byte)1);
			buttons[1] = new Button(Main.display.getWidth()/2 - 200, Main.display.getHeight()/2 + 300, (byte)0);
			buttons[2] = new Button(Main.display.getWidth()/2 + 200, Main.display.getHeight()/2 + 300, (byte)0);
		}

	}

	@Override
	public void draw(Graphics g) {
		
		g.setColor(new Color(0, 0, 0));
		g.fillRect(Main.display.getWidth()/2 - width/2 - 4, Main.display.getHeight()/2 - height/2 - 4, width + 8, height + 8);
		
		g.setColor(new Color(185, 185, 185));
		g.fillRect(Main.display.getWidth()/2 - width/2, Main.display.getHeight()/2 - height/2, width, height);
		
		g.setFont(new Font("Comic Sans", Font.BOLD, 70));
		g.setColor(Color.white);
		
		switch (type) {
		case 3:
			g.drawImage(Main.images.get("hotbar3"), Main.display.getWidth()/2 - 300 - 100, Main.display.getHeight()/2 - 200, 200, 200, null);
			g.drawImage(Main.images.get("material0"), Main.display.getWidth()/2 - 300 - 100, Main.display.getHeight()/2, 200, 200, null);
			g.drawString("2", Main.display.getWidth()/2 - 300 - 100 + 150, Main.display.getHeight()/2 + 200);
			
			g.drawImage(Main.images.get("hotbar5"), Main.display.getWidth()/2 - 100, Main.display.getHeight()/2 - 200, 200, 200, null);
			g.drawImage(Main.images.get("material0"), Main.display.getWidth()/2 - 100, Main.display.getHeight()/2, 200, 200, null);
			g.drawString("5", Main.display.getWidth()/2 - 100 + 150, Main.display.getHeight()/2 + 200);
			
			g.drawImage(Main.images.get("hotbar8"), Main.display.getWidth()/2 + 300 - 100, Main.display.getHeight()/2 - 200, 200, 200, null);
			g.drawImage(Main.images.get("material0"), Main.display.getWidth()/2 + 300 - 100, Main.display.getHeight()/2, 200, 200, null);
			g.drawString("5", Main.display.getWidth()/2 + 300 - 100 + 150, Main.display.getHeight()/2 + 200);
			break;
		case 0:
			g.drawImage(Main.images.get("hotbar6"), Main.display.getWidth()/2 - 200 - 100, Main.display.getHeight()/2 - 200, 200, 200, null);
			g.drawImage(Main.images.get("material1"), Main.display.getWidth()/2 - 200 - 100, Main.display.getHeight()/2, 200, 200, null);
			g.drawString("5", Main.display.getWidth()/2 - 200 - 100 + 150, Main.display.getHeight()/2 + 200);
			
		
			
			g.drawImage(Main.images.get("hotbar9"), Main.display.getWidth()/2 + 200 - 100, Main.display.getHeight()/2 - 200, 200, 200, null);
			g.drawImage(Main.images.get("material1"), Main.display.getWidth()/2 + 200 - 100, Main.display.getHeight()/2, 200, 200, null);
			g.drawString("5", Main.display.getWidth()/2 + 200 - 100 + 150, Main.display.getHeight()/2 + 200);
			break;
		case 1:
			g.drawImage(Main.images.get("hotbar7"), Main.display.getWidth()/2 - 200 - 100, Main.display.getHeight()/2 - 200, 200, 200, null);
			g.drawImage(Main.images.get("material2"), Main.display.getWidth()/2 - 200 - 100, Main.display.getHeight()/2, 200, 200, null);
			g.drawString("5", Main.display.getWidth()/2 - 200 - 100 + 150, Main.display.getHeight()/2 + 200);
			
		
			
			g.drawImage(Main.images.get("hotbar10"), Main.display.getWidth()/2 + 200 - 100, Main.display.getHeight()/2 - 200, 200, 200, null);
			g.drawImage(Main.images.get("material2"), Main.display.getWidth()/2 + 200 - 100, Main.display.getHeight()/2, 200, 200, null);
			g.drawString("5", Main.display.getWidth()/2 + 200 - 100 + 150, Main.display.getHeight()/2 + 200);
			break;
		case 2:
			g.drawImage(Main.images.get("hotbar11" + (System.currentTimeMillis()/500)%6), Main.display.getWidth()/2 - 200 - 100, Main.display.getHeight()/2 - 200, 200, 200, null);
			g.drawImage(Main.images.get("material2"), Main.display.getWidth()/2 - 200 - 100, Main.display.getHeight()/2, 200, 200, null);
			g.drawString("10", Main.display.getWidth()/2 - 200 - 100 + 150, Main.display.getHeight()/2 + 200);
			
		
			
			g.drawImage(Main.images.get("hotbar12" + (System.currentTimeMillis()/500)%6), Main.display.getWidth()/2 + 200 - 100, Main.display.getHeight()/2 - 200, 200, 200, null);
			g.drawImage(Main.images.get("material2"), Main.display.getWidth()/2 + 200 - 100, Main.display.getHeight()/2, 200, 200, null);
			g.drawString("2", Main.display.getWidth()/2 + 200 - 100 + 150, Main.display.getHeight()/2 + 200);
			break;
		}
		
		
	}
	
	@Override
	public void dispose() {
		for (Button b : buttons) {
			if (b != null) {
				b.dispose();
			}
		}
		Main.renderList.remove(this);
	}

	public void use() {
		//System.out.println(buttons[0]);
		if (buttons[0].isClicked()) {
			Main.hotbar.closeMenu();
			return;
		}
		
		
		switch(type) {
		case 3:
			if (buttons[1].isClicked() && Main.hotbar.useMaterial((byte)0, 2)) {
				Main.hotbar.addItem(3);
			}
			if (buttons[2].isClicked() && Main.hotbar.useMaterial((byte)0, 5)) {
				Main.hotbar.addItem(5);
			}
			if (buttons[3].isClicked() && Main.hotbar.useMaterial((byte)0, 5)) {
				Main.hotbar.addItem(8);
			}
			break;
		case 0:
			if (buttons[1].isClicked() && Main.hotbar.useMaterial((byte)1, 5)) {
				Main.hotbar.addItem(6);
			}
			if (buttons[2].isClicked() && Main.hotbar.useMaterial((byte)1, 5)) {
				Main.hotbar.addItem(9);
			}
			break;
		case 1:
			if (buttons[1].isClicked() && Main.hotbar.useMaterial((byte)2, 5)) {
				Main.hotbar.addItem(7);
			}
			if (buttons[2].isClicked() && Main.hotbar.useMaterial((byte)2, 5)) {
				Main.hotbar.addItem(10);
			}
			break;
		case 2:
			if (buttons[1].isClicked() && Main.hotbar.useMaterial((byte)2, 10)) {
				Main.hotbar.addItem(11);
			}
			if (buttons[2].isClicked() && Main.hotbar.useMaterial((byte)2, 2)) {
				Main.hotbar.addItem(12);
			}
			break;
		}
		
	}

}
