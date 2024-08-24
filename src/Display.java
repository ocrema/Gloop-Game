import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferStrategy;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;

public class Display extends JPanel {

    private JFrame jframe;
    private int width;
    private int height;
    private double x;
    private double y;

    public Display(int width, int height, KeyManager keyManager){
        jframe = new JFrame();
        //this.width = width;
        //this.height = height;
        //this.width = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
        //this.height = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
        this.width = width;
        this.height = height;
        jframe.setUndecorated(true);
        Dimension size = new Dimension(width, height);
        setPreferredSize(size);
        jframe.setTitle("gloop game");
        //jframe.setLocationRelativeTo(null);
        jframe.setLocation(0, 0);
        jframe.setResizable(false);
        jframe.add(this);
        //jframe.pack();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);
        jframe.setFocusable(true);
        //jframe.requestFocusInWindow();

        jframe.setContentPane(this);
        jframe.addKeyListener(keyManager);
        jframe.getContentPane().addMouseListener(keyManager);
        jframe.getContentPane().addMouseMotionListener(keyManager);
        jframe.getContentPane().addMouseWheelListener(keyManager);

        //jframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //jframe.setUndecorated(true);

        jframe.pack();
        //GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(jframe);
        
    }

    public void close(){
        jframe.dispose();
    }

    public void render(){
        BufferStrategy bs = jframe.getBufferStrategy();
        if (bs == null){
            jframe.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        draw(g);

        g.dispose();
        bs.show();
    }

    public void draw(Graphics g){
    	try {
    		
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);


        Collections.sort(Main.renderList, Comparator.reverseOrder());
        for (Renderable e : Main.renderList){
            e.draw(g);
        }
        /*
        g.setColor(new Color(200, 0, 0));
		g.setFont(new Font("Comic Sans", Font.BOLD, 50));
		g.drawString(Path.of("").toAbsolutePath().toString(), 500, 500);
		*/
    	}catch(ConcurrentModificationException e) {}
    }



    public void setTitle(String str){
        jframe.setTitle(str);
    }

    public void updatePos(){
        x = Main.player.getX();
        y = Main.player.getY();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight(){
        return height;
    }

    public int getDisplayX(double x){
        return (int)(x - this.x + width/2);
    }

    public int getDisplayY(double y){
        return (int)(y - this.y + height/2);
    }

    public double getX(int displayX){
        return displayX + this.x -width/2;
    }

    public double getY(int displayY){
        return displayY + this.y - height/2;
    }




}
