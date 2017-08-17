/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tib256_engine.pathfinder.table;

import java.util.Vector;




/**
 *
 * @author Tibor Fiak
 */
public class Table {
    
    
    
    private int row;
    private int column;
    
    Vector<TableItem> table = new Vector<TableItem>();

    public int getRow() {
        return row;
    }

    public void setRow(int sor) {
        if(sor>-1)
        this.row = sor;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int oszlop) {
        if(row>-1)
        this.column = oszlop;
    }
    
    public void addItem(int row,int column,int value){
        TableItem tableItem = new TableItem();
        tableItem.setRow(row);
        tableItem.setColumn(column);
        tableItem.value=value;
        
        if(tableItem.getColumn() < this.column && tableItem.getRow() < this.row){
            table.add(tableItem);
        }
    }
    
    public int getTableValue(int row,int column){
        return table.get(row*this.row+column).value;
    }
    
    public void setTableValue(int row,int column,int value){
        table.get(row*this.row+column).value=value;
    }
    
    public TableItem getTableItem(int column , int row){
        return table.get(row*this.row+column);
    }
    
    public void clear(){
        table.clear();
    }
    
    @Override
    public String toString() {
       StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
               stringBuilder.append(table.get(i*this.row+j).value).append(" ");
            }
            stringBuilder.append("\n");
        }
        
       return stringBuilder.toString();
    }
    
           
    
}
