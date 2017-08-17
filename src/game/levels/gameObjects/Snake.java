/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.levels.gameObjects;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import tib256_engine.Collision;
import tib256_engine.GameItem;

/**
 *
 * @author tib25
 */
public class Snake {
    
    private enum directions  {UP,DOWN,LEFT,RIGHT}
    private directions direction = directions.UP;
    public GameItem snake_head ;
    private final GameItem snake_part = new GameItem();
    private final GameItem snake_part2 = new GameItem();
    private final GameItem snake_part3 = new GameItem();
    private final GameItem snake_tail = new GameItem();
    
    public ArrayList<GameItem> mouses = new ArrayList<>();
    
    private int snake_direction=1;
    private boolean snake_turnable = true;
    private boolean snake_tail_change= true;
    
    private ArrayList<GameItem> snake = new ArrayList<>();
    
    private ArrayList<GameItem> gameItems;
    
    private long time=System.currentTimeMillis()+3000;

    public Snake() {
         
    }//#

    public void initSnake(){
        direction=direction.UP;
        snake_head = new GameItem();
        snake_head.setImage("snake_head.png");
        snake_head.angle=90;
        snake_head.x=200;
        snake_head.y=400;
        snake_head.width=32;
        snake_head.height=32;
        snake_head.velocity=3;
        snake.add(snake_head);
        
        snake_part.setImage("snake_part0.png");
        snake_part.x=200;
        snake_part.y=400+snake_head.height;
        snake_part.width=32;
        snake_part.height=32;
        snake_part.angle=90;
        snake.add(snake_part);
     
        snake_part2.setImage("snake_part1.png");
        snake_part2.x=snake.get(1).x;
        snake_part2.y=snake.get(1).y+snake.get(1).height;
        snake_part2.width=32;
        snake_part2.height=32;
        snake_part2.angle=90;
        snake.add(snake_part2);
       
        snake_part3.setImage("snake_part1.png");
        snake_part3.x=snake.get(2).x;
        snake_part3.y=snake.get(2).y+snake.get(2).height;
        snake_part3.width=32;
        snake_part3.height=32;
        snake_part3.angle=90;
        snake.add(snake_part3);
        
        snake_tail.setImage("snake_tail.png");
        snake_tail.x=snake.get(3).x;
        snake_tail.y=snake.get(3).y+snake.get(3).height;
        snake_tail.width=32;
        snake_tail.height=32;
        snake_tail.angle=90;
        snake.add(snake_tail);
        
        
    }
    
    public void initGameItems(ArrayList<GameItem> gameItems){
        this.gameItems=gameItems;
        gameItems.add(snake_head);
        gameItems.add(snake_part);
        gameItems.add(snake_part2);
        gameItems.add(snake_part3);
        gameItems.add(snake_tail);
    }
    
    public void initMouses(ArrayList<GameItem> mouses){
      this.mouses=mouses;
    }
    
    
    
    public void add_new_snake_part(){
     
     snake.remove(snake.size()-1);   
     gameItems.remove(snake_tail);
     GameItem new_snake_part = new GameItem();
     new_part_alternate(new_snake_part);
     new_snake_part.width=32;
     new_snake_part.height=32;

     lastes_part_angle_90(new_snake_part);
     lastes_part_angle_270(new_snake_part);   
     lastes_part_angle_0(new_snake_part);
     lastes_part_angle_180(new_snake_part);
     
     gameItems.add(new_snake_part);
     gameItems.add(snake_tail);
     
    }//#
    
    public void new_part_alternate(GameItem new_snake_part){
      if(snake_tail_change){
        new_snake_part.setImage("snake_part1.png");
        snake_tail_change=false;
        
      }
      else{
        new_snake_part.setImage("snake_part2.png");
        snake_tail_change=true;
      }
    
    }//#
    
    public void lastes_part_angle_90(GameItem new_snake_part){
       int snake_lastes_part_number=snake.size()-1;
       
       if(snake.get(snake_lastes_part_number).angle==90){
         snake.add(new_snake_part);
         snake_lastes_part_number=snake.size()-1;
         snake.get(snake_lastes_part_number).angle=90;
         snake.get(snake_lastes_part_number).x=snake.get(snake_lastes_part_number-1).x;
         snake.get(snake_lastes_part_number).y=snake.get(snake_lastes_part_number-1).y+snake.get(snake_lastes_part_number-1).height;
         
         snake.add(snake_tail);
         snake_lastes_part_number=snake.size()-1;
         snake.get(snake_lastes_part_number).angle=90;
         snake.get(snake_lastes_part_number).x=snake.get(snake_lastes_part_number-1).x;
         snake.get(snake_lastes_part_number).y=snake.get(snake_lastes_part_number-1).y+snake.get(snake_lastes_part_number-1).height;
       }
    
    }//#
    
