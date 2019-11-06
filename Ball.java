
package brickWreck;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;



public class Ball {

    int ballPosX =260;
    int ballPosY =350;
    int ballXdir =-2;
    int ballYdir =-6;
    
     public void paint(Graphics2D g){         
        g.setColor(Color.red);
        g.fillOval(ballPosX, ballPosY, 25, 25); 
        
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLACK);
        g.drawOval(ballPosX, ballPosY, 25, 25);
    }
}
