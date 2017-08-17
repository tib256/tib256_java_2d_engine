/**
 * 
 * 
 * 
 */
package tib256_engine.pathfinder.breadth_first;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import tib256_engine.pathfinder.table.Table;
import tib256_engine.pathfinder.table.TableItem;

/**
 *
 * @author Tibor Fiak
 * 
 */
public class PathFinder {
    
    private final ArrayList<Node> closed = new ArrayList();
    private final Queue<Node> opend = new LinkedList();
    private final Stack<TableItem> path = new Stack();
    private final ArrayList<Integer> barriers = new ArrayList();;
    
    private Table table;
    private TableItem start=new TableItem();
    private TableItem target=new TableItem();

    public void settingTable(Table table){
        this.table=table;
    }

    public void settingStart(TableItem start){
        this.start=start;
    }
    public void settingTarget(TableItem target){
        this.target=target;
    }
    public void addBarrier(int barrier){
        barriers.add(barrier);
    }
    
    public Stack<TableItem> lookingfor(){
       
        opend.clear();
        closed.clear();
        
        Node startItem = new Node();
        startItem.parent=start;
        
        if(!isBarrier(start))
         opend.add(startItem);
        else return null;
          
        if(lookingforMethod()){
            
            path();
            return path;
        }
        else return null;
    }
    
        private boolean lookingforMethod(){
            try{
                lookingForMethodOperations();
                do{
                    lookingForMethodOperations();
                }while(closed.get(closed.size()-1).child!=target && !opend.isEmpty());
                if(opend.isEmpty()) return false;
            }catch(java.lang.NullPointerException NullpointerException){
               return false; 
            }
            return true;
        }
            private void lookingForMethodOperations(){
                   addtoClosed();
                   extension();
                   opend.poll();
            }
        private void addtoClosed(){
            Node currentItem = opend.peek();
            if(!closed.contains(currentItem)){
                closed.add(currentItem);
                if(closed.size()>1)
                    closed.get(closed.size()-1).index=closed.get(closed.size()-2).index+1;
            }
        }
        private void extension(){
            Node currentItem=opend.peek();
            TableItem parent;
            if(currentItem.child==null){
               parent = currentItem.parent;
            }
            else parent=currentItem.child;
            addtoOpendPriority(parent);  
        }
            private void addtoOpendPriority(TableItem parent){
                newItemtoOpend(parent,-1,0);
                newItemtoOpend(parent,0,-1);
                newItemtoOpend(parent,1,0);
                newItemtoOpend(parent,0,1);
            }
            private void newItemtoOpend(TableItem parent,int newRowIndex,int newColumnIndex){

                if(newRowIndex==-1 && newColumnIndex==0){
                    if(parent.getRow()+newRowIndex>-1 && !isBarrier(table.getTableItem(parent.getColumn()+newColumnIndex,parent.getRow()+newRowIndex)) ){
                           Node newNode = new Node();
                           newNode.parent=parent;
                           newNode.child=table.getTableItem(parent.getColumn()+newColumnIndex,parent.getRow()+newRowIndex);
                           if(!opend.contains(newNode) && !closed.contains(newNode)){
                               opend.add(newNode);
                           }  
                    }
                }
                
                if(newRowIndex==1 && newColumnIndex==0){
                    if(parent.getRow()+newRowIndex<table.getRow() && !isBarrier(table.getTableItem(parent.getColumn()+newColumnIndex,parent.getRow()+newRowIndex)) ){
                           Node newNode = new Node();
                           newNode.parent=parent;
                           newNode.child=table.getTableItem(parent.getColumn()+newColumnIndex,parent.getRow()+newRowIndex);
                           if(!opend.contains(newNode) && !closed.contains(newNode)){
                               opend.add(newNode);
                           }  
                    }
                }
                
                if(newRowIndex==0 && newColumnIndex==-1){
                    if(parent.getColumn()+newColumnIndex>-1 && !isBarrier(table.getTableItem(parent.getColumn()+newColumnIndex,parent.getRow()+newRowIndex)) ){
                           Node newNode = new Node();
                           newNode.parent=parent;
                           newNode.child=table.getTableItem(parent.getColumn()+newColumnIndex,parent.getRow()+newRowIndex);
                           if(!opend.contains(newNode) && !closed.contains(newNode)){
                               opend.add(newNode);
                           }  
                    }
                }
                
                if(newRowIndex==0 && newColumnIndex==1){
                    if(parent.getColumn()+newColumnIndex<table.getColumn()&& !isBarrier(table.getTableItem(parent.getColumn()+newColumnIndex,parent.getRow()+newRowIndex)) ){
                           Node newNode = new Node();
                           newNode.parent=parent;
                           newNode.child=table.getTableItem(parent.getColumn()+newColumnIndex,parent.getRow()+newRowIndex);
                           if(!opend.contains(newNode) && !closed.contains(newNode)){
                               opend.add(newNode);
                           }  
                    }
                }
                
            }
            
            private boolean isBarrier(TableItem tablaElem){
              boolean isBarrier=false;

              for (Integer barrier_for : barriers) {
                 if(tablaElem.value==barrier_for){
                      isBarrier=true;
                      break;
                  }
              }

              return isBarrier;
            }   
            
        private void path(){
             path.clear();
             Node currentNode=null;
             currentNode = closed.get(closed.size()-1);
             int index = closed.size()-1;
             
             try{
                while(index>0){
                   path.push(currentNode.child);
                   currentNode = lookingforChildreninColsed(index,currentNode.parent);
                   index=currentNode.index;
                }
             }catch(java.lang.NullPointerException npex){
                 
             }
             
             
        }
            private Node lookingforChildreninColsed(int index,TableItem parent){
                for (int i = index-1; i > 0; i--) {
                    if( closed.get(i).child.equals(parent) ){
                        return closed.get(i);
                    }
                }
                return null;
            }
            
    @Override
    public String toString() {
        return "Uvonal{"  + '}';
    }
    
}//#class
