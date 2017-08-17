/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.levels.PathFinderExample;


import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JFrame;

import levelStates.AbstractGameState;
import game.levels.gameObjects.PathFinderExample.PathFinderExampleConstans;
import static game.levels.gameObjects.PathFinderExample.PathFinderExampleConstans.BARRIER;
import static game.levels.gameObjects.PathFinderExample.PathFinderExampleConstans.BUTTON_SIZE;
import java.util.ArrayList;
import java.util.Stack;
import tib256_engine.Collision;
import tib256_engine.GameItem;
import tib256_engine.Mouse;
import tib256_engine.pathfinder.breadth_first.PathFinder;
import tib256_engine.pathfinder.table.Table;
import tib256_engine.pathfinder.table.TableItem;

/**
 *
 * @author Tibor Fiak
 */
public class PathFinderExample extends AbstractGameState{
    
    private static final int FIELDSIZE=78;
    private static final int FIELDCORECTION=10;
    
    private int carrotX;
    private int carrotY;
    private int playerX;
    private int playerY;
    
    private final GameItem background = new GameItem();
    private final GameItem panel = new GameItem();
    private final GameItem playButton = new GameItem();
    private final GameItem pauseButton = new GameItem();
    private final GameItem repeatButton = new GameItem();
    private final GameItem player = new GameItem();
    private final GameItem carrot = new GameItem();
    
    private final ArrayList<GameItem> barriers = new ArrayList<>();
    private final Table map = new Table();
    private final PathFinder pathFinder = new PathFinder();
    private Stack<TableItem> path; 
    private long time=System.currentTimeMillis()+1000;
    private boolean pause=true;
    
    private boolean playerLeft=true;
    

    public PathFinderExample(int state, JFrame window) {
        super(state, window);
        windowSize(806,920);
        initGameItems();
        while(pathFinder()){
            resetLevel();
        }

    }
    
    @Override
    public void update(){
       if(!pause){
            catchtheCarrot();
            if(!Collision.collisionRactangle(player,carrot).equals("none")){
                gameItems.remove(carrot);
            }
       }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
 
    }

    @Override
    public void mousePressed(MouseEvent e) {
       if(Mouse.MouseonItem(e.getX(),e.getY(),playButton)){
           playButton.setImage("pathfinder/play_button_pushed.png");
           pause=false;
       }
       
       if(Mouse.MouseonItem(e.getX(),e.getY(),pauseButton)){
           pauseButton.setImage("pathfinder/pause_button_pressed.png");
           pause=true;
       }
       
       if(Mouse.MouseonItem(e.getX(),e.getY(),repeatButton)){
           do{
             resetLevel();    
           }while(pathFinder());
           
           pause=true;
       }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(pause) 
            button_release_effect(e,playButton,"pathfinder/play_button.png","pathfinder/play_button_release.png");
        if(!pause) 
            button_release_effect(e,pauseButton,"pathfinder/pause_button.png","pathfinder/pause_button_release.png");
        
        button_release_effect(e,repeatButton,"pathfinder/repeat_button.png","pathfinder/repeat_button_release.png");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
       
    }
        
    private void initGameItems(){
        initBackgroundanPanel();
        initButtons();
        initFields();
        initTable();
        drawTable();   
    }
    
    private void initBackgroundanPanel(){
        background.setImage("pathfinder/background.png");
        background.x=0;
        background.y=0;
        background.width=800;
        background.height=800;
        gameItems.add(background);
        
        panel.setImage("pathfinder/panel.png");
        panel.x=0;
        panel.y=790;
        panel.width=800;
        panel.height=100;
        gameItems.add(panel);
        
    }
    
    private void initButtons(){
        playButton.setImage("pathfinder/play_button.png");
        playButton.x=250;
        playButton.y=805;
        playButton.width=BUTTON_SIZE;
        playButton.height=BUTTON_SIZE;
        gameItems.add(playButton);
        
        pauseButton.setImage("pathfinder/pause_button_pressed.png");
        pauseButton.x=350;
        pauseButton.y=805;
        pauseButton.width=BUTTON_SIZE;
        pauseButton.height=BUTTON_SIZE;
        gameItems.add(pauseButton);
        
        repeatButton.setImage("pathfinder/repeat_button.png");
        repeatButton.x=450;
        repeatButton.y=805;
        repeatButton.width=BUTTON_SIZE;
        repeatButton.height=BUTTON_SIZE;
        gameItems.add(repeatButton);
    }
    
    private void button_release_effect(MouseEvent e,GameItem gameItem,String picture,String picture_release){
        if(Mouse.MouseonItem(e.getX(),e.getY(),gameItem)){  
            gameItem.setImage(picture_release);
        }
        else{
            gameItem.setImage(picture);
        }
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
                    if(chance<30){
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
                barrier.name="barrier";
                gameItems.add(barrier);
                barriers.add(barrier);
                
            }
            private void addtoGameItemsPlayer(int x, int y){
                player.setImage(PathFinderExampleConstans.PLAYER_PATH);
                player.x=x*FIELDSIZE+FIELDCORECTION;
                player.y=y*FIELDSIZE+FIELDCORECTION;
                player.width=PathFinderExampleConstans.PLAYER_WIDTH;
                player.height=PathFinderExampleConstans.PLAYER_HEIGHT;
                player.name="player";
                gameItems.add(player);
                
            }
            private void addtoGameItemsCarrot(int x, int y){
                carrot.setImage(PathFinderExampleConstans.CARROT_PATH);
                carrot.x=x*FIELDSIZE+FIELDCORECTION+3;
                carrot.y=y*FIELDSIZE+FIELDCORECTION+8;
                carrot.width=PathFinderExampleConstans.CARROT_WIDTH;
                carrot.height=PathFinderExampleConstans.CARROT_HEIGHT;
                carrot.name="carrot";
                gameItems.add(carrot);
                
            }
               
    private TableItem positiontoTableItem(int x, int y){
        TableItem tableItem=new TableItem();
        tableItem.setColumn(x/FIELDSIZE);
        tableItem.setRow(y/FIELDSIZE);
        return tableItem;
    }
    
    private void catchtheCarrot(){
        try{
            if(path!=null && time<System.currentTimeMillis()){
                time=System.currentTimeMillis()+200;
                TableItem tableItem = path.pop();
                
                int newX = tableItem.getColumn()*FIELDSIZE+FIELDCORECTION;
                int newY = tableItem.getRow()*FIELDSIZE+FIELDCORECTION;
                
                if(newX > player.x && playerLeft){
                    player.mirrorVertically();
                    playerLeft=false;
                }
                
                if(newX < player.x && !playerLeft){
                    player.mirrorVertically();
                    playerLeft=true;
                }
                
                player.x = newX;
                player.y = newY;
            }
            }catch(java.util.EmptyStackException emptyException){
                
            }

    }
    
    private boolean pathFinder(){
        pathFinder.settingTable(map);
        pathFinder.addBarrier(BARRIER);
        pathFinder.settingStart(map.getTableItem(player.x/FIELDSIZE,player.y/FIELDSIZE));
        pathFinder.settingTarget(map.getTableItem(carrot.x/FIELDSIZE,carrot.y/FIELDSIZE));
        path = pathFinder.lookingfor();
        return path==null;
    }
    
    private void resetLevel(){
        playerLeft=true;
        for (GameItem barrier : barriers) {
            gameItems.remove(barrier);
        }
        barriers.clear();
        gameItems.remove(player);
        gameItems.remove(carrot);
        map.clear();
        initTable();
    }

}
