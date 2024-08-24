import java.awt.*;

public class MoveMarker extends Renderable{

    public MoveMarker(){
        super(10);

    }

    public void draw(Graphics g){
        //g.setColor(Color.green);
        //g.fillOval(Main.display.getDisplayX(getX()) - getWidth()/2, Main.display.getDisplayY(getY())- getHeight()/2, getWidth(), getHeight());
    }

    public void move(){
       // setX(Main.player.getMoveToX());
        //setY(Main.player.getMoveToY());
    }


}
