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
    private int goalsAchieved;
    private int cost;//cost of getting to this state

    public State() {
        goalsAchieved=0;
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
    

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
    
    
}
