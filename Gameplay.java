package brickWreck;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener{

    private int score = 0;
    
    private int totalBricks = 32;
    
    private Timer timer;
    private int delay = 8;
    
    private int ballPosX =120;
    private int ballPosY =350;
    private int ballXdir =-2;
    private int ballYdir =-6;
    
    private Bricks brick;
    private Padle padle;
    
    public Gameplay(){
       padle = new Padle();
       brick = new Bricks (4,8);
       addKeyListener(this);
       setFocusable(true);
       setFocusTraversalKeysEnabled(false);
       timer = new Timer(delay, this);
       timer.start();
    }
    
    @Override
    public void paint(Graphics g){
        //background
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);       
        
        //bricks
        brick.draw((Graphics2D) g);
        
        //scores
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("SCORE:" +score, 560, 30);
        
        //border
        g.setColor(Color.white);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);
        
        //the paddle
        padle.paint((Graphics) g);
        
        //the ball
        g.setColor(Color.red);
        g.fillOval(ballPosX, ballPosY, 20, 20);
        
        if(totalBricks == 0){
            padle.play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 40));
            g.drawString("YOU WON!!!, Scores: "+score, 190, 300);
            
            g.setColor(Color.PINK);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("...Press Enter to Restart...", 280, 350);
        }
        
        if(ballPosY > 590){
            padle.play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 40));
            g.drawString("Game Over, Scores: "+score, 190, 300);
            
            g.setColor(Color.PINK);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("...Press Enter to Restart...", 280, 350);
        }
        
        g.dispose();
                
    }
       
    @Override
    public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
     @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void keyPressed(KeyEvent ke) {
       
      if(ke.getKeyCode()== KeyEvent.VK_RIGHT){
           if(padle.playerX >= 600){
               padle.playerX = 600;
           }else{
               padle.moveRight();
           }
           
       }
       if(ke.getKeyCode()== KeyEvent.VK_LEFT){
           if(padle.playerX < 10){
               padle.playerX = 10;
           }else{
               padle.moveLeft();
           }
       }
       if(ke.getKeyCode() == KeyEvent.VK_ENTER){
           
           if(!padle.play){
               padle.play = true;
               ballPosX = 120;
               ballPosY = 350;
               ballXdir = -2;
               ballYdir = -6;
               padle.playerX = 310;
               score = 0;
               totalBricks = 32;
               brick = new Bricks(4,8); 
               
               repaint();
           }
       }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
       timer.start();
       if(padle.play){
           
           if(new Rectangle(ballPosX, ballPosY,20,20).intersects(new Rectangle(padle.playerX,500,100,20))){
               ballYdir = -ballYdir;
           }
           
           A: for(int i = 0; i<brick.map.length; i++){
               for(int j = 0; j<brick.map[0].length; j++){
                   if(brick.map[i][j]>0){
                      int brickX = j*brick.brickWidth+80;
                      int brickY = i*brick.brickHeight+50;
                      int brickWidth = brick.brickWidth;
                      int brickHeight = brick.brickHeight;
                   
                      Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                      Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);
                   
               
                         if(ballRect.intersects(brickRect)){
                             brick.setBrickValue(0, i, j);
                             totalBricks--;
                             score+=5;                             
                             
                             if(ballPosX + 19 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickRect.width){
                                 ballXdir = -ballXdir;
                             }else{
                                 ballYdir = -ballYdir;
                             }
                         break A;
                        }
                   }
               }
           }
           
           ballPosX += ballXdir;
           ballPosY += ballYdir;
           
           if(ballPosX < 0){
               ballXdir = -ballXdir;
           }
            if(ballPosY < 0){
               ballYdir = -ballYdir;
           }
             if(ballPosX > 670){
               ballXdir = -ballXdir;
           }
       }
       repaint();
    }

}