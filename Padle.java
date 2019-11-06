package brickWreck;

import java.awt.BasicStroke;
import java.awt.Color;
//import java.awt.Graphics;
import java.awt.Graphics2D;

public class Padle {
    public boolean play = false;
    public int playerX = 310;
     
    public void paint(Graphics2D g){         
        g.setColor(Color.BLUE);
        g.fillRect(playerX, 500, 120, 20); 
        
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLACK);
        g.drawRect(playerX,500,120,20);
        
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