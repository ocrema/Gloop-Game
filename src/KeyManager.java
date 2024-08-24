

import java.awt.event.*;
import java.util.BitSet;
import java.util.LinkedList;



public class KeyManager implements MouseListener, KeyListener, MouseMotionListener, MouseWheelListener {
    //controls keyboard input

    public static final short mouse1 = -1;
    public static final short mouse2 = -2;
    public static final short mouse3 = -3;
    public static final short mouse4 = -4;
    public static final short mouse5 = -5;

    private int mouseX;
    private int mouseY;
    private int mouseWheel;
    //private static int mouseGameX;
    //private static int mouseGameY;


    private LinkedList<Integer> keysDown = new LinkedList<>();
    private LinkedList<Integer> keysClicked = new LinkedList<>();


    public int getMouseX(){
        return mouseX;
    }
    public int getMouseY(){
        return mouseY;
    }



    public boolean getKeyDown(int code){
    	
        return keysDown.contains(Integer.valueOf(code));
    }
    
    public boolean getKeyClicked(int code) {
    	
        return keysClicked.contains(Integer.valueOf(code));
    }
    
    public void update() {
    	keysClicked.clear();
    }


    //KeyListener

    @Override
    public void keyPressed(KeyEvent e) {
        //add if not found

        boolean bool = true;
        for (int i : keysDown){
            if (i == e.getKeyCode()){
                bool = false;
                break;
            }
        }
        if (bool){
            keysDown.add(e.getKeyCode());
        }
        
        keysClicked.add(e.getKeyCode());


    }

    @Override
    public void keyReleased(KeyEvent e) {

        keysDown.remove(Integer.valueOf(e.getKeyCode()));
    }


    //MouseListener

    @Override
    public void mousePressed(MouseEvent e) {
        //add if not found

        boolean bool = true;
        for (int i : keysDown){
            if (i == -1 * e.getButton()){
                bool = false;
                break;
            }
        }
        if (bool){
            keysDown.add(-1 * e.getButton());
        }
        keysClicked.add(-1 * e.getButton());

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        keysDown.remove(Integer.valueOf(-1 * e.getButton()));
    }



    //MouseMotionListener

    private void updateMousePos(MouseEvent e){
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        updateMousePos(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        updateMousePos(e);
    }
    
    public void mouseWheelMoved(MouseWheelEvent e) {
    	
    	if (e.getWheelRotation() > 0) {
    		keysClicked.add(Integer.valueOf(-6));
    	}
    	else {
    		keysClicked.add(Integer.valueOf(-7));
    	}
	}

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
	
	



}
