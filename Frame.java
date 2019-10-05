package brickWreck;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public final class Frame extends JFrame{
    
    private ImageIcon icon;
    
    Frame(){
       getIcon();
       setBounds(30, 30, 700,600);
       setTitle("BRICKWRECK");
       setResizable(false);
       setVisible(true);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    public void getIcon(){
       icon = new ImageIcon(getClass().getResource("snooker.png"));
       setIconImage(icon.getImage());
    }
}
