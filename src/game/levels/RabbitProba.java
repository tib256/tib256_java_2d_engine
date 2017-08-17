/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.levels;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import levelStates.AbstractGameState;
import tib256_engine.GameItem;

/**
 *
 * @author Tibor
 */
public class RabbitProba extends AbstractGameState{
    
    private final GameItem background = new GameItem();
    private final GameItem rabbit = new GameItem();
    private boolean turned=false;
    
    public RabbitProba(int state, JFrame window) {
        super(state, window);

        background.setImage("ret.png");
        background.x=0;
        background.y=0;
        background.destX=600;
        background.destY=400;
        background.width=800;
        background.height=600;
        
        
        rabbit.setImage("nyuszika.png");
        rabbit.x=300;
        rabbit.y=300;
        rabbit.destX=140;
        rabbit.destY=120;
        rabbit.width=70;
        rabbit.height=60;
        rabbit.velocity=2;
        rabbit.vx=rabbit.velocity;
        rabbit.angle=0;
        rabbit.mirrorHorizontally();
        
        
        gameItems.add(background);
        gameItems.add(rabbit);
        
        window.setSize(new Dimension(800,600));

    }

    @Override
    public void update() {
        go(rabbit);
        turnIfClosetheBorder(rabbit);
    }
    
    private void go(GameItem gameItem){
      gameItem.x+=gameItem.velocity*Math.cos(Math.toRadians(-rabbit.angle));
      gameItem.y+=gameItem.velocity*Math.sin(Math.toRadians(-rabbit.angle));
    }
    
    private void turnIfClosetheBorder(GameItem gameItem){
        
        int RIGHTBORDER=window.getWidth()-gameItem.width;
        int LEFTBORDER=0;
        int UPBORDER=0;
        int DOWNBORDER=window.getHeight()-gameItem.height-30;
        int ALIGN=-1;
        
        if( (gameItem.x>RIGHTBORDER || gameItem.x<LEFTBORDER || gameItem.y<UPBORDER || gameItem.y>DOWNBORDER) && !turned){
          gameItem.angle+=180;
          gameItem.angle+=ALIGN*Math.random()*121;
          turned=true;
        }
        else  turned=false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
      
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
