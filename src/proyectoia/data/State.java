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

    public boolean isSuit() {
        return suit;
    }

    public void setSuit(boolean suit) {
        this.suit = suit;
    }
    
    public State() {
        goalsAchieved=0;
    }
    
    public State(Point position, int[][] maze, int goalsAchieved){
        this.position = position;
        this.maze = maze;
        this.goalsAchieved = goalsAchieved;
    }

    public State(Point position, int goalsAchieved) {
        this.position = position;
        this.goalsAchieved =+ goalsAchieved;
    }

    public int getGoalsAchieved() {
        return goalsAchieved;
    }

    public void setGoalsAchieved(int goalsAchieved) {
        this.goalsAchieved = this.goalsAchieved+ goalsAchieved;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
    
    public int[][] getMaze() {
        
        return maze;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }
    
    public void removeItem(Point item){
        this.maze[item.x][item.y]=0;
    }
}
