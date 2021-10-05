package Tetris;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;

public class Main extends JPanel implements KeyListener{        //need multiple threads, one for player movement and one for passive movement, maybe not
    ArrayList<Block> blocks = new ArrayList<>();

    public Main(){
        JFrame frame = new JFrame();
        frame.add(this);
        frame.setSize(1020, 750); 
        frame.addKeyListener(this); 
       
        blocks.add(new Block(7));
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
            for(ArrayList<int[]> states : block.states){
                for(int[] coord : states){
                    if(coord[1] == 499){ 
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }
    
    public Boolean checkIfNextToLeftWall(){
        for(Block block : blocks){
            for(ArrayList<int[]> states : block.states){
                for(int[] coord : states){
                    if(coord[0] == 100){ 
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }
    
    public Boolean checkIfNextToRightWall(){
        for(Block block : blocks){
            for(ArrayList<int[]> states : block.states){
                for(int[] coord : states){
                    if(coord[0] == 419){ 
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }
    
    public void moveAllDown(){
        for(Block block : blocks){
            if(!checkIfLanded()){
                block.adjustBlock(20, 1);
            }
        }
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.red);
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
        
        g.setColor(Color.blue);
        //for(Block b : blocks){
        //    for(int[] a : b.coords){
        //        g.drawRect(a[0], a[1], 1, 1);
        //    }
        //}
        for(Block b : blocks){
            for(int[] d : b.states.get(b.state)){
                g.drawRect(d[0], d[1], 1, 1);
            }
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            int x = blocks.get(0).state;
            if(x == 3){
                blocks.get(0).state = 0;
            }
            else{
                blocks.get(0).state++;
            }
            repaint();
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            int x = blocks.get(0).state;
            if(x == 0){
                blocks.get(0).state = 3;
            }
            else{
                blocks.get(0).state--;
            }
            repaint();
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