    public void lastes_part_angle_270(GameItem new_snake_part){
        int snake_lastes_part_number=snake.size()-1;
       
       if(snake.get(snake_lastes_part_number).angle==270){
         snake.add(new_snake_part);
         snake_lastes_part_number=snake.size()-1;
         snake.get(snake_lastes_part_number).angle=270;
         snake.get(snake_lastes_part_number).x=snake.get(snake_lastes_part_number-1).x;
         snake.get(snake_lastes_part_number).y=snake.get(snake_lastes_part_number-1).y-snake.get(snake_lastes_part_number-1).height;
         
         snake.add(snake_tail);
         snake_lastes_part_number=snake.size()-1;
         snake.get(snake_lastes_part_number).angle=270;
         snake.get(snake_lastes_part_number).x=snake.get(snake_lastes_part_number-1).x;
         snake.get(snake_lastes_part_number).y=snake.get(snake_lastes_part_number-1).y-snake.get(snake_lastes_part_number-1).height;
       }
    
    }//#
    
    public void lastes_part_angle_0(GameItem new_snake_part){
       int snake_lastes_part_number=snake.size()-1;
       
       if(snake.get(snake_lastes_part_number).angle==0){
         snake.add(new_snake_part);
         snake_lastes_part_number=snake.size()-1;
         snake.get(snake_lastes_part_number).angle=0;
         snake.get(snake_lastes_part_number).x=snake.get(snake_lastes_part_number-1).x-snake.get(snake_lastes_part_number-1).height;
         snake.get(snake_lastes_part_number).y=snake.get(snake_lastes_part_number-1).y;
         
         snake.add(snake_tail);
         snake_lastes_part_number=snake.size()-1;
         snake.get(snake_lastes_part_number).angle=0;
         snake.get(snake_lastes_part_number).x=snake.get(snake_lastes_part_number-1).x-snake.get(snake_lastes_part_number-1).height;
         snake.get(snake_lastes_part_number).y=snake.get(snake_lastes_part_number-1).y;
       }
    
    }//#
    
    public void lastes_part_angle_180(GameItem new_snake_part){
       int snake_lastes_part_number=snake.size()-1;
       
       if(snake.get(snake_lastes_part_number).angle==180){
         snake.add(new_snake_part);
         snake_lastes_part_number=snake.size()-1;
         snake.get(snake_lastes_part_number).angle=180;
         snake.get(snake_lastes_part_number).x=snake.get(snake_lastes_part_number-1).x+snake.get(snake_lastes_part_number-1).height;
         snake.get(snake_lastes_part_number).y=snake.get(snake_lastes_part_number-1).y;
         
         snake.add(snake_tail);
         snake_lastes_part_number=snake.size()-1;
         snake.get(snake_lastes_part_number).angle=180;
         snake.get(snake_lastes_part_number).x=snake.get(snake_lastes_part_number-1).x+snake.get(snake_lastes_part_number-1).height;
         snake.get(snake_lastes_part_number).y=snake.get(snake_lastes_part_number-1).y;
       }

    
    }//#

        public void head_controll_with_buttons(int key){

          if(key == KeyEvent.VK_LEFT && snake_turnable && direction!=directions.RIGHT){
              direction=directions.LEFT;
              snake_turnable=false;
              
          }

          if(key == KeyEvent.VK_RIGHT && snake_turnable && direction!=directions.LEFT){
              direction=directions.RIGHT;
              snake_turnable=false;
          }
          
          if(key == KeyEvent.VK_UP && key != KeyEvent.VK_RIGHT && snake_turnable && direction!=directions.DOWN){
              direction=directions.UP;
              snake_turnable=false;
          }

          if(key == KeyEvent.VK_DOWN && snake_turnable && direction!=directions.UP){
              direction=directions.DOWN;
              snake_turnable=false;
          }

        }//#
        
        
        public void move(){
          head_controll();
          part_follow_the_infrontof_part();
          
        }//#
    
        private void head_controll(){
        
            switch(direction){
                case UP:
                   snake_head.y-=snake_head.velocity;
                   
                   snake_head.angle=90;
                   break;
                case DOWN:
                   snake_head.y+=snake_head.velocity;
                   snake_head.angle=270;
                   break;
                case LEFT:
                   snake_head.x-=snake_head.velocity;
                   snake_head.angle=180;
                   break;
                case RIGHT:
                   snake_head.x+=snake_head.velocity;
                   snake_head.angle=0;
                   break;
            }
        }//#
       
