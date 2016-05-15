/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia.data;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chrisecc
 */
public class Entorno {
    private Map originalEnv;//matrix without the robot,
    private int [] conventions;
    private int [] neighbors;
    private int size;

    public int[] getConventions() {
        return conventions;
    }

    public int getSize() {
        return size;
    }
    private int goalCount;
    private State initialState;
    
    /**
     * initialize the variables
     */
    public Entorno(){
        
        conventions = new int[7];
        //0 libre, 1 muro,2 inicio, 3 traje, 4 cem tipo1, 5 cem tipo2, 6 item
        for(int i=0;i<conventions.length;i++){conventions[i]=i;}
        neighbors=new int[4];
        neighbors[0]=1;neighbors[1]=1;neighbors[2]=1;neighbors[3]=1;//left,up,right,down
        originalEnv = new HashMap();
        goalCount=2;// 2 items
        initialState=new State();
    }
    
    
    /**
     * Loads a text file
     * @param fileName String with the name of the file
     */
    public void loadFile(String fileName){
        int matrix[][]=new int[size][size];
        try {
            Scanner input = new Scanner(new File("src/proyectoia/pruebas/"+fileName+".txt"));
            for(int i = 0; i < size; ++i){
                for(int j = 0; j < size; ++j){
                    if(input.hasNextInt()){
                        matrix[i][j] = input.nextInt();
                        originalEnv.put(i+""+j, matrix[i][j]);
                        if((int)originalEnv.get(i+""+j)==conventions[2]){
                            initialState.setPosition(new Point(i,j));
                            originalEnv.put(i+""+j,conventions[0]);
                            
                        }
                    }
                }
            }
        initialState.setMaze(originalEnv);
            
        }catch (FileNotFoundException ex) {
            Logger.getLogger(Entorno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /***
     * 
     * @param mundoFile
     * @return 
     */
     public int[][] getMundo(File mundoFile){
        
        
        int matrix[][]=new int[size][size];
        try {
            Scanner input = new Scanner(mundoFile);
            for(int i = 0; i < size; ++i){
                for(int j = 0; j < size; ++j){
                    if(input.hasNextInt()){
                        matrix[i][j] = input.nextInt();
                        
                        
                    }
                }
            }
            
            
                        
        }catch (FileNotFoundException ex) {
            Logger.getLogger(Entorno.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i= 0; i<size;i++){
            for(int j= 0; j<size;j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        return matrix;
    }
    
    /**
     * finds the position of the robot
     * @param state State of the world in which to find the robot
     */
    public Point findRobot(State state) {
        return state.getPosition();
    }
    
    
    /**
     * Sets the value of the 4 cells surrounding the robot
     * @param state State of the world to find the neighbors from
     */
    public void setNeighbors(State state){
        Point currentPosition = findRobot(state);
        int row= (int) currentPosition.getX();
	int col= (int) currentPosition.getY();
	int L=1,U=1,R=1,D=1;

	//one position of the inner square of the matrix
	if((row>0 && row<(size-1)) && (col>0 && col<(size-1)) ){
            L= (int) originalEnv.get(row+""+(col-1));//left
            U= (int)originalEnv.get((row-1)+""+col);//up
            R= (int)originalEnv.get(row+""+(col+1));//right              
            D= (int)originalEnv.get((row+1)+""+col);//down
	}
	//upper edge 
	if(row==0){
            D=(int)originalEnv.get((row+1)+""+col);
            if(col>0 && col<(size-1)){//no corners
                L=(int) originalEnv.get(row+""+(col-1));
                R= (int) originalEnv.get(row+""+(col+1));
            }
            if(col==0){R= (int)originalEnv.get(row+""+(col+1));}//left corner
            if(col==(size-1)){L= (int)originalEnv.get(row+""+(col-1));}//right corner
	}
	//lower edge 
	if(row==(size-1)){
            U=(int)originalEnv.get((row-1)+""+col);
            if(col>0 && col<(size-1)){//no corners
                L= (int)originalEnv.get(row+""+(col-1));
                R= (int)originalEnv.get(row+""+(col+1));
            }
            if(col==0){R= (int)originalEnv.get(row+""+(col+1));}//left corner
            if(col==(size-1)){L= (int)originalEnv.get(row+""+(col-1));}//right corner
	}
	//left edge
	if(col==0){
            R=(int)originalEnv.get(row+""+(col+1));
            if(row>0 && row<(size-1)){//no corners
                U= (int)originalEnv.get((row-1)+""+col);//up
                D= (int)originalEnv.get((row+1)+""+col);//down
            }
            if(row==0){D= (int)originalEnv.get((row+1)+""+col);}//left corner
            if(row==(size-1)){U= (int)originalEnv.get((row-1)+""+col);}//right corner
	}
	//right edge
	if(col==(size-1)){
            L=(int)originalEnv.get(row+""+(col-1));
            if(row>0 && row<(size-1)){//no corners
                U= (int)originalEnv.get((row-1)+""+col);//up
                D= (int)originalEnv.get((row+1)+""+col);//down
            }
            if(row==0){D= (int)originalEnv.get((row+1)+""+col);}//left corner
            if(row==(size-1)){U= (int)originalEnv.get((row-1)+""+col);}//right corner
	}

	neighbors[0]= L;
	neighbors[1]= U;
	neighbors[2]= R;
	neighbors[3]= D;
        
    }  
    
    /**
     * changes the position of the robot to a given point and restores the position
     * where it was to its original value
     * @param state State of the world to get the robot's current position
     * @param position Point where the robot will move
     */
    public State setRobotPosition(Point position){
        //to
        int toRow= (int) position.getX();
        int toCol= (int) position.getY();
        //updating the robot at the environment
        State newState = new State(new Point(toRow,toCol),0);
        return newState;
    }
    
      
    /**
     * Uses setNeighbors and returns the neighbors 
     * @param state State of the world to get the neighbors from
     * @return Array with the values of each neighbor of the robot
     */
    public int [] getNeighbors(State state){
        setNeighbors(state);
        //System.out.println("left: "+neighbors[0]+" up: "+neighbors[1]+" right: "+neighbors[2]+" down: "+neighbors[3]);
        return neighbors;
    }
    
   
    /**
     * returns the original environment, without the robot
     * @return the matrix with the original environment
     */
    public Map getOriginalEnv(){
        /*for(int i = 0; i < size; ++i){
            for(int j = 0; j < size; ++j){
                System.out.print(" "+originalEnv[i][j]+" ");
            }
            System.out.println();
        }*/
        return originalEnv;
    }
        
    /**
     * Returns the maximum number of items to collect
     * @return int with the max number of items in the environment
     */
    public int getGoalCount(){
        return goalCount;
    }

    public State getInitialState() {
        return initialState;
    }
      
    
}
