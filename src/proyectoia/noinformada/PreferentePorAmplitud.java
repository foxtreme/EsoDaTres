/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia.noinformada;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import proyectoia.data.Entorno;
import proyectoia.data.Node;
import proyectoia.data.State;

/**
 *
 * @author chris
 */
public class PreferentePorAmplitud {
    private Node root,solution;
    private Entorno environment;
    private Vector<Node> frontier,explored;
    private String indexes[];
    /**
     * Constructor of this class
     */
    public PreferentePorAmplitud(){
        environment = new Entorno();
        environment.loadFile("Prueba1");
        indexes = new String[100];
        int k = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                indexes[k] = i + "" + j;
            }
        }
        //initial State
        State initialState = environment.getInitialState();
        //root node - state, parent , operator , cost
        root = new Node(initialState,null,0,0);
        //this will contain the solution
        frontier=new Vector<Node>();
        frontier.add(root);
        //this will contain the vectors expanded
        explored= new Vector<Node>();
        //solution is null initially
        solution = null;
    }
    
    /**
     * Creates and returns a vector with the possible operators to apply
     * @param neighbors array corresponding to the valid neighbor cells of the robot
     * @return a vector with the corresponding operators
     */
    private Vector<Integer> generateOperators(int[] neighbors) {
        Vector <Integer> operators = new Vector<Integer>();
        if(neighbors[0]!=1){operators.add(4);}//left
        if(neighbors[1]!=1){operators.add(8);}//up
        if(neighbors[2]!=1){operators.add(6);}//right
        if(neighbors[3]!=1){operators.add(5);}//down
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
        
        Map maze = new HashMap();
        
        for(int i=0;i<100;i++){
            
                maze.put(indexes[i], parent.getState().getMaze().get(indexes[i]));
            
        }
                
        
        Point positionParent = parent.getState().getPosition();
        int goalsParent = parent.getState().getGoalsAchieved();
        //gets the position for the next movement according to the operator
        Point nextPos = parent.applyOperator(operator, environment, parent.getState());
        //create the new state to asign
        State state = new State(nextPos,(HashMap)maze,goalsParent);
        //creates the child node with the calculated state        
        Node child = new Node(state, parent, operator, 1);
        
        return child;
    }

    public boolean expand(Node node) {
        //gets the value of the cell where the robot is
        int value = node.getPositionValue(environment);
        System.out.println("robot en: "+node.getState().getPosition().toString());
        System.out.println("value: "+value);
        //if it is an item, increase the goals achieved
        if (value == 6) {
            node.getState().setGoalsAchieved(1);
            node.getState().removeItem(node.getState().getPosition());
            System.out.println(node.getState().getMaze().get(node.getState().getPosition().x+""+node.getState().getPosition().y));
        }
        //check if this is a goal
        boolean goal = node.isItGoal(environment);
        if (goal) {
            solution = node;
        }
        System.out.println("goals: "+node.getState().getGoalsAchieved());
        return goal;
    }

    public void breadthFirst() {
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
                if (loop) {//if node wasn't the goal
                    Vector <Integer> operators = generateOperators(getEnvironment().getNeighbors(node.getState()));
                    for(int i=0; i<operators.size();i++){
                        Node child = childNode(node,operators.get(i));
                        Point q = child.getState().getPosition();
                        frontier.add(child); 
                        System.out.println("Depth: "+child.getDepth());
                    }
                    //System.out.println("cuantos nodos tiene frontier: "+frontier.size());
                }else{
                    List<Node> path = solution.getPathFromRoot();
                    for(int i=0;i<path.size();i++){
                        Point pos = this.getEnvironment().findRobot(path.get(i).getState());
                        System.out.println(pos.getX()+", "+pos.getY());
                    }
                    System.out.println("Number of Expanded nodes: "+explored.size());
                    System.out.println("Depth of the tree: "+solution.getDepth());
                    loop=false;
                }
            }
        }
    }
    
    /**
     * 
     * @return 
     */
    public Node getRoot(){
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

    public Node getSolution(){
        return solution;
    }
    
    public static void main(String []args){
        PreferentePorAmplitud ppa = new PreferentePorAmplitud();
        //ppa.breadthFirst();
        /*
        boolean b0,b1,b2,b3,b4,b5,b6,b7;
        Node n0 = ppa.getFrontier().firstElement();
        b0 = ppa.expand(n0);
        n0.printStateMaze();
        
        Node n1 = ppa.childNode(n0, 4);
        b1 = ppa.expand(n1);
        
        n1.printStateMaze();
        Node n2 = ppa.childNode(n1, 4);
        b2 = ppa.expand(n2);
        n2.printStateMaze();
        n1.printStateMaze();
        Node n3 = ppa.childNode(n2, 8);
        b3 = ppa.expand(n3);
        Node n4 = ppa.childNode(n3, 5);
        b4 = ppa.expand(n4);
        Node n5 = ppa.childNode(n3, 6);
        b5 = ppa.expand(n5);
        Node n6 = ppa.childNode(n5, 6);
        b6 = ppa.expand(n6);
        Node n7 = ppa.childNode(n6, 8);
        b7 = ppa.expand(n7);
        System.out.println(n0.getState().getPosition().toString());
        System.out.println(n1.getState().getPosition().toString());
        System.out.println(n2.getState().getPosition().toString());
        System.out.println(n3.getState().getPosition().toString());
        System.out.println(n4.getState().getPosition().toString());
        System.out.println(n5.getState().getPosition().toString());
        System.out.println(n6.getState().getPosition().toString());
        System.out.println(n7.getState().getPosition().toString());
            
        System.out.println(n0.getState().getGoalsAchieved());
        System.out.println(n1.getState().getGoalsAchieved());
        System.out.println(n2.getState().getGoalsAchieved());
        System.out.println(n3.getState().getGoalsAchieved());
        System.out.println(n4.getState().getGoalsAchieved());
        System.out.println(n5.getState().getGoalsAchieved());
        System.out.println(n6.getState().getGoalsAchieved());
        System.out.println(n7.getState().getGoalsAchieved());
        */
    }
    
}
