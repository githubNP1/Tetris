package Tetris;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;

public class Main extends JPanel implements KeyListener{        //need multiple threads, one for player movement and one for passive movement, maybe not
    ArrayList<Block> blocks = new ArrayList<>();
    ArrayList<int[]> flooredBlocks = new ArrayList<>();

    public Main(){
        JFrame frame = new JFrame();
        frame.add(this);
        frame.setSize(1020, 750); 
        this.setBackground(Color.white); 
        frame.addKeyListener(this); 
       
        blocks.add(new Block(6));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {}
            //checkIfLanded();
            moveAllDown();
        }
    }
    
    //how to rotate block when right next to wall
    //how to stop block from moving through wall
    
    public Boolean checkIfLanded(){ //check if any coords in a block are directly above the floor
        for(Block block : blocks){
            for(int[] coord : block.states.get(block.state)){
                if(coord[1] == 499){ 
                    return true;
                }
            }
            return false;
        }
        return false;
    }
    
    public Boolean checkIfCollision(){
        
    }
    
    public ArrayList<int[]> foreshadowing(ArrayList<int[]> block){
        ArrayList<int[]> shadow = new ArrayList<>();
        int lowestPoint = 0;
        for(int[] coord : block){
            lowestPoint = coord[1] > lowestPoint ? coord[1] : lowestPoint;
        }
        int difference = 499 - lowestPoint;
        for(int[] coord : block){
            int[] newCoord = {coord[0], coord[1] + difference};
            //coord[1] += difference;
            shadow.add(newCoord);
        }
        return shadow;
    }
    
    public Boolean checkIfNextToLeftWall(){
        for(Block block : blocks){
            for(int[] coord : block.states.get(block.state)){
                if(coord[0] == 100){ 
                    return true;
                }
            }
            return false;
        }
        return false;
    }
    
    public Boolean checkIfNextToRightWall(){
        for(Block block : blocks){
            for(int[] coord : block.states.get(block.state)){
                if(coord[0] == 419){ 
                    return true;
                }
            }
            return false;
        }
        return false;
    }
    
    public Boolean checkIfCanRotateNextToLeftWall(ArrayList<int[]> nextState){
        for(int[] coord : nextState){
            if(coord[0] < 100){ 
                return true;
            }
        }
        return false;
    }
    
    public Boolean checkIfCanRotateNextToRightWall(ArrayList<int[]> nextState){
        for(int[] coord : nextState){
            if(coord[0] > 419){ 
                return true; //should be other way round
            }
        }
        return false;
    }
    
    public void addToFlooredBlocks(Block block){
        for(int[] coord : block.states.get(block.state)){
            flooredBlocks.add(coord);
        }
    }
    
    public void moveAllDown(){
        Iterator iter = blocks.iterator();
        while(iter.hasNext()){
            Block block = (Block) iter.next();
            if(checkIfLanded()){
                addToFlooredBlocks(block);
                iter.remove();
            }
            else{
                block.adjustBlock(20, 1);
                repaint();
            }
        }
        
        /*for(Block block : blocks){
            if(!checkIfLanded()){
                block.adjustBlock(20, 1);
            }
        }
        repaint();*/
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.gray);
        int a = 100; int c = 100; //c not needed if same value as a 
        for(int i = a - 5; i < a + 325; i++){
            for(int j = a - 5; j < a; j++){
                g.drawRect(i, j, 1, 1);
            }
        }
        for(int i = a - 5; i < a; i++){
            for(int j = a; j < a + 400; j++){
                g.drawRect(i, j, 1, 1);
            }
        }
        for(int i = a + 320; i < a + 325; i++){
            for(int j = a; j < a + 400; j++){
                g.drawRect(i, j, 1, 1);
            }
        }
        for(int i = a - 5; i < a + 325; i++){
            for(int j = a + 400; j < a + 405; j++){
                g.drawRect(i, j, 1, 1);
            }
        }
        
        //g.setColor(Color.blue);
        //for(Block b : blocks){
        //    for(int[] a : b.coords){
        //        g.drawRect(a[0], a[1], 1, 1);
        //    }
        //}
        for(Block b : blocks){
            g.setColor(b.colour); 
            for(int[] d : b.states.get(b.state)){
                g.drawRect(d[0], d[1], 1, 1);
            }
            g.setColor(b.shadow);
            for(int[] d : foreshadowing(b.states.get(b.state))){
                g.drawRect(d[0], d[1], 1, 1);
            }
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            int y;
            int x = blocks.get(0).state;
            if(x == 3){
                y = 0;
            }
            else{
                y = x + 1;
            }
            if(!checkIfCanRotateNextToLeftWall(blocks.get(0).states.get(y)) && !checkIfCanRotateNextToRightWall(blocks.get(0).states.get(y))){
                blocks.get(0).state = y;
                repaint();
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            int x = blocks.get(0).state;
            int y;
            if(x == 0){
                y = 3;
            }
            else{
                y = x - 1;
            }
            if(!checkIfCanRotateNextToLeftWall(blocks.get(0).states.get(y)) && !checkIfCanRotateNextToRightWall(blocks.get(0).states.get(y))){
                blocks.get(0).state = y;
                repaint();
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(!checkIfNextToRightWall()){
                blocks.get(0).adjustBlock(20, 0);
                repaint();
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(!checkIfNextToLeftWall()){
                blocks.get(0).adjustBlock(-20, 0); 
                repaint();
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_SPACE){
            blocks.get(0).floorBlock();
            repaint(); 
        }
        //try {
        //    Thread.sleep(500);
        //} catch (InterruptedException ex) {}
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    
    public static void main(String[] args){
        new Main();
    }
}
