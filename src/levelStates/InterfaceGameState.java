
package levelStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import tib256_engine.GameItem;

/**
 * GameLevel interface
 * @author Tibor Fiak
 */
public interface InterfaceGameState {
    
    
    public void update();
    
    public void render(Graphics g);
    
    public void keyPressed(KeyEvent e);
    
    public void keyReleased(KeyEvent e);
    
    public void mousePressed(MouseEvent e);
    
    public void mouseMoved(MouseEvent e);
    
    public void mouseDragged(MouseEvent e);
    
    public void playSound(String audioFilePath);
    
}
