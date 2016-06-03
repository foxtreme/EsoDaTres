/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia.informada;


import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import proyectoia.data.Entorno;
import proyectoia.data.Node;
import proyectoia.data.State;

/**
 *
 * @author chris
 */
public class Aestrella {

    private Node root, solution;
    private Entorno environment;
    private List<Node> frontier, explored;
    private long totalTime;
    
    
    /**
     * Constructor of this class
     * @param fileMundo file containing the world to be tested
     */
    public Aestrella(File fileMundo) {
        environment = new Entorno();
        environment.loadFile(fileMundo);
        //initial State
        State initialState = environment.getInitialState();
        //root node - state, parent , operator , cost
        root = new Node(initialState, null, 0, 0);
        //this will contain the solution
        frontier = new ArrayList<Node>();
        frontier.add(root);
        //this will contain the vectors expanded
        explored = new ArrayList<Node>();
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
     * Sorts the frontier and puts in front the nodes with less heuristic so they are expanded first
     * @param nodeList List of nodes to be sorted by their heuristic
     */
    public void sort(List<Node> nodeList) {
        Collections.sort(nodeList, new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return (n1.getHeuristic()<n2.getHeuristic())?-1:(n1.getHeuristic()>n2.getHeuristic())?1:0;
            }
        });
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
        //gets the position for the next movement according to the operator
        Point nextPos = parent.applyOperator(operator, environment, parent.getState());
        if (nextPos != null) {//if the move can be made
            int[][] maze = new int[environment.getSize()][environment.getSize()];
            for (int i = 0; i < environment.getSize(); i++) {
                for (int j = 0; j < environment.getSize(); j++) {
                    maze[i][j] = parent.getState().getMaze()[i][j];
                }
            }
            int goalsParent = parent.getState().getGoalsAchieved();
            boolean suit = parent.getState().isSuit();
            //create the new state to asign
            State state = new State(nextPos, maze, goalsParent);
            state.setSuit(suit);
            //creates the child node with the calculated state        
            child = new Node(state, parent, operator, 1);
        }
        if (child.goingBack()) {//if the child node has been created before, sets it to null
            child = null;
        }else{//if the child can be created calculates the cost of being in the new cell and adds it 
            int cellType = child.getPositionValue(environment);
            int movementCost=0;
            int distance = this.getEnvironment().findClosestGoal(child.getState());
            
            if(cellType==4 && (!child.getState().isSuit())){
                movementCost=3;
            }
            if(cellType==5 && (!child.getState().isSuit())){
                movementCost=6;
            }
            child.addCost(movementCost);
            movementCost = child.getCost();
            int heuristic = distance+movementCost;
            child.setHeuristic(heuristic);
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
     * Search algorithm using A*
     */
    public void aStar() {
        long startTime = System.currentTimeMillis();
        boolean loop = true;
        while (loop) {
            //if there are no more nodes to expand, end it
            if (frontier.isEmpty()) {
                loop = false;
            } else {
                //get the first node in the frotier
                sort(frontier);
                Node node = frontier.remove(0);
                //add the node to the expanded list
                explored.add(node);
                //expand the node and tell if it's the goal
                loop = !expand(node);
                if (loop) {//if node wasn't the goal
                    Vector<Integer> operators = generateOperators(node);
                    for (int i = 0; i < operators.size(); i++) {
                        Node child = childNode(node, operators.get(i));
                        if (child != null) {
                            frontier.add(child);
                        }

                    }
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
                    System.out.println("total cost: "+solution.getCost());
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
    public List<Node> getFrontier() {
        return frontier;
    }

    /**
     * returns the expanded nodes
     * @return List of expanded nodes
     */
    public List<Node> getExplored() {
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
        
        Aestrella as = new Aestrella("Prueba1");
        as.aStar();
        System.out.println("running time (milisecs): " + as.getTotalTime());
        
    }*/
    
    
    
}
