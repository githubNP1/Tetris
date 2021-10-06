package Tetris;

import java.awt.*;
import java.util.*;

public class Block {
    int x = 200, y = 100;  //center point position,  block is built and rotated around this point
    ArrayList<int[]> coords = new ArrayList<>();
    int type;
    ArrayList<ArrayList<int[]>> states = new ArrayList<>();
    int state = 0;
    Color colour;
    Color shadow;
    
    public Block(int blockType){
        this.type = blockType;
        switch(blockType){
            case 1:
                //makeBlock(x, y, x + 40, y + 20);
                //makeBlock(x - 20, y + 20, x + 20, y + 40);
                x = 150;
                y = 70;
                makeLBlock();
                colour = Color.red;
                shadow = new Color(Color.red.getRed(), Color.red.getGreen(), Color.red.getBlue(), 10);
                break;
            case 2:
                //makeBlock(x, y, x + 60, y + 20);
                //makeBlock(x + 20, y - 20, x + 40, y);
                x = 150;
                y = 70;
                makeJBlock();
                colour = Color.BLUE;
                shadow = new Color(Color.BLUE.getRed(), Color.BLUE.getGreen(), Color.BLUE.getBlue(), 10);
                break;
            case 3:
                //makeBlock(x, y, x + 40, y + 20);
                //makeBlock(x + 20, y + 20, x + 60, y + 40);
                x = 150;
                y = 70;
                makeSBlock();
                colour = Color.GREEN;
                shadow = new Color(Color.GREEN.getRed(), Color.GREEN.getGreen(), Color.GREEN.getBlue(), 10);
                break;
            case 4:
                //makeBlock(x, y, x + 40, y + 40);
                x = 150;
                y = 70;
                makeZBlock();
                colour = Color.yellow;
                shadow = new Color(Color.yellow.getRed(), Color.yellow.getGreen(), Color.yellow.getBlue(), 30);
                break;
            case 5:
                //makeBlock(x, y, x + 60, y + 20);
                //makeBlock(x + 40, y + 20, x + 60, y + 40);
                x = 150;
                y = 70;
                makeTBlock();
                colour = Color.orange;
                shadow = new Color(Color.orange.getRed(), Color.orange.getGreen(), Color.orange.getBlue(), 20);
                break;
            case 6:
                //makeBlock(x, y, x + 80, y + 20);
                x = 160;
                y = 80;
                makeLongBlock();
                colour = Color.pink;
                shadow = new Color(Color.pink.getRed(), Color.pink.getGreen(), Color.pink.getBlue(), 30);
                break;
            case 7:
                x = 140;
                y = 80;
                makeSquareBlock();
                colour = Color.black;
                shadow = new Color(Color.black.getRed(), Color.black.getGreen(), Color.black.getBlue(), 10);
                break;
        }
    }
    
    public void floorBlock(){
        for(ArrayList<int[]> state : states){
            int lowestPoint = 0;
            for(int[] coord : state){
                lowestPoint = coord[1] > lowestPoint ? coord[1] : lowestPoint;
            }
            int difference = 499 - lowestPoint;
            for(int[] coord : state){
                coord[1] += difference;
            }
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
