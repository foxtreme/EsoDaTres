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
    public Node(State state) {
        this.state = state;
        depth = 0;
        cost = 0;
    }

    /**
     * parameterized constructor of the Node class
     *
     * @param state
     * @param parent
     * @param operator
     * @param depth
     * @param cost
     */
    public Node(State state, Node parent, int operator, int cost) {
        this.state = state;
        this.parent = parent;
        this.operator = operator;
        if (parent != null) {
            this.cost = parent.cost + cost;
            this.depth = parent.depth + 1;
        } else {
            this.cost = this.cost + cost;
            this.depth = 0;
        }
    }

    /**
     * Calls the corresponding directional movement
     *
     * @param operator int corresponding to each of the 4 directions
     * @param environment world of the robot
     */
    public Point applyOperator(int operator, Entorno environment, State state) {
        Point movement = new Point();
        if (isItGrandpa()) {
            movement = null;
        } else {
            if (operator == 4) {
                movement = moveLeft(environment, state);
            }
            if (operator == 6) {
                movement = moveRight(environment, state);
            }
            if (operator == 8) {
                movement = moveUp(environment, state);
            }
            if (operator == 5) {
                movement = moveDown(environment, state);
            }
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
        neighbors = environment.getNeighbors(state);
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
        neighbors = environment.getNeighbors(state);
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
        neighbors = environment.getNeighbors(state);
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
        neighbors = environment.getNeighbors(state);
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
     * @param state Entorno with the world object
     * @return boolean whether it is or not a goal
     */
    public boolean isItGoal(Entorno environment) {
        return (state.getGoalsAchieved() == environment.getGoalCount());
    }

    /**
     * Checks if this node is the root node
     *
     * @return
     */
    public boolean isItRoot() {
        boolean isIt = false;
        if (parent == null) {
            isIt = true;
        }
        return isIt;
    }

    public boolean isItGrandpa() {
        boolean cycle = false;
        Node thisNode = this;
        while (!thisNode.isItRoot() && (!cycle)) {
        
            boolean matrix = (thisNode.getState().getMaze() == thisNode.getParent().getState().getMaze());
            boolean goals = (thisNode.getState().getGoalsAchieved() == thisNode.getParent().getState().getGoalsAchieved());
            boolean suit = (thisNode.getState().isSuit() == thisNode.getParent().getState().isSuit());
            boolean robot = (thisNode.getState().getPosition() == thisNode.getParent().getState().getPosition());
            cycle = (matrix && goals && suit && robot);
            thisNode = thisNode.getParent();
        }
        return cycle;
    }

    /**
     * Gets the list of the solution nodes
     *
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
     *
     * @param environment Entorno world of the robot
     * @return int with the value of the cell
     */
    public int getPositionValue(Entorno environment) {
        Point position = environment.findRobot(state);
        int value = state.getMaze()[position.x][position.y];
        return value;
    }

    public void printStateMaze() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(state.getMaze()[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("=================");
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

    public void setState(State state) {
        this.state = state;
    }

    public void infoNode(){
        System.out.println("Depth: "+depth);
        System.out.println("Cost: "+cost);
        System.out.println("Operator: "+operator);
        System.out.println("Traje: "+state.isSuit());
        System.out.println("goals: "+state.getGoalsAchieved());
        System.out.println(state.getPosition().toString());
    }
}
