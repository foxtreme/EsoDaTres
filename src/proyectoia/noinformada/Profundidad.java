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
public class Profundidad {

    private Node root, solution;
    private Entorno environment;
    private Vector<Node> frontier, explored;
    private long totalTime;

    /**
     * Constructor of this class
     */
    public Profundidad(File fileMundo) {
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
        if (neighbors[0] != 1) {
            operators.add(4);
        }//left
        if (neighbors[2] != 1) {
            operators.add(6);
        }//right       
        
        if (neighbors[1] != 1) {
            operators.add(8);
        }//up
        
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

        //recover the status data of the parent
        Node child = null;
        Point nextPos = parent.applyOperator(operator, environment, parent.getState());
        
        if (nextPos != null) {
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
            
        }
        //System.out.println("cycling?: "+child.isItGrandpa());
        if(child.isItGrandpa()){
           child=null; 
        }
        return child;
    }

    public boolean expand(Node node) {

        //gets the value of the cell where the robot is
        int value = node.getPositionValue(environment);
        //if it is an item, increase the goals achieved
        if (value == 6) {
            node.getState().setGoalsAchieved(1);
            node.getState().removeItem(node.getState().getPosition());
            System.out.println("found goal " + node.getState().getPosition().toString());
        }
        if (value == 3) {
            node.getState().setSuit(true);
            node.getState().removeItem(node.getState().getPosition());
            System.out.println("found suit " + node.getState().getPosition().toString());
        }
        //check if this is a goal
        boolean goal = node.isItGoal(environment);
        if (goal) {
            solution = node;
        }
        return goal;

    }

    public void depthFirst() {

        long startTime = System.currentTimeMillis();
        boolean end=false;
        while (frontier.size()>0 && (!end)) {
            //if there are no more nodes to expand, end it
            Node node = frontier.remove(0);
            //add the node to the expanded list
            explored.add(node);
            //expand the node and tell if it's the goal
            end = expand(node);
            System.out.println("depth: " + node.getDepth());
            if (!end) {//if node wasn't the goal
                Vector<Integer> operators = generateOperators(node);
                for (int i = 0; i < operators.size(); i++) {
                    Node child = childNode(node, operators.get(i));
                    //System.out.println("child to operator: "+);
                    if (child != null) {
                        frontier.add(0, child);
                    }
                }

            } else {
                List<Node> path = solution.getPathFromRoot();
                for (int i = 0; i < path.size(); i++) {
                    Point pos = this.getEnvironment().findRobot(path.get(i).getState());
                    System.out.println((int) pos.getX() + ", " + (int) pos.getY());
                }
                System.out.println("Number of Expanded nodes: " + explored.size());
                System.out.println("Depth of the tree: " + solution.getDepth());
                
            }
        }
        long endTime = System.currentTimeMillis();
        totalTime  = endTime - startTime;
    }
    



/**
 *
 * @return
 */
public Node getRoot() {
        return root;
    }

    public Entorno getEnvironment() {
        return environment;
    }

    public Vector<Node> getFrontier() {
        return frontier;
    }

    public Vector<Node> getExplored() {
        return explored;
    }

    public Node getSolution() {
        return solution;
    }

    public long getTotalTime() {
        return totalTime;
    }
    /*
    public static void main(String[] args) {

        Profundidad ppa = new Profundidad("Prueba1");
        ppa.depthFirst();
        System.out.println("running time (milisecs): "+ppa.getTotalTime());
        
    }*/
}