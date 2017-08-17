
package game.levels;

import levelStates.AbstractGameState;
import tib256_engine.Collision;
import tib256_engine.GameItem;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import tib256_engine.Mouse;
/**
 *
 * @author Tibor Fiak
 */
public class Platform extends AbstractGameState{
    
    GameItem cat = new GameItem();
    GameItem box ;
       
    GameItem panel_pause = new GameItem();
    GameItem button_exit = new GameItem();
    GameItem button_continue = new GameItem();
    
    
    long time = 0;
    
    int mx,my;
    int bx=400;
    boolean mouseMoved=false;
    boolean pause=false;
    boolean esc=true;
    
    boolean moveable = false;
    
    boolean jump = false;
    
    public Platform(int state,JFrame window){      
      super(state,window);
      
      cat.moveable=true;
      cat.x=20;
      cat.y=508;
      cat.width=64;
      cat.height=64;
      cat.sourceX=64;
      cat.sourceY=0;
      cat.destX=128;
      cat.destY=64;
      cat.setImageCutPiece("catAndBox.png");
      gameItems.add(cat); 
      
      
     for(int i=0 ; i<5 ; i++){
          
      box = new GameItem();
      box.name="box";
      box.moveable=true;
      box.x=bx;
      box.y=400;
      box.width=64;
      box.height=64;
      box.sourceX=0;
      box.sourceY=0;
      box.destX=64;
      box.destY=64;
      box.setImageCutPiece("catAndBox.png");
      
      gameItems.add(box);     
      bx+=64;
      }

      panel_pause.x=0;
      panel_pause.y=0;
      panel_pause.width=800;
      panel_pause.height=600;
      panel_pause.sourceX=0;
      panel_pause.sourceY=0;
      panel_pause.destX=800;
      panel_pause.destY=600;
      panel_pause.visible=false;
      panel_pause.setImageCutPiece("pause_panel.png");
      
      button_continue.x=panel_pause.x+315;
      button_continue.y=panel_pause.y+250;
      button_continue.width=175;
      button_continue.height=75;
      button_continue.sourceX=0;
      button_continue.sourceY=75;
      button_continue.destX=175;
      button_continue.destY=150;
      button_continue.visible=false;
      button_continue.setImageCutPiece("button_continue.png");
      
      button_exit.x=panel_pause.x+330;
      button_exit.y=panel_pause.y+400;
      button_exit.width=143;
      button_exit.height=75;
      button_exit.sourceX=0;
      button_exit.sourceY=75;
      button_exit.destX=143;
      button_exit.destY=150;
      button_exit.visible=false;
      button_exit.setImageCutPiece("button_exit.png");
      
      gameItems.add(panel_pause);
      gameItems.add(button_continue);
      gameItems.add(button_exit);
      
      windowSize(800,600);
    }

    public void update(){        
      if(!pause){
          move(cat);
          
            if(jump && cat.isOnGround)
            {
                cat.vy += cat.jumpForce;
                cat.isOnGround = false;
                cat.friction = 1;
            }
            
             cat.y += Math.round(cat.vy);
             
             cat.vy += cat.gravity;
             
            if(cat.y + cat.height > 573)
            {
               cat.y = 573 - cat.height;
               cat.isOnGround = true;
               cat.vy = Math.round(-cat.gravity);
               cat.jumpForce=-11;
            }
            
           for (GameItem p : gameItems) {
               if(p.name.equals("box")) box = p;
               if(Collision.collisionRactangleBlock(cat, box).equals("bottom")) cat.isOnGround=true;
           }
            
      }//fi pause
          
          
    }//#update
    
    private void move(GameItem p){         
          if(p.moveLeft){
           p.vx=-p.velocity;;
          }
          
          if(p.moveRight){
           p.vx=p.velocity;
          }
          
         p.x += p.vx;

    }
    
   
    public void keyPressed(KeyEvent e){
      int key = e.getKeyCode();     
      
      if(key == KeyEvent.VK_LEFT){
          for (GameItem p : gameItems) {
              if(p.moveable) p.moveLeft = true;
          } 
      }
      
      if(key == KeyEvent.VK_RIGHT){
          for (GameItem p : gameItems) {
              if(p.moveable) p.moveRight = true;
          } 
      }
      
      
      if(key == KeyEvent.VK_P){
          pause = true;
          panel_pause.visible=true;
          button_continue.visible=true;
          button_exit.visible=true;
      }
      
      if(key == KeyEvent.VK_ESCAPE){
        if(esc){ 
          pause = true;
          panel_pause.visible=true;
          button_continue.visible=true;
          button_exit.visible=true;
          esc=false;
        }
        else{
           button_exit.visible=false;
           button_continue.visible=false;
           panel_pause.visible=false;
           pause=false;
           esc=true;
        }
      }
      
      if(key == KeyEvent.VK_SPACE){
          jump = true;    
      }
      
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
          
      if(key == KeyEvent.VK_LEFT){
          for (GameItem p : gameItems) {
              if(p.moveable) p.moveLeft = false;
              if(p.moveable) p.vx = 0;
          } 
      }
      
      if(key == KeyEvent.VK_RIGHT){
          for (GameItem p : gameItems) {
              if(p.moveable) p.moveRight = false;
              if(p.moveable) p.vx = 0;
          } 
      }  
            
     if(key == KeyEvent.VK_SPACE){
          jump = false;    
      }
          
   }//#keyReleased
    
    
    public void mousePressed(MouseEvent e){
      if(pause){  
         if(Mouse.MouseonItem(e.getX(),e.getY(),button_continue)){
           button_exit.visible=false;
           button_continue.visible=false;
           panel_pause.visible=false;
           pause=false;
         }
                 
         if(Mouse.MouseonItem(e.getX(),e.getY(),button_exit)){
               button_exit.visible=false;
               button_continue.visible=false;
               panel_pause.visible=false;
               pause=false;
               System.exit(1);
         }
       }
    }
    
 public void mouseMoved(MouseEvent e) {
        
     if(Mouse.MouseonItem(e.getX(),e.getY(),button_exit)){   
      button_exit.sourceX=0;
      button_exit.sourceY=0;
      button_exit.destX=143;
      button_exit.destY=75;
            
     }
     else{
      button_exit.sourceX=0;
      button_exit.sourceY=75;
      button_exit.destX=143;
      button_exit.destY=150;

     }     
     
     if(Mouse.MouseonItem(e.getX(),e.getY(),button_continue)){  
      button_continue.sourceX=0;
      button_continue.sourceY=0;
      button_continue.destX=175;
      button_continue.destY=75;
     } 
     else {                
      button_continue.sourceX=0;
      button_continue.sourceY=75;
      button_continue.destX=175;
      button_continue.destY=150;
     
     }        
    }
    
    public void mouseDragged(MouseEvent e) {
       
    }
    
    
}
