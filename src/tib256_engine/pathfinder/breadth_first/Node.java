
package tib256_engine.pathfinder.breadth_first;

import tib256_engine.pathfinder.table.TableItem;

/**
 *
 * @author Tibor Fiak
 */
public class Node {
   
    TableItem parent=null;
    TableItem child=null;
    int index=0;
    
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        
        return this.parent.getRow() == other.parent.getRow() 
                && this.parent.getColumn()== other.parent.getColumn()
                && other.child!=null
                && this.child.getRow() == other.child.getRow()
                && this.child.getColumn()== other.child.getColumn();
    }

    @Override
    public String toString() {
        return "{" + parent + ">>" + child +"}";
    }
    
    
    
    

   
}
