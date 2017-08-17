
package game.levels;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import levelStates.AbstractGameState;
import tib256_engine.GameItem;
import tib256_engine.Mouse;

/**
 *
 * @author Tibor Fiak
 */
public class Animation_test extends AbstractGameState{
    
        GameItem bagoly = new GameItem();
        GameItem bagoly2 = new GameItem();

    public Animation_test(int state, JFrame window) {
        super(state, window);
        
        bagoly.width=128;
        bagoly.height=256;
        bagoly.rows=3;
        bagoly.colummns=3;
        bagoly.numberOfFrames=7;
        bagoly.setAnimationImage("frames2.png");
        gameItems.add(bagoly);
        addAnimationGameItem(bagoly);
        
        
        bagoly2.x=250;
        bagoly2.width=128;
        bagoly2.height=256;
        bagoly2.rows=3;
        bagoly2.colummns=3;
        bagoly2.numberOfFrames=6;
        bagoly2.setAnimationImage("frames2.png");
        gameItems.add(bagoly2);
        addAnimationGameItem(bagoly2);
        
        windowSize(1024,768);
        
    }
    
    

    @Override
    public void update() {
        animationBackAndForth(bagoly2,83); //6 picture back and forth equals 12 picture 1000/12=83
        animation(bagoly,142);  //7 picture under a second , 1000/7=142, so a picture show time 142 millisecond.
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
