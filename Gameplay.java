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
    private Bricks brick;
    private Padle padle;
    private Ball ball;
    
    public Gameplay(){
       padle = new Padle();
       brick = new Bricks (4,8);
       ball = new Ball();
       addKeyListener(this);
       setFocusable(true);
       setFocusTraversalKeysEnabled(false);
       timer = new Timer(delay, this);
       timer.start();
    }
    
    @Override
    public void paint(Graphics g){
        //background
        g.setColor(Color.cyan);
        g.fillRect(1, 1, 692, 592);
        
        //bricks
        brick.draw((Graphics2D) g);
        
        //scores
        g.setColor(Color.BLACK);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("SCORE:" +score, 560, 30);
        
        //border
        g.setColor(Color.white);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);
        
        //the paddle
        padle.paint((Graphics2D) g);
        
        //the ball
        ball.paint((Graphics2D) g);
        
        if(totalBricks == 0){
            padle.play = false;
            ball.ballXdir = 0;
            ball.ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 40));
            g.drawString("YOU WON!!!, Scores: "+score, 190, 300);
            
            g.setColor(Color.PINK);
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("...Press Enter to Restart...", 280, 350);
        }
        
        if(ball.ballPosY > 590){
            padle.play = false;
            ball.ballXdir = 0;
            ball.ballYdir = 0;
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
               ball.ballPosX = 260;
               ball.ballPosY = 350;
               ball.ballXdir = -2;
               ball.ballYdir = -6;
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
           
           if(new Rectangle(ball.ballPosX, ball.ballPosY,20,20).intersects(new Rectangle(padle.playerX,500,100,20))){
               ball.ballYdir = -ball.ballYdir;
           }
           
           A: for(int i = 0; i<brick.map.length; i++){
               for(int j = 0; j<brick.map[0].length; j++){
                   if(brick.map[i][j]>0){
                      int brickX = j*brick.brickWidth+80;
                      int brickY = i*brick.brickHeight+50;
                      int brickWidth = brick.brickWidth;
                      int brickHeight = brick.brickHeight;
                   
                      Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                      Rectangle ballRect = new Rectangle(ball.ballPosX, ball.ballPosY, 20, 20);
                   
               
                         if(ballRect.intersects(brickRect)){
                             brick.setBrickValue(0, i, j);
                             totalBricks--;
                             score+=5;                             
                             
                             if(ball.ballPosX + 19 <= brickRect.x || ball.ballPosX + 1 >= brickRect.x + brickRect.width){
                                 ball.ballXdir = -ball.ballXdir;
                             }else{
                                 ball.ballYdir = -ball.ballYdir;
                             }
                         break A;
                        }
                   }
               }
           }
           
           ball.ballPosX += ball.ballXdir;
           ball.ballPosY += ball.ballYdir;
           
           if(ball.ballPosX < 0){
               ball.ballXdir = -ball.ballXdir;
           }
            if(ball.ballPosY < 0){
               ball.ballYdir = -ball.ballYdir;
           }
             if(ball.ballPosX > 670){
               ball.ballXdir = -ball.ballXdir;
           }
       }
       repaint();
    }

}