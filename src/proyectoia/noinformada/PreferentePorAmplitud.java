/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia.noinformada;

import java.awt.Point;
import java.io.File;
import java.util.List;
import java.util.Vector;
import proyectoia.data.Entorno;
import proyectoia.data.Node;
import proyectoia.data.State;

/**
 *
 * @author chris
 */
public class PreferentePorAmplitud {

    private Node root, solution;
    private Entorno environment;
    private Vector<Node> frontier, explored;
    private long totalTime;

    /**
     * Constructor of this class
     */
    public PreferentePorAmplitud(File fileMundo) {
        environment = new Entorno();
        environment.loadFile(fileMundo);
        //initial State
        State initialState = environment.getInitialState();
        //root node - state, parent , operator , cost
        root = new Node(initialState, null, 0, 0);
        //this will contain the solution
        frontier = new Vector<Node>();
        frontier.add(root);
        //this will contain the vectors expanded
        explored = new Vector<Node>();
        //solution is null initially
        solution = null;
    }

    /**
     * Creates and returns a vector with the possible operators to apply
     *
     * @param neighbors array corresponding to the valid neighbor cells of the
     * robot
     * @return a vector with the corresponding operators
     */
    private Vector<Integer> generateOperators(Node node) {
        Vector<Integer> operators = new Vector<Integer>();
        int neighbors[] = environment.getNeighbors(node.getState());
        //if is not a wall then is a valid cell to move
        if (neighbors[0] != 1) {
            operators.add(4);
        }//left
        if (neighbors[1] != 1) {
            operators.add(8);
        }//up
        if (neighbors[2] != 1) {
            operators.add(6);
        }//right       
        if (neighbors[3] != 1) {
            operators.add(5);
        }//down
        return operators;
    }

    /**
     * Creates a child with a given parent and an operator
     *
     * @param parent Node parent of this child
     * @param operator operator used to generate this child
     * @return Node child of given father Node
     */
    public Node childNode(Node parent, int operator) {
        Node child = null;
        //calculates the position where the child will be 
        Point nextPos = parent.applyOperator(operator, environment, parent.getState());
        if (nextPos != null) {//if the movement is valid copy the state from the parent
            int[][] maze = new int[environment.getSize()][environment.getSize()];
            for (int i = 0; i < environment.getSize(); i++) {
                for (int j = 0; j < environment.getSize(); j++) {
                    maze[i][j] = parent.getState().getMaze()[i][j];
                }
            }
            int goalsParent = parent.getState().getGoalsAchieved();
            boolean suit = parent.getState().isSuit();
            //gets the position for the next movement according to the operator
            //create the new state to asign
            State state = new State(nextPos, maze, goalsParent);
            state.setSuit(suit);
            //creates the child node with the calculated state        
            child = new Node(state, parent, operator, 1);
        }//avoid going back
        if (child.goingBack()) {
            child = null;
        }
        return child;
    }

    /**
     * Checks if a node is goal or not and takes items and the suit in case it finds them
     * @param node to be checked
     * @return true if this is the goal, false otherwise
     */
    public boolean expand(Node node) {
        //gets the value of the cell where the robot is
        int value = node.getPositionValue(environment);
        //if it is an item, increase the goals achieved
        if (value == 6) {
            node.getState().setGoalsAchieved(1);
            node.getState().removeItem(node.getState().getPosition());
        }
        //if it is a suit , set the value for the state
        if (value == 3) {
            node.getState().setSuit(true);
            node.getState().removeItem(node.getState().getPosition());
        }
        //check if this is a goal
        boolean goal = node.isItGoal(environment);
        if (goal) {
            solution = node;
        }
        return goal;
    }

    /**
     * Search algorithm using breadth first
     */
    public void breadthFirst() {
        long startTime = System.currentTimeMillis();
        boolean loop = true;
        while (loop) {
            //if there are no more nodes to expand, end it
            if (frontier.isEmpty()) {
                loop = false;
            } else {
                //get the first node in the frotier
                Node node = frontier.remove(0);
                //add the node to the expanded list
                explored.add(node);
                //expand the node and tell if it's the goal
                loop = !expand(node);
                //System.out.println("depth: " + node.getDepth());
                if (loop) {//if node wasn't the goal
                    Vector<Integer> operators = generateOperators(node);
                    for (int i = 0; i < operators.size(); i++) {
                        Node child = childNode(node, operators.get(i));
                        if (child != null) {
                            frontier.add(child);
                        }

                    }
                    //System.out.println("cuantos nodos tiene frontier: "+frontier.size());
                } else {
                    List<Node> path = solution.getPathFromRoot();
                    System.out.println("Path to Victory!");
                    for (int i = 0; i < path.size(); i++) {
                        Point pos = this.getEnvironment().findRobot(path.get(i).getState());
                        System.out.println((int) pos.getX() + ", " + (int) pos.getY());
                    }
                    System.out.println("Number of Expanded nodes: " + explored.size());
                    System.out.println("Depth of the tree: " + solution.getDepth());
                    System.out.println("Found suit?: "+solution.getState().isSuit());
                    loop = false;
                }
            }
        }
        long endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
    }

    /**
     * Returns the root node 
     * @return The root node
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Returns the world of the problem
     * @return World of the problem
     */
    public Entorno getEnvironment() {
        return environment;
    }

    /**
     * Returns the possible nodes to be expanded
     * @return List of nodes to be expanded
     */
    public Vector<Node> getFrontier() {
        return frontier;
    }

    /**
     * returns the expanded nodes
     * @return List of expanded nodes
     */
    public Vector<Node> getExplored() {
        return explored;
    }

    /**
     * Returns the solution of the problem
     * @return The solution node of the problem
     */
    public Node getSolution() {
        return solution;
    }

    /**
     * Returns the running time of the search
     * @return time of the search
     */
    public long getTotalTime() {
        return totalTime;
    }
    /*
    public static void main(String[] args) {

        PreferentePorAmplitud ppa = new PreferentePorAmplitud("Prueba1");
        ppa.breadthFirst();
        System.out.println("running time (milisecs): " + ppa.getTotalTime());

    }
    */
}
