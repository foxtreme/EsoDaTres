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
    private int heuristic;//only used for aEstrella

    /**
     * Constructor of the Node class
     *
     * @param state a given state for this node
     */
    public Node(State state) {
        this.state = state;
        depth = 0;
        cost = 0;
    }

    /**
     * Parameterized constructor of the Node class
     *
     * @param state the state of the world for this node
     * @param parent the node who is parent of this node
     * @param operator the operator to get to this node
     * @param cost the cost of getting to this node
     */
    public Node(State state, Node parent, int operator, int cost) {
        this.state = state;
        this.parent = parent;
        this.operator = operator;
        if (parent != null) {//if this node is not root then its depth is 1 more than its parent and the cost accumulates
            this.cost = parent.cost + cost;
            this.depth = parent.depth + 1;
        } else {//if this node is the root then its cost is 0 and is depth too
            this.cost = 0;
            this.depth = 0;
        }
    }

    /**
     * Calls the corresponding directional movement
     *
     * @param operator integer number corresponding to each of the 4 directions
     * @param environment world of the robot
     * @param state the state of the world for this node
     * @return the position where the robot will be next
     */
    public Point applyOperator(int operator, Entorno environment, State state) {
        Point movement = new Point();
        if (operator == 4) {//left
            movement = moveLeft(environment, state);
        }
        if (operator == 6) {//right
            movement = moveRight(environment, state);
        }
        if (operator == 8) {//up
            movement = moveUp(environment, state);
        }
        if (operator == 5) {//down
            movement = moveDown(environment, state);
        }
        return movement;

    }

    /**
     * Moves to the left
     *
     * @param environment world of the robot
     */
    private Point moveLeft(Entorno environment, State state) {
        int neighbors[] = new int[4];
        Point movement = new Point();
        neighbors = environment.getNeighbors(state);//gets all neighbors of the robot
        if (neighbors[0] != 1) {
            Point robot = environment.findRobot(state);
            int row = (int) robot.getX();
            int col = ((int) robot.getY()) - 1;
            movement.setLocation(row, col);
        }
        return movement;
    }

    /**
     * Moves up
     *
     * @param environment world of the robot
     */
    private Point moveUp(Entorno environment, State state) {
        int neighbors[] = new int[4];
        Point movement = new Point();
        neighbors = environment.getNeighbors(state);//gets all neighbors of the robot
        if (neighbors[1] != 1) {
            Point robot = environment.findRobot(state);
            int row = ((int) robot.getX()) - 1;
            int col = (int) robot.getY();
            movement = new Point(row, col);
        }
        return movement;
    }

    /**
     * Moves to the right
     *
     * @param environment world of the robot
     */
    private Point moveRight(Entorno environment, State state) {
        int neighbors[] = new int[4];
        Point movement = new Point();
        neighbors = environment.getNeighbors(state);//gets all neighbors of the robot
        if (neighbors[2] != 1) {
            Point robot = environment.findRobot(state);
            int row = (int) robot.getX();
            int col = ((int) robot.getY()) + 1;
            movement = new Point(row, col);
        }
        return movement;
    }

    /**
     * Moves down
     *
     * @param environment world of the robot
     */
    private Point moveDown(Entorno environment, State state) {
        int neighbors[] = new int[4];
        Point movement = new Point();
        neighbors = environment.getNeighbors(state);//gets all neighbors of the robot
        if (neighbors[3] != 1) {
            Point robot = environment.findRobot(state);
            int row = ((int) robot.getX()) + 1;
            int col = (int) robot.getY();
            movement = new Point(row, col);
        }
        return movement;
    }

    /**
     * Verifies if all goals have been achieved
     *
     * @param environment world of the robot
     * @return boolean whether it is a goal or not 
     */
    public boolean isItGoal(Entorno environment) {
        return (state.getGoalsAchieved() == environment.getGoalCount());
    }

    /**
     * Checks if this node is the root node
     *
     * @return whether this is the root or not
     */
    public boolean isItRoot() {
        boolean isIt = false;
        if (parent == null) {
            isIt = true;
        }
        return isIt;
    }

    /**
     * Verifies if this node is equal to any of its ancestors
     *
     * @return true if this node is any of its own ancestors
     */
    public boolean isItGrandpa() {
        boolean cycle = false;
        //creates the branch from the root to this node
        List<Node> path = this.getPathFromRoot();
        path.remove(path.size() - 1);
        // compares to the whole brach
        for (int i = 0; i < path.size() && !cycle; i++) {
            Node thisNode = path.get(i);
            boolean goals = (getState().getGoalsAchieved() == thisNode.getState().getGoalsAchieved());
            boolean suit = (getState().isSuit() == thisNode.getState().isSuit());
            boolean robot = (getState().getPosition().equals(thisNode.getState().getPosition()));
            cycle = (goals && suit && robot);
        }
        return cycle;
    }

    /**
     * Verifies if the robot is going back to where it came from
     * @return whether the robot is going back or not
     */
    public boolean goingBack() {
        boolean grandpa = false;
        if (getDepth()>1) {
            boolean goals = (getState().getGoalsAchieved() == getParent().getParent().getState().getGoalsAchieved());
            boolean suit = (getState().isSuit() == getParent().getParent().getState().isSuit());
            boolean robot = (getState().getPosition().equals(getParent().getParent().getState().getPosition()));
            grandpa = (goals && suit && robot);
        }
        return grandpa;
    }

    /**
     * Gets the path from the root to the solution nodes
     *
     * @return the list of nodes from the root to this node
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
     *
     * @param environment world of the robot
     * @return the value of the cell where the robot is
     */
    public int getPositionValue(Entorno environment) {
        Point position = environment.findRobot(state);
        int value = state.getMaze()[position.x][position.y];
        return value;
    }

    /**
     * Auxiliary method to show the node matrix in console
     */
    public void printStateMaze() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(state.getMaze()[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("=================");
    }

    /**
     * Auxiliary function to show the info for the node
     */
    public void infoNode() {
        System.out.println("-----------Node----------");
        System.out.println("Depth: " + depth);
        System.out.println("Cost: " + cost);
        System.out.println("Operator: " + operator);
        System.out.println("Traje: " + state.isSuit());
        System.out.println("goals: " + state.getGoalsAchieved());
        System.out.println("position: " + state.getPosition().toString());
        System.out.println("-------------------------");
    }

    /**
     * returns the parent of this node
     * @return Parent of this node
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Returns the operator responsible of creating this node
     * @return the operator used for this node
     */
    public int getOperator() {
        return operator;
    }

    /**
     * Returns the depth of the node
     * @return The depth of the node
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Returns the cost of getting to this node
     * @return The cost of getting to this node
     */
    public int getCost() {
        return cost;
    }

    /**
     * Returns the state of the node
     * @return State of the node
     */
    public State getState() {
        return state;
    }

    /**
     * Sets a state for the node
     * @param state to be set
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Sets a cost for the node
     * @param cost to be set
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Adds a cost to the node other than it already has
     * @param cost to be added
     */
    public void addCost(int cost) {
        this.cost = this.cost + cost;
    }
    
    /**
     * Returns the heuristic associated to this node
     * @return The heuristic
     */
    public int getHeuristic() {
        return heuristic;
    }

    /**
     * Sets an heuristic for this node
     * @param heuristic to be set
     */
    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

}