    private void part_follow_the_infrontof_part(){
        if(snake.get(0).angle==snake.get(1).angle){
          snake_turnable=true;
        }
        
        for (int i = 1; i < snake.size(); i++) {

              if(snake.get(i).angle==90){
                 snake.get(i).y-=snake_head.velocity;
              }

              if(snake.get(i).angle==270){
                 snake.get(i).y+=snake_head.velocity;
              }

              if(snake.get(i).angle==180){
                 snake.get(i).x-=snake_head.velocity;
              }

              if(snake.get(i).angle==0){
                 snake.get(i).x+=snake_head.velocity;
              }

              if(snake.get(i-1).angle==0 && snake.get(i).angle==90 && snake.get(i).y<snake.get(i-1).y ){
                 snake.get(i).angle=0;
                 snake.get(i).y=snake.get(i-1).y;
                 snake.get(i).x=snake.get(i-1).x-snake.get(i).width;
              }
              
              if(snake.get(i-1).angle==270 && snake.get(i).angle==0 && snake.get(i).x>snake.get(i-1).x ){
                 snake.get(i).angle=270;
                 snake.get(i).x=snake.get(i-1).x;
                 snake.get(i).y=snake.get(i-1).y-snake.get(i).width;
              }
              
              if(snake.get(i-1).angle==180 && snake.get(i).angle==270 && snake.get(i).y>snake.get(i-1).y ){
                 snake.get(i).angle=180;
                 snake.get(i).y=snake.get(i-1).y;
                 snake.get(i).x=snake.get(i-1).x+snake.get(i).width;
              }
              
              if(snake.get(i-1).angle==90 && snake.get(i).angle==180 && snake.get(i).x<snake.get(i-1).x ){
                 snake.get(i).angle=90;
                 snake.get(i).x=snake.get(i-1).x;
                 snake.get(i).y=snake.get(i-1).y+snake.get(i).width;
              }
             
              if(snake.get(i-1).angle==90 && snake.get(i).angle==0 && snake.get(i).x>snake.get(i-1).x ){
                 snake.get(i).angle=90;
                 snake.get(i).x=snake.get(i-1).x;
                 snake.get(i).y=snake.get(i-1).y+snake.get(i).width;
              }
              
              if(snake.get(i-1).angle==180 && snake.get(i).angle==90 && snake.get(i).y<snake.get(i-1).y ){
                 snake.get(i).angle=180;
                 snake.get(i).y=snake.get(i-1).y;
                 snake.get(i).x=snake.get(i-1).x+snake.get(i).width;
              }
              
              if(snake.get(i-1).angle==270 && snake.get(i).angle==180 && snake.get(i).x<snake.get(i-1).x ){
                 snake.get(i).angle=270;
                 snake.get(i).x=snake.get(i-1).x;
                 snake.get(i).y=snake.get(i-1).y-snake.get(i).width;
              }
              
              if(snake.get(i-1).angle==0 && snake.get(i).angle==270 && snake.get(i).y>snake.get(i-1).y ){
                 snake.get(i).angle=0;
                 snake.get(i).y=snake.get(i-1).y;
                 snake.get(i).x=snake.get(i-1).x-snake.get(i).width;
              }
              
 

        }
    }//#
    
    public boolean end(){
        if(snake_head.x<64 
           || snake_head.x>900 
           || snake_head.y<32 
           || snake_head.y>690){ 
            for (GameItem snake_part_ : snake) {
                    snake_part_.grayPng();
            }
            return true;
        }
        for (int i = 5; i < snake.size()-1; i++) {
            if(!Collision.collisionRactangle(snake_head,snake.get(i)).equals("none") )
            {
                for (GameItem snake_part_ : snake) {
                    snake_part_.grayPng();
                }
              return true;
            }
        }
    return false;
    }//#
    
    public void death(){
      try{
        gameItems.remove(snake_tail);
       if(time<System.currentTimeMillis()){
            gameItems.remove(snake.get(snake.size()-1));
            snake.remove(snake.get(snake.size()-1));
            time=System.currentTimeMillis()+50;
       }
      }
       
      catch(Exception ex){
      
      }
       
    }//#
    
    public int getSize(){
      return snake.size();
    }
    
    public boolean eat(){
        try{
          for (GameItem mouse : mouses) {
            if(!Collision.collisionRactangle(snake_head,mouse).equals("none")){
               gameItems.remove(mouse);
               mouses.remove(mouse);
               add_new_snake_part();
               return true;
            }  
          }
        }
        catch(java.util.ConcurrentModificationException ex){
            
        }
        
        return false;
    }
    

    
    
    
    
}//end of class
