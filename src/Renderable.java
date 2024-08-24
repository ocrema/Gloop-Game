import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Renderable implements Comparable<Renderable>{

    public abstract void draw(Graphics g);


    private double uiLayer;
 

    public Renderable(double uiLayer){
       
        this.uiLayer = uiLayer;
       
        Main.renderList.add(this);
    }

    public int compareTo(Renderable e){
       
        if (uiLayer > e.uiLayer){
            return -1;
        }
        else if (uiLayer < e.uiLayer){
            return 1;
        }
        else {
            return 0;
        }
    }

    public void dispose(){
        Main.renderList.remove(this);
    }

   
}
