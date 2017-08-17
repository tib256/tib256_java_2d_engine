
package tib256_engine;

/**
 * 
 * @author Tibor Fiak
 */
public class Mouse {
    
    /**
     * When you take the mouse over the gameItem. You don' have to count gameItem borders. 
     * @param x MouseEvent e.getX()
     * @param y MouseEvent e.getY()
     * @param gameItem 
     * @return the mouse is over gameItem or not.
     */
    public static boolean MouseonItem(int x,int y,GameItem gameItem){
      if(gameItem.visible){
        if( x>gameItem.x && x< gameItem.x+gameItem.width && y>gameItem.y && y< gameItem.y+gameItem.height+Consts.MOUSECURSORHEIGHT ){ 
           return true;
        }
      }
      return false;
    }//#MouseonItem
}//end of class
