/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.levels;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import levelStates.AbstractGameState;
import tib256_engine.GameItem;

/**
 *
 * @author Tibor
 */
public class Game2 extends AbstractGameState{
    
    GameItem tile = new GameItem();
    GameItem archAngel = new GameItem();
    int degree=1;
    public ArrayList<GameItem> blocks = new ArrayList<>();         

    public Game2(int state,JFrame window) {
        super(state, window);
        
        
        
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){ 
                
                    tile = new GameItem();
                    tile.setImage("tile1.png");
                    tile.x=80*j;
                    tile.y=57*i;
                    tile.width=80;
                    tile.height=60;
                     
                    gameItems.add(tile);
            }
        }
        
         archAngel.setImage("archangel2.png");
         archAngel.name="player";
         archAngel.x=10;
         archAngel.y=0;
         archAngel.width=60;
         archAngel.height=57;
         
         gameItems.add(archAngel);
         
         
        for(int i=0;i<10;i++){  
            for(int j=0;j<10;j++){
                int random=(int)(Math.random()*100);
                if(random<20){
                    GameItem   block = new GameItem();
                               block.setImage("tile1_block.png");
                               block.x=80*j;
                               block.y=57*i;
                               block.name="block";
                               block.width=80;
                               block.height=60;
                              
                   blocks.add(block);
                   gameItems.add(block);
               }

            }
         }
        
         window.setSize(new Dimension(800,600));
        
    }
    

    
    public boolean isBlock(int x,int y){
        for (GameItem block : blocks) {
            if(x/80 == block.x/80 && y/57 ==block.y/57)
                return true;
        }
    
        return false;
    }

    @Override
    public void update() {
        updateMove(archAngel);
        degree+=1;
    }
 
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        keyPressedItem(archAngel,key,80,57);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        keyReleasedItem(archAngel,key);
    }
    
    public void keyReleasedItem(GameItem gi,int key){
        if(key == KeyEvent.VK_LEFT){
            gi.vx=0;
            gi.moveLeft=false;

        }
        if(key == KeyEvent.VK_RIGHT){
            gi.vx=0;
            gi.moveRight=false;
        }
        
        if(key == KeyEvent.VK_UP){
            gi.vy=0;
            gi.moveUp=false;

        }
        if(key == KeyEvent.VK_DOWN){
            gi.vy=0;
            gi.moveDown=false;
        }
      }
    
    public void keyPressedItem(GameItem gi,int key,int x,int y){
        if(key == KeyEvent.VK_LEFT){
            gi.vx=-x;
            gi.moveLeft=true;

        }
        if(key == KeyEvent.VK_RIGHT){
            gi.vx=x;
            gi.moveRight=true;
        }
        
        if(key == KeyEvent.VK_UP){
            gi.vy=-y;
            gi.moveUp=true;

        }
        if(key == KeyEvent.VK_DOWN){
            gi.vy=y;
            gi.moveDown=true;
        }
      }
    
    public void updateMove(GameItem gi){
        if(gi.moveRight && gi.x+gi.vx<window.getWidth() && !isBlock((int)(gi.x+gi.vx),(int)(gi.y+gi.vy) ) ){
         gi.x+=gi.vx;
         gi.moveRight=false;
        }
        
        if(gi.moveLeft && gi.x+gi.vx>0 && !isBlock((int)(gi.x+gi.vx),(int)(gi.y+gi.vy) ) ){
         gi.x+=gi.vx;
         gi.moveLeft=false;
        }
        
        if(gi.moveUp && gi.y+gi.vy>=0 && !isBlock((int)(gi.x+gi.vx),(int)(gi.y+gi.vy) ) ){
         gi.y+=gi.vy;
         gi.moveUp=false;
        }
        
        if(gi.moveDown && gi.y+gi.vy<window.getHeight()-80 && !isBlock((int)(gi.x+gi.vx),(int)(gi.y+gi.vy) ) ){
         gi.y+=gi.vy;
         gi.moveDown=false;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }
    
}
