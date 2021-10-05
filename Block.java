package Tetris;

import java.util.*;

public class Block {
    int x = 200, y = 100;  //center point position,  block is built and rotated around this point
    ArrayList<int[]> coords = new ArrayList<>();
    int type;
    ArrayList<ArrayList<int[]>> states = new ArrayList<>();
    int state = 0;
    
    public Block(int blockType){
        this.type = blockType;
        switch(blockType){
            case 1:
                //makeBlock(x, y, x + 40, y + 20);
                //makeBlock(x - 20, y + 20, x + 20, y + 40);
                x = 150;
                y = 70;
                makeLBlock();
                break;
            case 2:
                //makeBlock(x, y, x + 60, y + 20);
                //makeBlock(x + 20, y - 20, x + 40, y);
                x = 150;
                y = 70;
                makeJBlock();
                break;
            case 3:
                //makeBlock(x, y, x + 40, y + 20);
                //makeBlock(x + 20, y + 20, x + 60, y + 40);
                x = 150;
                y = 70;
                makeSBlock();
                break;
            case 4:
                //makeBlock(x, y, x + 40, y + 40);
                x = 150;
                y = 70;
                makeZBlock();
                break;
            case 5:
                //makeBlock(x, y, x + 60, y + 20);
                //makeBlock(x + 40, y + 20, x + 60, y + 40);
                x = 150;
                y = 70;
                makeTBlock();
                break;
            case 6:
                //makeBlock(x, y, x + 80, y + 20);
                x = 160;
                y = 80;
                makeLongBlock();
                break;
            case 7:
                x = 140;
                y = 80;
                makeSquareBlock();
                break;
        }
    }
    
    public void makeBlock(int x1, int y1, int x2, int y2){
        for(int i = x1; i < x2; i++){
            for(int j = y1; j < y2; j++){
                int[] x = {i, j};
                coords.add(x);
            }
        }
    }
    
    public ArrayList makeBlock2(int x1, int y1, int x2, int y2, ArrayList<int[]> coords){
        for(int i = x1; i < x2; i++){
            for(int j = y1; j < y2; j++){
                int[] x = {i, j};
                coords.add(x);
            }
        }
        return coords;
    }
    
    public void makeLongBlock(){
        ArrayList<int[]> coords = new ArrayList<>();
        coords = makeBlock2(x - 40, y - 20, x + 40, y, coords);
        states.add(coords);
        coords = new ArrayList<>();
        coords = makeBlock2(x, y - 40, x + 20, y + 40, coords);
        states.add(coords);
        coords = new ArrayList<>();
        coords = makeBlock2(x - 40, y, x + 40, y + 20, coords);
        states.add(coords);
        coords = new ArrayList<>();
        coords = makeBlock2(x - 20, y - 40, x, y + 40, coords);
        states.add(coords);
    }
    
    public void makeSquareBlock(){
        ArrayList<int[]> coords = new ArrayList<>();
        coords = makeBlock2(x - 20, y - 20, x + 20, y + 20, coords);
        states.add(coords);
        for(int i = 0; i < 3; i++){
            ArrayList<int[]> coords2 = new ArrayList<>();
            for(int[] coord : coords){
                coords2.add(coord.clone());
            }
            states.add(coords2);
        }
        
        //states.add(new ArrayList<>(coords));
        //states.add(new ArrayList<>(coords));
        //states.add(new ArrayList<>(coords));
    }
    
    public void makeLBlock(){
        ArrayList<int[]> coords = new ArrayList<>();
        coords = makeBlock2(x - 30, y - 10, x + 30, y + 10, coords);
        coords = makeBlock2(x + 10, y - 30, x + 30, y - 10, coords);
        states.add(coords);
        coords = new ArrayList<>();
        coords = makeBlock2(x - 10, y - 30, x + 10, y + 30, coords);
        coords = makeBlock2(x + 10, y + 10, x + 30, y + 30, coords);
        states.add(coords);
        coords = new ArrayList<>();
        coords = makeBlock2(x - 30, y - 10, x + 30, y + 10, coords);
        coords = makeBlock2(x - 30, y + 10, x - 10, y + 30, coords);
        states.add(coords);
        coords = new ArrayList<>();
        coords = makeBlock2(x - 10, y - 30, x + 10, y + 30, coords);
        coords = makeBlock2(x - 30, y - 30, x - 10, y - 10, coords);
        states.add(coords);
    }
    
