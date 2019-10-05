package brickWreck;

import java.awt.Color;
import java.awt.Graphics;

public class Padle {
    public boolean play = false;
    public int playerX = 310;
     
    public void paint(Graphics g){         
        g.setColor(Color.cyan);
        g.fillRect(playerX, 500, 100, 20);     
    }
     
    public void moveRight(){
        play = true;
        playerX += 60;
    }
    
    public void moveLeft(){
        play = true;
        playerX -= 60;
    }

}
