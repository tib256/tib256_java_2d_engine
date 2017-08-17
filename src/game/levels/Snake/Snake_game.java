/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.levels.Snake;

import game.Main;
import game.levels.gameObjects.Snake.Snake;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import levelStates.AbstractGameState;
import tib256_engine.GameItem;

/**
 *
 * @author Tibor Fiak
 */
public final class Snake_game extends AbstractGameState{
    


    private final GameItem background = new GameItem();
    private final GameItem instraktion = new GameItem();
    private final GameItem score_image = new GameItem();
    private final Snake snake = new Snake();
    public Long time = System.currentTimeMillis();
    private boolean pause = false; 
    private boolean start = false; 
    private boolean p_pressd = false;
    private boolean end = false;
    private int score;
    
    public ArrayList<GameItem> mouses = new ArrayList<>();
    
    public Snake_game(int state, JFrame window) {
        super(state, window);
        
        
        background.setImage("snake_background.png");
        background.x=0;
        background.y=0;
        background.width=1024;
        background.height=740;
        gameItems.add(background);

        snake.initSnake();
        snake.initMouses(mouses);   
        snake.initGameItems(gameItems);
      
        score_image.x=0;
        score_image.y=0;
        score_image.width=200;
        score_image.height=30;
        score_image.text_write("score: "+score,20,25,25);
        gameItems.add(score_image);
       
        add_new_mouse();
        add_new_mouse();
        
        instraktion.setImage("snake_instruction.png");
        instraktion.x=220;
        instraktion.y=70;
        instraktion.width=600;
        instraktion.height=600;
        gameItems.add(instraktion);
        
        windowSize(1024,768);
    }
    
    public void windowSize(){
        windowSize(1024,768);
    }
     
    public void add_new_mouse(){
        Random random = new Random();
        GameItem mouse = new GameItem();
        mouse.setImage("mouse.png");
        mouse.x=random.nextInt(640)+128;
        mouse.y=random.nextInt(576)+64;
        mouse.width=26;
        mouse.height=24;
        gameItems.add(mouse);
        mouses.add(mouse);
    }
    
    private void restart(){
        end=false;
        for (int i = gameItems.size()-1; i > 0; i--) {
            gameItems.remove(i);
        }
        for (int i = mouses.size()-1; i > -1; i--) {
            mouses.remove(i);
        }
        snake.initSnake();
        snake.initGameItems(gameItems);
        score=0;
        score_image.text_write("score: "+score,20,25,25);
        gameItems.add(score_image);
        add_new_mouse();
        add_new_mouse();
        pause=false;
        
    }
    
    @Override
    public void update() {
        
            if(!pause && start){
                snake.move();
                if(snake.eat()){
                 add_new_mouse();
                 score++;
                 score_image.text_write("score: "+score,20,25,25);
                }
                

            }
            
            if(snake.end()){
              pause=true;
              end=true;
              
            }
            
            if(end){
             snake.death();
            }

    }
    

    @Override
    public void keyPressed(KeyEvent e) {
      int key = e.getKeyCode();
        
      snake.head_controll_with_buttons(key);
      
      if(key == KeyEvent.VK_P && !end){
       if(!p_pressd){
        pause=true;
        p_pressd=true;
        background.gray();
       }
       else{
        pause=false;
        p_pressd=false;
        background.imageRepair();
       }
      }
      
      if(key == KeyEvent.VK_R && end && snake.getSize()==0){
          restart();
      }
      
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
      start=true;
      gameItems.remove(instraktion);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
       
    }

    @Override
    public void mouseDragged(MouseEvent e) {
       
    }
    
}
