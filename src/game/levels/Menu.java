
package game.levels;

/**
 *
 * @author Tibor Fiak
 */

import game.levels.Snake.Snake_game;
import levelStates.AbstractGameState;
import tib256_engine.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;


public class Menu extends AbstractGameState{
    
     GameItem menu = new GameItem();
     GameItem button_start = new GameItem();
     GameItem button_exit = new GameItem();
     GameItem button_game = new GameItem();
     GameItem button_platform = new GameItem();
     
     private final int BUFFER_SIZE = 4096;
     
     boolean click=false;
     
    // ArrayList<GameItem> menuItems = new ArrayList<GameItem>();
     
     
     public Menu(int state,JFrame window){ 
      super(state,window);
      
      menu.setImage("menu.png");
      menu.x=140;
      menu.y=90;
      menu.width=498;
      menu.height=384;
      
      
      gameItems.add(menu);
      
      button_start.x=menu.x+180;
      button_start.y=menu.y+100;
      button_start.width=143;
      button_start.height=75;
      button_start.sourceX=0;
      button_start.sourceY=75;
      button_start.destX=143;
      button_start.destY=150;
      button_start.setImageCutPiece("button_start.png");
      
      gameItems.add(button_start);
      
      button_exit.x=menu.x+180;
      button_exit.y=menu.y+200;
      button_exit.width=143;
      button_exit.height=75;
      button_exit.sourceX=0;
      button_exit.sourceY=75;
      button_exit.destX=143;
      button_exit.destY=150;
      button_exit.setImageCutPiece("button_exit.png");
      
      gameItems.add(button_exit);
      
      button_game.filename="button_game.png";
      button_game.x=menu.x+165;
      button_game.y=menu.y+205;
      button_game.width=175;
      button_game.height=75;
      button_game.sourceX=0;
      button_game.sourceY=75;
      button_game.destX=175;
      button_game.destY=150;
      button_game.visible=false;
      button_game.setImageCutPiece("button_game.png");
      
      gameItems.add(button_game);
      
      
      button_platform.x=menu.x+165;
      button_platform.y=menu.y+85;
      button_platform.width=175;
      button_platform.height=75;
      button_platform.sourceX=0;
      button_platform.sourceY=75;
      button_platform.destX=175;
      button_platform.destY=150;
      button_platform.visible=false;
      button_platform.setImageCutPiece("button_platform.png");
     
      
      gameItems.add(button_platform);
      windowSize(800,600);
       //playSound("coin.wav");
       
     }//#menu
     
    
    public void update(){            
    }//update end
    
    
        public void mousePressed(MouseEvent e){
         if(Mouse.MouseonItem(e.getX(),e.getY(),button_platform)){
             ((Platform)gameLevels.get(0)).windowSize(800,600);
             AbstractGameState.visible=1;  //platform         
          }
         
         if(Mouse.MouseonItem(e.getX(),e.getY(),button_game)){
             ((Snake_game)gameLevels.get(1)).windowSize();
             AbstractGameState.visible=3;
          }
         
         if(Mouse.MouseonItem(e.getX(),e.getY(),button_start)){
              button_start.visible = false;
              button_exit.visible = false;
              button_platform.visible = true;
              button_game.visible = true;
              click=true;
         }

         if(Mouse.MouseonItem(e.getX(),e.getY(),button_exit)){
             System.exit(0);
         }
         
         
    }//#MouseonItem
    
  
    public void mouseMoved(MouseEvent e) {
     if(Mouse.MouseonItem(e.getX(),e.getY(),button_start)){   
      button_start.sourceX=0;
      button_start.sourceY=0;
      button_start.destX=143;
      button_start.destY=75;
      button_start.setImageCutPiece("button_start.png"); 
     }
     else{
      button_start.sourceX=0;
      button_start.sourceY=75;
      button_start.destX=143;
      button_start.destY=150;
      button_start.setImageCutPiece("button_start.png");
     }
     
     if(Mouse.MouseonItem(e.getX(),e.getY(),button_exit)){   
      button_exit.sourceX=0;
      button_exit.sourceY=0;
      button_exit.destX=143;
      button_exit.destY=75;
      button_exit.setImageCutPiece("button_exit.png");
      
     }
     else{
      button_exit.sourceX=0;
      button_exit.sourceY=75;
      button_exit.destX=143;
      button_exit.destY=150;
      button_exit.setImageCutPiece("button_exit.png");
     }

     if(Mouse.MouseonItem(e.getX(),e.getY(),button_game)){  
      button_game.sourceX=0;
      button_game.sourceY=0;
      button_game.destX=175;
      button_game.destY=75;
      button_game.setImageCutPiece("button_game.png");
     } 
     else {                
      button_game.sourceX=0;
      button_game.sourceY=75;
      button_game.destX=175;
      button_game.destY=150;
      button_game.setImageCutPiece("button_game.png");
     
     }
     
    if(Mouse.MouseonItem(e.getX(),e.getY(),button_platform)){  
      button_platform.sourceX=0;
      button_platform.sourceY=0;
      button_platform.destX=175;
      button_platform.destY=75;
      button_platform.setImageCutPiece("button_platform.png");
     } 
     else {                
      button_platform.sourceX=0;
      button_platform.sourceY=75;
      button_platform.destX=175;
      button_platform.destY=150;
      button_platform.setImageCutPiece("button_platform.png");
     
     }
    
    }//#mouseMoved

    @Override
    public void keyPressed(KeyEvent e) {
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
       
    }
    

}//osztaly vege
