/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia.data;

import java.awt.Point;

/**
 *
 * @author chris
 */
public class State {

    private Point position;
    private int [][] maze;
    private int goalsAchieved;
    private boolean suit;

    
    /**
     * Constructor of the State class
     */
    public State() {
        goalsAchieved=0;
    }
    
    /**
     * Constructor of the State class
     * @param position position where the robot is
     * @param maze world matrix 
     * @param goalsAchieved goals achieved by the robot
     */
    public State(Point position, int[][] maze, int goalsAchieved){
        this.position = position;
        this.maze = maze;
        this.goalsAchieved = goalsAchieved;
    }

    /**
     * Constructor of State class
     * @param position position where the robot is
     * @param goalsAchieved goals achieved by the robot
     */
    public State(Point position, int goalsAchieved) {
        this.position = position;
        this.goalsAchieved =+ goalsAchieved;
    }

    /**
     * Returns the quantity of goals achieved in this state
     * @return The quantity of goals
     */
    public int getGoalsAchieved() {
        return goalsAchieved;
    }

    /**
     * Increments the quantity of goals achieved
     * @param goalsAchieved goals gotten by the robot
     */
    public void setGoalsAchieved(int goalsAchieved) {
        this.goalsAchieved = this.goalsAchieved+ goalsAchieved;
    }

    /**
     * Returns the position of the robot in this state
     * @return position of the robot
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Sets the position of the robot in this state
     * @param position Position where the robot will be
     */
    public void setPosition(Point position) {
        this.position = position;
    }
    
    /**
     * Returns the world matrix of this state
     * @return matrix of this state
     */
    public int[][] getMaze() {
        
        return maze;
    }

    /**
     * Sets the world matrix of this state
     * @param maze world matrix to be set
     */
    public void setMaze(int[][] maze) {
        this.maze = maze;
    }
    
    /**
     * Removes an object from the world matrix and sets its value to 0
     * @param item location of the object to be removed
     */
    public void removeItem(Point item){
        this.maze[item.x][item.y]=0;
    }
    
    
    /**
     * Returns whether the robot is wearing a suit or not
     * @return true if the robot is wearing a suit and false otherwise
     */
    public boolean isSuit() {
        return suit;
    }

    /**
     * Sets whether the robot has found a suit or not
     * @param suit true if the robot found a suit, false otherwise 
     */
    public void setSuit(boolean suit) {
        this.suit = suit;
    }
}
