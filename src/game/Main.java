/**
 * Fiák Tibor tib256_engine_test
 * This is a simple 2D game engine
 * You can make levels with AbstractGameState , AbstractGameState included InterfaceGameState and InterfaceWorlds
 * You can make GameItems ,which included a GameItem properties example: x,y coordinate, picture, picture manipulation methods etc. 
 * You can store the leves in the InterfaceWorlds in gameLevels array
 * You can play wav sound and play animation.
 * I made a few game, you can learn tihs engine.
 * I hope that this engine will like for you :).
 * My vision that you can learn the objectum oriented programing easily and playfully.
 */
package game;

import game.levels.PathFinderExample.PathFinderExample;
import game.levels.*;
import game.levels.Snake.Snake_game;
import levelStates.InterfaceWorlds;
import levelStates.AbstractGameState;
import static levelStates.InterfaceWorlds.gameLevels;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.event.MouseInputAdapter;

/**
 *
 * @author Tibor Fiak
 * @version 1.0
 */
public class Main extends JComponent implements ActionListener, InterfaceWorlds{
    
    private String title;
    private int width;
    private int height;
    
    private Timer timer;
    
    private JFrame window;
    

    public Main(String title,int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        
        init();
        ImageIcon img = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/snake_head.png")));
        window.setIconImage(img.getImage());
        window.setVisible(true);
    }
    
    private void init(){
        window = new JFrame(title);
        window.add(this);
        window.pack();    
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        
            gameLevels.add(new Platform(1,window));
//          gameLevels.add(new PictureRotation(2,window));
            gameLevels.add(new Snake_game(3,window));
//          gameLevels.add(new Animation_test(4,window));
//          gameLevels.add(new PathFinderExample(5,window));
            gameLevels.add(new Menu(0,window));
        
        //Start level
        AbstractGameState.visible=0;
        
        window.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
               for (AbstractGameState gameLevel : gameLevels) {
                    if(AbstractGameState.visible == gameLevel.getState())
                    gameLevel.keyPressed(e);
               }
              
            }
            
            @Override
            public void keyReleased(KeyEvent e){
               for (AbstractGameState gameLevel : gameLevels) {
                    if(AbstractGameState.visible == gameLevel.getState())
                    gameLevel.keyReleased(e);
               }
            }
            
        });
        
        window.addMouseListener(new MouseInputAdapter(){
        
            @Override
            public void mousePressed(MouseEvent e){
                for (AbstractGameState gameLevel : gameLevels) {
                    if(AbstractGameState.visible == gameLevel.getState())
                    gameLevel.mousePressed(e);
                }
              
            }
          
        });
        
        window.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                for (AbstractGameState gameLevel : gameLevels) {
                    if(AbstractGameState.visible == gameLevel.getState())
                    gameLevel.mouseMoved(e);
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                 for (AbstractGameState gameLevel : gameLevels) {
                    if(AbstractGameState.visible == gameLevel.getState())
                    gameLevel.mouseDragged(e);
                 }
            }

            
        } );
             
         /**
          * I want 80fps, 1000/80 = 12.5. 1000millisecond a second. 
          */      
         timer = new Timer(12, this);
         timer.start();
         
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        for (AbstractGameState gameLevel : gameLevels) {
            if(AbstractGameState.visible == gameLevel.getState())
            gameLevel.update();
        }
        
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {   
        super.paintComponent(g);
        for (AbstractGameState gameLevel : gameLevels) {
            if(AbstractGameState.visible == gameLevel.getState()){
              gameLevel.render(g);
            }
        }
        
    }
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public static void main(String[] args) {
      GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
      int width2 = gd.getDisplayMode().getWidth();
      int height2 = gd.getDisplayMode().getHeight();
      new Main("Tib256 engine Test",width2,height2);
    }
  
}