    public void makeJBlock(){
        ArrayList<int[]> coords = new ArrayList<>();
        coords = makeBlock2(x - 30, y - 10, x + 30, y + 10, coords);
        coords = makeBlock2(x - 30, y - 30, x - 10, y - 10, coords);
        states.add(coords);
        coords = new ArrayList<>();
        coords = makeBlock2(x - 10, y - 30, x + 10, y + 30, coords);
        coords = makeBlock2(x + 10, y - 30, x + 30, y - 10, coords);
        states.add(coords);
        coords = new ArrayList<>();
        coords = makeBlock2(x - 30, y - 10, x + 30, y + 10, coords);
        coords = makeBlock2(x + 10, y + 10, x + 30, y + 30, coords);
        states.add(coords);
        coords = new ArrayList<>();
        coords = makeBlock2(x - 10, y - 30, x + 10, y + 30, coords);
        coords = makeBlock2(x - 30, y + 10, x - 10, y + 30, coords);
        states.add(coords);
    }
    
    public void makeTBlock(){
        ArrayList<int[]> coords = new ArrayList<>();
        coords = makeBlock2(x - 30, y - 10, x + 30, y + 10, coords);
        coords = makeBlock2(x - 10, y - 30, x + 10, y - 10, coords);
        states.add(coords);
        coords = new ArrayList<>();
        coords = makeBlock2(x - 10, y - 30, x + 10, y + 30, coords);
        coords = makeBlock2(x + 10, y - 10, x + 30, y + 10, coords);
        states.add(coords);
        coords = new ArrayList<>();
        coords = makeBlock2(x - 30, y - 10, x + 30, y + 10, coords);
        coords = makeBlock2(x - 10, y + 10, x + 10, y + 30, coords);
        states.add(coords);
        coords = new ArrayList<>();
        coords = makeBlock2(x - 10, y - 30, x + 10, y + 30, coords);
        coords = makeBlock2(x - 30, y - 10, x - 10, y + 10, coords);
        states.add(coords);
    }
    
    public void makeSBlock(){
        ArrayList<int[]> coords = new ArrayList<>();
        coords = makeBlock2(x - 30, y - 10, x + 10, y + 10, coords);
        coords = makeBlock2(x - 10, y - 30, x + 30, y - 10, coords);
        states.add(coords);
        coords = new ArrayList<>();
        coords = makeBlock2(x - 10, y - 30, x + 10, y + 10, coords);
        coords = makeBlock2(x + 10, y - 10, x + 30, y + 30, coords);
        states.add(coords);
        coords = new ArrayList<>();
        coords = makeBlock2(x - 10, y - 10, x + 30, y + 10, coords);
        coords = makeBlock2(x - 30, y + 10, x + 10, y + 30, coords);
        states.add(coords);
        coords = new ArrayList<>();
        coords = makeBlock2(x - 30, y - 30, x - 10, y + 10, coords);
        coords = makeBlock2(x - 10, y - 10, x + 10, y + 30, coords);
        states.add(coords);
    }
    
    public void makeZBlock(){
        ArrayList<int[]> coords = new ArrayList<>();
        coords = makeBlock2(x - 30, y - 30, x + 10, y - 10, coords);
        coords = makeBlock2(x - 10, y - 10, x + 30, y + 10, coords);
        states.add(coords);
        coords = new ArrayList<>();
        coords = makeBlock2(x - 10, y - 10, x + 10, y + 30, coords);
        coords = makeBlock2(x + 10, y - 30, x + 30, y + 10, coords);
        states.add(coords);
        coords = new ArrayList<>();
        coords = makeBlock2(x - 30, y - 10, x + 10, y + 10, coords);
        coords = makeBlock2(x - 10, y + 10, x + 30, y + 30, coords);
        states.add(coords);
        coords = new ArrayList<>();
        coords = makeBlock2(x - 30, y - 10, x - 10, y + 30, coords);
        coords = makeBlock2(x - 10, y - 30, x + 10, y + 10, coords);
        states.add(coords);
    }
    
    public void adjustBlock(int displacement, int axis){
        for(ArrayList<int[]> state : states){
            for(int[] coords : state){
                coords[axis] += displacement;
            }
        }
    }
}
