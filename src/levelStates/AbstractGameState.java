
package levelStates;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.Timer;
import tib256_engine.GameItem;
import tib256_engine.Texture;
import tib256_engine.*;



/**
 *
 * @author  Tibor Fiak
 */
public abstract class AbstractGameState implements InterfaceGameState,InterfaceWorlds  {

    /**
     * gameItems this is the gameItem array, these gameItems visible on the window
     */
    public ArrayList<GameItem> gameItems = new ArrayList<>();
    
    public ArrayList<Long> times = new ArrayList<Long>();
    public ArrayList<GameItem> AnimationdgameItems = new ArrayList<GameItem>();
    
    public static int visible=0;
    
    private int state;
    
    public JFrame window;
    
    private Timer timer;
    private long time = System.currentTimeMillis();
    
    BufferedImage masolat;
    
    int id=0;
    Texture t ;
    int deg=1;
    
    double turnDegree=0;
    
    public AbstractGameState(int state,JFrame window){
      this.state=state;
      this.window = window;
    }

    public int getState() {
        return state;
    }

    /**
     * This method draw the pictures from gameItems array.
     * Don't touch this method.
     * @param Graphics
     */
    @Override
    public void render(Graphics g) {      
        for (GameItem gameItem : gameItems) {
          if(gameItem.visible){
            Graphics2D g2d = (Graphics2D) g;
            AffineTransform tx = AffineTransform.getTranslateInstance(gameItem.x,gameItem.y);
            tx.scale(gameItem.width/(double)gameItem.getImage().getWidth(),gameItem.height/(double)gameItem.getImage().getHeight());
            tx.rotate(Math.toRadians(-gameItem.angle),gameItem.getImage().getWidth()/2,gameItem.getImage().getHeight()/2);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            BufferedImage newImage = new BufferedImage(gameItem.width,gameItem.height,BufferedImage.TYPE_INT_ARGB);
            g2d.drawImage(gameItem.getImage(),tx,null);
          }
        }
    }
    
    public void addAnimationGameItem(GameItem gi){
       AnimationdgameItems.add(gi);
       times.add(new Long(0));
    }
    
    public void animationBackAndForth(GameItem gi,int fast){
        int this_=1;
        long time;
        
        for (int i=0;i<AnimationdgameItems.size();i++) {
            if(AnimationdgameItems.get(i)==gi)
                this_=i;
                break;
        }
          
          time=times.get(this_).longValue();
        
          if(time<System.currentTimeMillis()){
              
            times.remove(this_);
            times.add(this_, new Long(System.currentTimeMillis()+fast)); 

            if(!AnimationdgameItems.get(this_).animend)  
                AnimationdgameItems.get(this_).currentFrame++;
            else
                AnimationdgameItems.get(this_).currentFrame--;
            
            if(AnimationdgameItems.get(this_).currentFrame==AnimationdgameItems.get(this_).numberOfFrames-1) gi.animend=true;
            if(AnimationdgameItems.get(this_).currentFrame==0) AnimationdgameItems.get(this_).animend=false;
            
          }
         gi.updateAnimation();
         
    }
    
    /**
     * Play animation
     * @param gi GameItem ,which included animation 
     * @param fast animation speed , a picture show time.
     */
    public void animation(GameItem gi,int fast){
        int this_=0;
        long time;
        
        for (int i=0;i<AnimationdgameItems.size();i++) {
            if(AnimationdgameItems.get(i)==gi)
                this_=i;
                break;
        }
        
          time=times.get(this_).longValue();
          
          if(time<System.currentTimeMillis()){
                times.remove(this_);
                times.add(this_, new Long(System.currentTimeMillis()+fast));
                AnimationdgameItems.get(this_).currentFrame++;
            if(AnimationdgameItems.get(this_).currentFrame==AnimationdgameItems.get(this_).numberOfFrames) AnimationdgameItems.get(this_).currentFrame=0;
          }
          
          
         gi.updateAnimation();
         
    }
    
    /**
     * You can play wav audio file.
     * @param audioFilePath example: "sounds/click.wav"
     */
    @Override
    public void playSound(String audioFilePath) {
        File audioFile = new File(audioFilePath);
        try {
            final int BUFFER_SIZE = 4096;
            
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
 
            AudioFormat format = audioStream.getFormat();
 
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
 
            SourceDataLine audioLine = (SourceDataLine) AudioSystem.getLine(info);
 
            audioLine.open(format);
 
            audioLine.start();
             
            System.out.println("Playback started.");
             
            byte[] bytesBuffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
 
            while ((bytesRead = audioStream.read(bytesBuffer)) != -1) {
                audioLine.write(bytesBuffer, 0, bytesRead);
            }
             
            audioLine.drain();
            audioLine.close();
            audioStream.close();
             
            System.out.println("Playback completed.");
             
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }      
    }
    
     public void windowSize(int width,int height){
             window.setSize(new Dimension(width,height));
    }

}
