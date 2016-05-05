/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia.data;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chris
 */
public class Node {
    
    private State state;
    private Node parent;
    private int operator;//4:left, 8:up, 6:right, 5:down
    private int depth;
    private int cost;
    
    
    /**
     * Constructor of the Node class
     */
    public Node(State state){
        this.state = state;
        depth=0;
        cost=0;
    }
    
    /**
     * parameterized constructor of the Node class
     * @param state
     * @param parent
     * @param operator
     * @param depth
     * @param cost
     */
    public Node(State state,Node parent,int operator,int cost){
        this.state = state;
        this.parent=parent;
        this.operator = operator;
        if(parent!=null){
            this.cost = parent.cost + cost;
            this.depth= parent.depth+ 1;
        }else{
            this.cost = cost;
            this.depth = 0;
        }        
    }
    
    /**
     * Calls the corresponding directional movement 
     * @param operator int corresponding to each of the 4 directions
     * @param environment world of the robot
     */
    public State applyOperator(int operator,Entorno environment,State state){
        State nextState=new State();
        if(operator==4){
            nextState=moveLeft(environment,state);
        }
        if(operator==6){
            nextState=moveRight(environment,state);
        }
        if(operator==8){
            nextState=moveUp(environment,state);
        }
        if(operator==5){
            nextState=moveDown(environment,state);
        }
        //ensures to keep track of the goals by the parent's state
        //nextState.setGoalsAchieved(state.getGoalsAchieved());
        return nextState;
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
    }
    
    /**
     * Moves to the left
     * @param environment world of the robot
     */
    private State moveLeft(Entorno environment,State state) {
        int neighbors[]= new int[4];
        neighbors = environment.getNeighbors(state);
        State nextState=new State();
        if(neighbors[0]!=1){
            Point robot = environment.findRobot(state);
            int row = (int) robot.getX();
            int col = ((int) robot.getY())-1;
            Point movement = new Point(row,col);
            nextState = environment.setRobotPosition(movement);
            nextState.setCost(neighbors[0]);
        }
        return nextState;
    }
    
    /**
     * Moves up 
     * @param environment world of the robot
     */
    private State moveUp(Entorno environment,State state) {
        int neighbors[]= new int[4];
        neighbors = environment.getNeighbors(state);
        State nextState=new State();
        if(neighbors[1]!=1){
            Point robot = environment.findRobot(state);
            int row = ((int) robot.getX())-1;
            int col = (int) robot.getY();
            Point movement = new Point(row,col);
            nextState = environment.setRobotPosition(movement);
            nextState.setCost(neighbors[1]);
        }
        return nextState;
    }

    /**
     * Moves to the right
     * @param environment world of the robot
     */
    private State moveRight(Entorno environment,State state) {
        int neighbors[]= new int[4];
        neighbors = environment.getNeighbors(state);
        State nextState=new State();
        if(neighbors[2]!=1){
            Point robot = environment.findRobot(state);
            int row = (int) robot.getX();
            int col = ((int) robot.getY())+1;
            Point movement = new Point(row,col);
            nextState = environment.setRobotPosition(movement);
            nextState.setCost(neighbors[2]);
        }
        return nextState;
    }
        
        
    /**
     * Moves down
     * @param environment world of the robot
     */
    private State moveDown(Entorno environment,State state) {
        int neighbors[]= new int[4];
        neighbors = environment.getNeighbors(state);
        State nextState=new State();
        if(neighbors[3]!=1){
            Point robot = environment.findRobot(state);
            int row = ((int) robot.getX())+1;
            int col = (int) robot.getY();
            Point movement = new Point(row,col);
            nextState = environment.setRobotPosition(movement);
            nextState.setCost(neighbors[3]);
        }
        return nextState;
    }
    
    /**
     * Verifies if all goals have been achieved
     * @param state Entorno with the world object
     * @return boolean whether it is or not a goal
     */    
    public boolean isItGoal(Entorno environment){
        return (state.getGoalsAchieved()==environment.getGoalCount());
    }
    
    /**
     * Checks if this node is the root node
     * @return 
     */
    public boolean isItRoot(){
        boolean isIt = false;
        if(parent==null){
            isIt=true;
        }
        return isIt;
    }
    
    /**
     * Gets the list of the solution nodes
     * @return 
     */
    public List<Node> getPathFromRoot() {
	List<Node> path = new ArrayList<Node>();
	Node thisNode = this;
	while (!thisNode.isItRoot()) {
		path.add(0, thisNode);
		thisNode = thisNode.getParent();
	}
	// adds the root node
	path.add(0, thisNode);
	return path;
    }
    
    /**
     * returns the value of the cell where the robot stands
     * @param environment Entorno world of the robot
     * @return int with the value of the cell
     */
    public int getPositionValue(Entorno environment){
        Point position = environment.findRobot(state);
        int matrix[][]=environment.getOriginalEnv();
        int value = matrix[(int)position.getX()][(int)position.getY()];
        return value;
    }
    
    public void setPositionValue(Entorno environment, int value){
        Point position = environment.findRobot(state);
        int matrix[][]=environment.getOriginalEnv();
        matrix[(int)position.getX()][(int)position.getY()]= value;
    }

    public Node getParent() {
        return parent;
    }

    public int getOperator() {
        return operator;
    }

    public int getDepth() {
        return depth;
    }

    public int getCost() {
        return cost;
    }

    public State getState() {
        return state;
    }
    
    public void setState(State state){
        this.state = state;
    }

    
    
}
