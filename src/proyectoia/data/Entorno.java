/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia.data;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chrisecc
 */
public class Entorno {

    private int[][] originalEnv;//matrix without the robot,
    private int[] conventions;
    private int[] neighbors;
    private int size;
    private int goalCount;
    private State initialState;

    /**
     * initialize the variables
     */
    public Entorno() {

        conventions = new int[7];
        //0 libre, 1 muro,2 inicio, 3 traje, 4 cem tipo1, 5 cem tipo2, 6 item
        for (int i = 0; i < conventions.length; i++) {
            conventions[i] = i;
        }
        neighbors = new int[4];
        neighbors[0] = 1;
        neighbors[1] = 1;
        neighbors[2] = 1;
        neighbors[3] = 1;//left,up,right,down
        size = 10;//square matrix size
        originalEnv = new int[size][size];
        goalCount = 2;// 2 items
        initialState = new State();
    }

    /**
     * Loads a text file from a file
     *
     * @param fileName String with the name of the file
     */
    public void loadFile(File fileName){
        int matrix[][]=new int[size][size];
        try {
            Scanner input = new Scanner(fileName);
            for(int i = 0; i < size; ++i){
                for(int j = 0; j < size; ++j){
                    if(input.hasNextInt()){
                        matrix[i][j] = input.nextInt();
                        originalEnv[i][j] = matrix[i][j];
                        if(originalEnv[i][j]==conventions[2]){
                            initialState.setPosition(new Point(i,j));
                            originalEnv[i][j]=conventions[0];
                            
                        }
                    }
                }
            }
        initialState.setMaze(originalEnv);
            
        }catch (FileNotFoundException ex) {
            Logger.getLogger(Entorno.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*for(int i= 0; i<size;i++){
            for(int j= 0; j<size;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }*/
    }
    
    /*public void loadFile(String fileName) {
        int matrix[][] = new int[size][size];
        try {
            Scanner input = new Scanner(new File("src/proyectoia/pruebas/" + fileName + ".txt"));
            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    if (input.hasNextInt()) {
                        matrix[i][j] = input.nextInt();
                        originalEnv[i][j] = matrix[i][j];
                        if (originalEnv[i][j] == conventions[2]) {
                            initialState.setPosition(new Point(i, j));
                            originalEnv[i][j] = conventions[0];

                        }
                    }
                }
            }
            initialState.setMaze(originalEnv);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Entorno.class.getName()).log(Level.SEVERE, null, ex);
        }

    }*/

    /**
     * finds the position of the robot
     *
     * @param state State of the world to find the robot
     * @return The point with the position of the robot
     */
    public Point findRobot(State state) {

        return state.getPosition();
    }

    /**
     * Gets the closest distance to a goal
     * @param state of the world where the robot is
     * @return the L distance of the robot to the closest goal
     */
    public int findClosestGoal(State state) {
        int distanceFromGoal = 0;
        Point locationItem1 = new Point();
        Point locationItem2 = new Point();
        if (state.getGoalsAchieved() == 0) {//if no items have been found
            int distanceItem1 = 0, distanceItem2 = 0, distance1from2=0;
            int count = 0;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if ((state.getMaze()[i][j] == 6) && (count == 0)) {//if found first item
                        locationItem1.setLocation(i,j);
                        distanceItem1 = Math.abs(state.getPosition().x - locationItem1.x)+Math.abs(state.getPosition().y - locationItem1.y);//manhattan distance
                        count++;
                    }
                    if ((state.getMaze()[i][j] == 6) && (count == 1)) {//if found second item
                        locationItem2.setLocation(i,j);
                        distanceItem2 = Math.abs(locationItem2.x - state.getPosition().x)+Math.abs(locationItem2.y - state.getPosition().y);
                        //gets the distance between items
                        distance1from2 = Math.abs(locationItem2.x - locationItem1.x)+Math.abs(locationItem2.y - locationItem1.y);
                    }
                }
            }
            if (distanceItem1 <= distanceItem2) {//gets the closest item
                distanceFromGoal = distanceItem1+distance1from2;
            } else {
                distanceFromGoal = distanceItem2+distance1from2;
            }
        }
        if (state.getGoalsAchieved() == 1) {//if an item has already been found dont count the distance to the first item
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (state.getMaze()[i][j] == 6) {
                        distanceFromGoal = Math.abs(state.getPosition().x - i)+Math.abs(state.getPosition().y - j);
                    }
                }
            }
        }
        return distanceFromGoal;
    }

    /**
     * Sets the value of the 4 cells surrounding the robot
     *
     * @param state State of the world to find the neighbors
     */
    public void setNeighbors(State state) {
        Point currentPosition = findRobot(state);
        int row = (int) currentPosition.getX();
        int col = (int) currentPosition.getY();
        int L = 1, U = 1, R = 1, D = 1;

        //one position of the inner square of the matrix
        if ((row > 0 && row < (size - 1)) && (col > 0 && col < (size - 1))) {
            L = originalEnv[row][col - 1];//left
            U = originalEnv[row - 1][col];//up
            R = originalEnv[row][col + 1];//right              
            D = originalEnv[row + 1][col];//down
        }
        //upper edge 
        if (row == 0) {
            D = originalEnv[row + 1][col];
            if (col > 0 && col < (size - 1)) {//no corners
                L = originalEnv[row][col - 1];
                R = originalEnv[row][col + 1];
            }
            if (col == 0) {
                R = originalEnv[row][col + 1];
            }//left corner
            if (col == (size - 1)) {
                L = originalEnv[row][col - 1];
            }//right corner
        }
        //lower edge 
        if (row == (size - 1)) {
            U = originalEnv[row - 1][col];
            if (col > 0 && col < (size - 1)) {//no corners
                L = originalEnv[row][col - 1];
                R = originalEnv[row][col + 1];
            }
            if (col == 0) {
                R = originalEnv[row][col + 1];
            }//left corner
            if (col == (size - 1)) {
                L = originalEnv[row][col - 1];
            }//right corner
        }
        //left edge
        if (col == 0) {
            R = originalEnv[row][col + 1];
            if (row > 0 && row < (size - 1)) {//no corners
                U = originalEnv[row - 1][col];//up
                D = originalEnv[row + 1][col];//down
            }
            if (row == 0) {
                D = originalEnv[row + 1][col];
            }//left corner
            if (row == (size - 1)) {
                U = originalEnv[row - 1][col];
            }//right corner
        }
        //right edge
        if (col == (size - 1)) {
            L = originalEnv[row][col - 1];
            if (row > 0 && row < (size - 1)) {//no corners
                U = originalEnv[row - 1][col];//up
                D = originalEnv[row + 1][col];//down
            }
            if (row == 0) {
                D = originalEnv[row + 1][col];
            }//left corner
            if (row == (size - 1)) {
                U = originalEnv[row - 1][col];
            }//right corner
        }
        //the values of every neighbor in the matrix
        neighbors[0] = L;
        neighbors[1] = U;
        neighbors[2] = R;
        neighbors[3] = D;

    }

    /**
     * changes the position of the robot to a given point and restores the
     * position where it was to its original value - method unused
     *
     * @param position Point where the robot will move
     * @return a state where the robot teleports to 
     */
    public State setRobotPosition(Point position) {
        //to
        int toRow = (int) position.getX();
        int toCol = (int) position.getY();
        //updating the robot at the environment
        State newState = new State(new Point(toRow, toCol), 0);
        return newState;
    }

    /**
     * Uses setNeighbors and returns the neighbors
     *
     * @param state State of the world to get the neighbors from
     * @return Array with the values of each neighbor of the robot
     */
    public int[] getNeighbors(State state) {
        setNeighbors(state);
        return neighbors;
    }

    
    /**
     * *
     * Returns the matrix from the file
     *
     * @param mundoFile matrix with the world
     * @return matrix with the info of the world
     */
    public int[][] getMundo(File mundoFile) {

        int matrix[][] = new int[size][size];
        try {
            Scanner input = new Scanner(mundoFile);
            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    if (input.hasNextInt()) {
                        matrix[i][j] = input.nextInt();

                    }
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Entorno.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return matrix;
    }//fin getMundo

    /**
     * Returns the maximum number of items to collect
     *
     * @return int with the max number of items in the environment
     */
    public int getGoalCount() {
        return goalCount;
    }

    /**
     * Returns the initial state of the world
     * @return Initial state of the world
     */
    public State getInitialState() {
        return initialState;
    }

    /**
     * Returns the conventions used for the objects in the world
     * @return Array of numbers representing each convention
     */
    public int[] getConventions() {
        return conventions;
    }

    /**
     * Returns the size of the matrix of the world
     * @return The size of the matrix of the world
     */
    public int getSize() {
        return size;
    }

}
