
package tib256_engine.pathfinder.table;

/**
 *
 * @author Tibor Fiak
 */
 public class TableItem {
            private int row=0;
            private int column=0;

            public int value=0;

            public int getRow() {
                return row;
            }

            public void setRow(int sor) {
                if(column>-1)
                this.row = sor;
            }

            public int getColumn() {
                return column;
            }

            public void setColumn(int oszlop) {
                if(oszlop>-1)
                this.column = oszlop;
            }

            public int zeroorOne(int szazalek){

                int valoszinuseg=(int)(Math.random()*100);
                if(szazalek>valoszinuseg)
                    return 1;
                else return 0;

            }


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
        
        final TableItem other = (TableItem) obj;
        
        return this.row == other.row && this.column == other.column;
    }
            
    

    @Override
    public String toString() {
        return "[" + column + "," + row + "]";
    }
            
            
    
    
}
