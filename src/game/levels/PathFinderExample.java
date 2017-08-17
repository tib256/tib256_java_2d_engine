/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.levels;


import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JFrame;

import levelStates.AbstractGameState;
import game.levels.gameObjects.PathFinderExampleConstans;
import tib256_engine.GameItem;
import tib256_engine.pathfinder.table.Table;

/**
 *
 * @author fiak
 */
public class PathFinderExample extends AbstractGameState{
    
    private static final int FIELDSIZE=78;
    private static final int FIELDCORECTION=10;
    private int carrotX;
    private int carrotY;
    private int playerX;
    private int playerY;
    
    private final GameItem background = new GameItem();
    
    private Table map = new Table();
    

    public PathFinderExample(int state, JFrame window) {
        super(state, window);
        
        initBackground();
        initFields();
        initTable();
        System.out.println(map.toString());
        drawTable();
       
    }
    

    
    @Override
    public void update() {
        
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
    
    private void initBackground(){
        background.setImage("pathfinder/background.png");
        background.x=0;
        background.y=0;
        background.width=800;
        background.height=800;
        gameItems.add(background);
    }
    
    private void initFields(){
        boolean change=false;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(change){
                    rowSample1(j, i);
                }
                else{
                    rowSample2(j, i);
                }
            }
            if(change){
               change=false; 
            }
            else change=true;
        } 
    }
        private void rowSample1(int j,int i){
            if(j%2==0){
                addField1ItemtoWorld(j*FIELDSIZE+FIELDCORECTION, i*FIELDSIZE+FIELDCORECTION);
            }
            else{
                addField2ItemtoWorld(j*FIELDSIZE+FIELDCORECTION, i*FIELDSIZE+FIELDCORECTION);
            }
        }
        private void rowSample2(int j,int i){
            if(j%2!=0){
                addField1ItemtoWorld(j*FIELDSIZE+FIELDCORECTION, i*FIELDSIZE+FIELDCORECTION);
            }
            else{
                addField2ItemtoWorld(j*FIELDSIZE+FIELDCORECTION, i*FIELDSIZE+FIELDCORECTION);
            }
        }
        private void addField1ItemtoWorld(int x , int y){
            GameItem field = new GameItem();
            field.setImage("pathfinder/field_item1.png");
            field.x=x;
            field.y=y;
            field.width=FIELDSIZE;
            field.height=FIELDSIZE;
            gameItems.add(field);
        }
        private void addField2ItemtoWorld(int x , int y){
            GameItem field = new GameItem();
            field.setImage("pathfinder/field_item2.png");
            field.x=x;
            field.y=y;
            field.width=FIELDSIZE;
            field.height=FIELDSIZE;
            gameItems.add(field);
        }
        
    private void initTable(){
        map.setRow(PathFinderExampleConstans.TABLE_ROW);
        map.setColumn(PathFinderExampleConstans.TABLE_COLUMN);
        addBarriers();
        addPlayer();
        addCarrot();
        drawTable();
    }
        private void addBarriers(){
            Random random = new Random();
        
            for (int i = 0; i < PathFinderExampleConstans.TABLE_ROW; i++) {
                for (int j = 0; j < PathFinderExampleConstans.TABLE_COLUMN; j++) {
                    int chance=random.nextInt(100);
                    if(chance<20){
                        map.addItem(i,j,PathFinderExampleConstans.BARRIER);
                    }
                    else {
                        map.addItem(i,j,PathFinderExampleConstans.FIELD);
                    }

                }
            }
        }
        private void addPlayer(){
             Random random = new Random();
             int x = random.nextInt(10);
             int y = random.nextInt(10);
             while(     map.getTableValue(y,x)==PathFinderExampleConstans.BARRIER 
                     && map.getTableValue(y,x)==PathFinderExampleConstans.CARROT){
                x = random.nextInt(10);
                y = random.nextInt(10);
             }
             map.setTableValue(y,x,PathFinderExampleConstans.PLAYER); 
        }
        private void addCarrot(){
             Random random = new Random();
             int x = random.nextInt(10);
             int y = random.nextInt(10);
             while(     map.getTableValue(y,x)==PathFinderExampleConstans.BARRIER 
                     && map.getTableValue(y,x)==PathFinderExampleConstans.PLAYER){
                x = random.nextInt(10);
                y = random.nextInt(10);
             }
             map.setTableValue(y,x,PathFinderExampleConstans.CARROT);
        }
        private void drawTable(){
                for (int i = 0; i < PathFinderExampleConstans.TABLE_ROW; i++) {
                    for (int j = 0; j < PathFinderExampleConstans.TABLE_COLUMN; j++) {
                         switch(map.getTableValue(i, j)){
                             case PathFinderExampleConstans.BARRIER: 
                                 addtoGameItemsBarrier(j,i);
                                 break;
                             case PathFinderExampleConstans.PLAYER: 
                                 addtoGameItemsPlayer(j,i);
                                 break;
                             case PathFinderExampleConstans.CARROT: 
                                 addtoGameItemsCarrot(j,i);
                                 break;
                         }
                    }
                }
        }
            private void addtoGameItemsBarrier(int x, int y){
                GameItem barrier = new GameItem();
                barrier.setImage(PathFinderExampleConstans.BARRIER_PATH);
                barrier.x=x*FIELDSIZE+FIELDCORECTION+4;
                barrier.y=y*FIELDSIZE+FIELDCORECTION+1;
                barrier.width=PathFinderExampleConstans.BARRIER_WIDTH;
                barrier.height=PathFinderExampleConstans.BARRIER_HEIGHT;
                gameItems.add(barrier);
            }
            private void addtoGameItemsPlayer(int x, int y){
                GameItem player = new GameItem();
                player.setImage(PathFinderExampleConstans.PLAYER_PATH);
                player.x=x*FIELDSIZE+FIELDCORECTION;
                player.y=y*FIELDSIZE+FIELDCORECTION;
                player.width=PathFinderExampleConstans.PLAYER_WIDTH;
                player.height=PathFinderExampleConstans.PLAYER_HEIGHT;
                gameItems.add(player);
            }
            private void addtoGameItemsCarrot(int x, int y){
                GameItem carrot = new GameItem();
                carrot.setImage(PathFinderExampleConstans.CARROT_PATH);
                carrot.x=x*FIELDSIZE+FIELDCORECTION+3;
                carrot.y=y*FIELDSIZE+FIELDCORECTION+8;
                carrot.width=PathFinderExampleConstans.CARROT_WIDTH;
                carrot.height=PathFinderExampleConstans.CARROT_HEIGHT;
                gameItems.add(carrot);
            }
    
}
